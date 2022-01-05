package org.seckill.zk_manager.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述: Zk锁注解
 * 〈〉
 * @Param:
 * @Return:
 * @Author: lang
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ZkLock {
    // 锁的名称
    String name() default "";
    // 业务keys
    String[] keys() default {};
}
