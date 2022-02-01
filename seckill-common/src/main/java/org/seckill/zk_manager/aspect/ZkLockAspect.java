package org.seckill.zk_manager.aspect;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.seckill.exception.BizException;
import org.seckill.zk_manager.annotation.ZkLock;
import org.seckill.zk_manager.lockinfo.LockInfo;
import org.seckill.zk_manager.lockinfo.LockInfoProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Aspect
@Component
public class ZkLockAspect {
    private static final Logger log = LoggerFactory.getLogger(ZkLockAspect.class);
    // 当前锁
    ThreadLocal<LockInfo> currentThreadLock = new ThreadLocal<>();
    @Resource
    private LockInfoProvider lockInfoProvider;
    @Resource(name = "myCuratorFramework")
    private CuratorFramework zkClient;

    /**
     * 功能描述: 加锁
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     */
    @Around(value = "@annotation(zkLock)")
    public Object around(ProceedingJoinPoint joinPoint, ZkLock zkLock) throws Throwable {
        zkLock.name();
        zkLock.keys();
        //获取自定义锁信息
        LockInfo lockInfo = lockInfoProvider.get(joinPoint, zkLock);
        try {
            if (zkClient.checkExists().forPath(lockInfo.getLockPath()) == null) {
                log.info("初始化根节点==========>" + lockInfo.getLockPath());
                zkClient.create().creatingParentsIfNeeded().forPath(lockInfo.getLockPath());
                log.info("当前线程" + Thread.currentThread().getName() + "初始化根节点" + lockInfo.getLockPath());
            }
        } catch (Exception e) {
            throw new BizException("zookeeper构建根节点失败");
        }
        try {
            lockInfo.setNode(zkClient.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(lockInfo.getLockPath() + "/")
                    .replace(lockInfo.getLockPath() + LockInfo.SEPARATOR_CHARACTER, ""));
            currentThreadLock.set(lockInfo);
        } catch (Exception e) {
            throw new BizException("zookeeper创建锁节点失败");
        }
        // 检查是否成功获取锁
        checkLock();
        return joinPoint.proceed();
    }

    /**
     * 功能描述: 方法结束后解锁
     * 〈〉
     *
     * @Param: [joinPoint, zkLock]
     * @Return: void
     * @Author: lang
     */
    @AfterReturning(value = "@annotation(zkLock)")
    public void afterReturning(JoinPoint joinPoint, ZkLock zkLock) {
        unlock();
    }

    @AfterThrowing(value = "@annotation(zkLock)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, ZkLock zkLock, Throwable ex) throws Throwable {
        unlock();
        throw ex;
    }

    /**
     * 功能描述: 自选获取锁
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     */
    private void checkLock() {
        // 判断获取锁
        if (!tryLock()) {
            // 监听锁
            waitForLock();
            // 前锁已解除继续判断等待
            checkLock();
        }
    }

    public void unlock() {
        try {
            zkClient.delete().guaranteed().deletingChildrenIfNeeded()
                    .forPath(currentThreadLock.get().getNodePath());
            log.info(currentThreadLock.get().getNodePath() + " unlock success!");
            currentThreadLock.remove();
        } catch (Exception e) {
            log.error("unlock failed!");
            //guaranteed()保障机制，若未删除成功，只要会话有效会在后台一直尝试删除
        }
    }

    /**
     * 功能描述: 阻塞监听节点，锁等待
     * 〈〉
     *
     * @Param: []
     * @Return: void
     * @Author: lang
     */
    private void waitForLock() {
        CountDownLatch latch = new CountDownLatch(1);
        // 创建监听器watch
        CuratorCache curatorCache = CuratorCache.build(zkClient, currentThreadLock.get().getLastNodePath());
        CuratorCacheListener listener = CuratorCacheListener
                .builder()
                .forNodeCache(new NodeCacheListener() {
                    @Override
                    public void nodeChanged() throws Exception {
                        log.info(currentThreadLock.get().getLastNodePath() + "v节点监听事件触发");
                        latch.countDown();
                    }
                })
                .build();
        curatorCache.listenable().addListener(listener);
        curatorCache.start();
        // 阻塞等待前一节点删除
        try {
            if (zkClient.checkExists().forPath(currentThreadLock.get().getLastNodePath()) != null) {
                latch.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            curatorCache.close();
        }
    }

    private boolean tryLock() {
        List<String> childrenNodes = null;
        try {
            childrenNodes = zkClient.getChildren().forPath(currentThreadLock.get().getNodePath());
        } catch (Exception e) {
            return false;
        }
        Collections.sort(childrenNodes);
        if (currentThreadLock.get().getNodePath().equals(childrenNodes.get(0))) {
            log.info("当前线程获得锁" + currentThreadLock.get().getNodePath());
            return true;
        } else {
            // 取前一节点
            int curIndex = childrenNodes.indexOf(currentThreadLock.get().getNodePath().substring(currentThreadLock.get().getLockPath().length() + 1));
            // 如果是-1表示children里面没有该节点
            currentThreadLock.get().setLastNode(childrenNodes.get(curIndex - 1));
            log.info("前一个节点: " + childrenNodes.get(curIndex - 1));
        }
        return false;
    }
}
