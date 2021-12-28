package org.seckill.service.impl;

import org.seckill.base.BaseResponse;
import org.seckill.base.StatusCode;
import org.seckill.dao.GoodsMapper;
import org.seckill.service.GoodsService;
import org.seckill.vo.GoodsDetailVo;
import org.seckill.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    public BaseResponse<GoodsDetailVo> goodsVoById(Long goodsId) {
        BaseResponse<GoodsDetailVo> response = BaseResponse.Success();
        log.info("***goodsVoById查询***start!");
        GoodsVo goodsVo;
        try {
            goodsVo = Objects.requireNonNull(goodsMapper.goodsVoByGoodsId(goodsId));
        } catch (Exception e) {
            log.error("***查询goodsVoById发生错误*** error:{}", e);
            response = BaseResponse.error(StatusCode.DATA_NOT_EXISTS);
            return response;
        }
        // 判断秒杀状态
        LocalDateTime startAt = goodsVo.getStartDate();
        LocalDateTime endAt = goodsVo.getEndDate();
        LocalDateTime timeNow = LocalDateTime.now();
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        // 秒杀未开始
        if (timeNow.isBefore(startAt)) {
            miaoshaStatus = StatusCode.SECKILL_NOT_START.getCode();
            remainSeconds = (int) timeNow.until(startAt, ChronoUnit.SECONDS);
        } else if (timeNow.isAfter(endAt)) {// 秒杀已结束
            miaoshaStatus = StatusCode.SECKILL_OVER.getCode();
            remainSeconds = -1;
        } else {
            miaoshaStatus = StatusCode.SECKILL_ING.getCode();
            remainSeconds = 0;
        }
        GoodsDetailVo detailVo = new GoodsDetailVo();
        detailVo.setMiaoshaStatus(miaoshaStatus);
        detailVo.setRemainSeconds(remainSeconds);
        detailVo.setGoods(goodsVo);
        response.setData(detailVo);
        return response;
    }

    @Override
    public BaseResponse<Boolean> reduceStock(GoodsVo goodsVo) {
        return null;
    }
}
