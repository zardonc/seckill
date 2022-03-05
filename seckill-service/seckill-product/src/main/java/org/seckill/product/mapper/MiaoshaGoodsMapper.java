package org.seckill.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MiaoshaGoods;

import java.util.List;

public interface MiaoshaGoodsMapper extends BaseMapper<MiaoshaGoods> {
    int updateBatch(List<MiaoshaGoods> list);

    int updateBatchSelective(List<MiaoshaGoods> list);

    int batchInsert(@Param("list") List<MiaoshaGoods> list);

    int insertOrUpdate(MiaoshaGoods record);

    int insertOrUpdateSelective(MiaoshaGoods record);
}