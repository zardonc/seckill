package org.seckill.service.impl;

import org.seckill.base.BaseResponse;
import org.seckill.base.StatusCode;
import org.seckill.dao.GoodsMapper;
import org.seckill.service.GoodsService;
import org.seckill.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    private static final Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public BaseResponse<List<GoodsVo>> goodsVoList() {
        BaseResponse<List<GoodsVo>> response = BaseResponse.Success();
        log.info("***goodsVoList查询***start!");
        List<GoodsVo> goodsVoList = Collections.emptyList();
        try {
            goodsVoList = goodsMapper.goodsVoList();
        } catch (Exception e) {
            log.error("***查询goodsvoList发生错误*** error:{}", e);
            response = BaseResponse.error(StatusCode.DATA_NOT_EXISTS);
            return response;
        }
        response.setData(goodsVoList);
        return response;
    }

    @Override
    public BaseResponse<GoodsVo> goodsVoById(Long goodsId) {
        return null;
    }

    @Override
    public BaseResponse<Boolean> reduceStock(GoodsVo goodsVo) {
        return null;
    }
}
