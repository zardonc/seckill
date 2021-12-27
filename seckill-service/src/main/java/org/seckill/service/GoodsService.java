package org.seckill.service;

import org.seckill.base.BaseResponse;
import org.seckill.vo.GoodsVo;

import java.util.List;

public interface GoodsService {
    BaseResponse<List<GoodsVo>> goodsVoList();

    BaseResponse<GoodsVo> goodsVoById(Long goodsId);

    BaseResponse<Boolean> reduceStock(GoodsVo goodsVo);
}
