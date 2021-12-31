package org.seckill.service.impl;

import cn.hutool.core.lang.UUID;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.seckill.base.BaseResponse;
import org.seckill.base.StatusCode;
import org.seckill.entity.MiaoshaUser;
import org.seckill.redis_manager.rediskeysbean.MiaoshaKey;
import org.seckill.service.MiaoshaBizService;
import org.seckill.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MiaoshaBizServiceImpl implements MiaoshaBizService {
    private static final Logger log = LoggerFactory.getLogger(MiaoshaBizServiceImpl.class);

    @Resource
    private RedissonClient redissonClient;

    @Override
    public BaseResponse<String> createMiaoshaPath(MiaoshaUser user, Long goodsId) {
        BaseResponse<String> result = BaseResponse.Success();
        if (user == null || goodsId <= 0) {
            return BaseResponse.error(StatusCode.USER_NOT_LOGIN);
        }
        String pathStr = MD5Util.md5(UUID.fastUUID() + "123456");
        log.info("createMiaoshaPath str:{}", pathStr);
        MiaoshaKey miaoshaKey = MiaoshaKey.getMiaoshaPathKey;
        String cacheKey = miaoshaKey.getPrefix() + user.getNickname() + "_" + goodsId;
        RBucket<String> keyBucket = redissonClient.getBucket(cacheKey);
        keyBucket.set(cacheKey, miaoshaKey.getExpTTL(), miaoshaKey.getTimeUnit());
        return result;
    }

    @Override
    public BaseResponse<Boolean> verifyMiaoshaPath(MiaoshaUser user, Long goodsId, String path) {
        Boolean verifyFlag = false;
        if (StringUtils.isBlank(path)) {
            return BaseResponse.errorWithData(StatusCode.DATA_NOT_EXISTS, verifyFlag);
        }
        MiaoshaKey miaoshaKey = MiaoshaKey.getMiaoshaPathKey;
        String pathKey = miaoshaKey.getPrefix() + user.getNickname() + "_" + goodsId;
        RBucket<String> keyBucket = redissonClient.getBucket(pathKey);
        if (keyBucket.get().equals(path)) {
            verifyFlag = true;
        }
        return BaseResponse.SuccessWithData(verifyFlag);
    }
}
