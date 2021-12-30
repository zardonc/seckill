package org.seckill.service;

import org.seckill.base.BaseResponse;
import org.seckill.entity.MiaoshaUser;

public interface MiaoshaBizService {
    // 创建秒杀链接
    BaseResponse<String> createMiaoshaPath(MiaoshaUser user, Long goodsId);
}
