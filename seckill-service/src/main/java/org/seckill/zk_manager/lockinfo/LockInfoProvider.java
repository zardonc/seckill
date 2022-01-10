package org.seckill.zk_manager.lockinfo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.seckill.zk_manager.annotation.ZkLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 功能描述: 创建锁的相关信息
 * 〈〉
 *
 * @Param:
 * @Return:
 * @Author: lang
 */
@Component
public class LockInfoProvider {
    public static final String LOCK_NAME_SEPARATOR = "/";
    @Value("${zookeeper.lockPath}")
    private String LOCK_NAME_PREFIX;
    @Resource
    private BusinessKeyProvider businessKeyProvider;

    /**
     * 功能描述: 获取锁信息
     * 锁的名称 = 根路径+子路径+锁名
     * 〈〉
     *
     * @Param: [joinPoint, zkLock]
     * @Return: org.seckill.zk_manager.lockinfo.LockInfo
     * @Author: lang
     */
    public LockInfo get(ProceedingJoinPoint joinPoint, ZkLock zkLock) {
        //获取到切面的信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //根据自定义业务key 获取keyName
        String businessKeyName = businessKeyProvider.getKeyName(joinPoint, zkLock);
        //拼接lockName地址
        String lockPath = LOCK_NAME_PREFIX + LOCK_NAME_SEPARATOR + getName(zkLock.name(), signature) + businessKeyName;
        //实例化锁
        return new LockInfo(lockPath);
    }

    /**
     * 功能描述: 获取锁名称
     * 〈〉
     *
     * @Param: [annotationName, signature]
     * @Return: java.lang.String
     * @Author: lang
     */
    private String getName(String annotationName, MethodSignature signature) {
        //如果keyName没有设置 则返回方法名称
        if (annotationName.isEmpty()) {
            return String.format("%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName());
        } else {
            return annotationName;
        }
    }
}
