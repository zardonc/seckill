package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MiaoshaOrder;

import java.util.List;

public interface MiaoshaOrderMapper extends BaseMapper<MiaoshaOrder> {
    int updateBatch(List<MiaoshaOrder> list);

    int updateBatchSelective(List<MiaoshaOrder> list);

    int batchInsert(@Param("list") List<MiaoshaOrder> list);

    int insertOrUpdate(MiaoshaOrder record);

    int insertOrUpdateSelective(MiaoshaOrder record);
}