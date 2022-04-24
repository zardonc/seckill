package org.seckill.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.product.entity.Goods;
import org.seckill.product.vo.GoodsVo;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    int updateBatch(List<Goods> list);

    int updateBatchSelective(List<Goods> list);

    int batchInsert(@Param("list") List<Goods> list);

    int insertOrUpdate(Goods record);

    int insertOrUpdateSelective(Goods record);

    List<GoodsVo> goodsVoList();

    GoodsVo goodsVoByGoodsId(@Param("goodsId") Long goodsId);
}