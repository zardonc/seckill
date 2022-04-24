package org.seckill.product.service;

import org.seckill.base.BaseResponse;
import org.seckill.product.vo.GoodsDetailVo;
import org.seckill.product.vo.GoodsVo;

import java.util.List;

public interface GoodsService {
    BaseResponse<List<GoodsVo>> goodsVoList();

    BaseResponse<GoodsDetailVo> goodsVoById(Long goodsId);

    BaseResponse<Boolean> reduceStock(GoodsVo goodsVo);
}
