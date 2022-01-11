package org.seckill.zk_manager.lockinfo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@Service
public class LockerByZk implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(LockerByZk.class);

    @Resource(name = "myCuratorFramework")
    private CuratorFramework curatorFramework;

    @Value("${zookeeper.lockPath}")
    private String LOCK_NAME_PREFIX;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 获取分布式锁
     */
    public void acquireDistributedLock(String path) {
        String keyPath = LOCK_NAME_PREFIX + "/" + path;
        while (true) {
            try {
                curatorFramework
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)  // 临时节点
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(keyPath);
                log.info("success to acquire lock for path:{}", keyPath);
                break;
            } catch (Exception e) {
                log.info("failed to acquire lock for path:{}", keyPath);
                log.info("while try again .......");
                if (countDownLatch.getCount() <= 0) {
                    countDownLatch = new CountDownLatch(1);
                }
                try {
                    // 阻塞等待锁释放，重新获取
                    countDownLatch.wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 释放分布式锁
     */
    public boolean releaseDistributedLock(String path) {
        String keyPath = LOCK_NAME_PREFIX + "/" + path;
        try {
            if (curatorFramework.checkExists().forPath(keyPath) != null) {
                curatorFramework.delete().forPath(keyPath);
            }
        } catch (Exception e) {
            log.error("failed to release lock");
            return false;
        }
        return true;
    }

    /**
     * 创建 watcher 事件
     */
    private void addWatcher(String path) throws Exception {
        String keyPath;
        if (path.equals(LOCK_NAME_PREFIX)) {
            keyPath = "/" + path;
        } else {
            keyPath = LOCK_NAME_PREFIX + "/" + path;
        }
        final CuratorCache cache = CuratorCache.build(curatorFramework, keyPath);
        CuratorCacheListener listener = CuratorCacheListener
                .builder()
                .forNodeCache(new NodeCacheListener() {
                    public void nodeChanged() throws Exception {
                        log.info(keyPath + "v节点监听事件触发");
                        countDownLatch.countDown();
                    }
                })
                .build();
        cache.listenable().addListener(listener);
        cache.start();
    }

    /**
     * 初始化创建永久父节点
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        curatorFramework = curatorFramework.usingNamespace("lock-namespace");
        String path = LOCK_NAME_PREFIX;
        try {
            if (curatorFramework.checkExists().forPath(path) == null) {
                curatorFramework
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(path);
            }
            addWatcher(LOCK_NAME_PREFIX);
            log.info("root path 的 watcher 事件创建成功");
        } catch (Exception e) {
            log.error("connect zookeeper fail，please check the log >> {}", e.getMessage(), e);
        }
    }
}
