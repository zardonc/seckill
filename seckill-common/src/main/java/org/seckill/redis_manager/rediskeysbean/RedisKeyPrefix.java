package org.seckill.redis_manager.rediskeysbean;

import java.util.concurrent.TimeUnit;

public abstract class RedisKeyPrefix {
    private final Long expTTL;
    private final String prefix;
    private final TimeUnit timeUnit;

    public RedisKeyPrefix(String prefix, Long expTTL, TimeUnit timeUnit) {
        this.expTTL = expTTL;
        this.prefix = prefix;
        this.timeUnit = timeUnit;
    }

    public RedisKeyPrefix(String prefix) {
        this(prefix, 0L, TimeUnit.SECONDS);
    }

    public RedisKeyPrefix(String prefix, Long expTTL) {
        this(prefix, expTTL, TimeUnit.SECONDS);
    }

    // 默认0永不过期
    public Long getExpTTL() {
        return expTTL;
    }

    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
