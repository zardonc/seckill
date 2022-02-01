package org.seckill.redis_manager.rediskeysbean;

public class MiaoshaKey extends RedisKeyPrefix {

    public static MiaoshaKey getMiaoshaPathKey = new MiaoshaKey(60L, "mp");
    public static MiaoshaKey getMiaoshaVerifyCodeKey = new MiaoshaKey(300L, "vc");
    public MiaoshaKey(Long expTTL, String prefix) {
        super(prefix, expTTL);
    }
}
