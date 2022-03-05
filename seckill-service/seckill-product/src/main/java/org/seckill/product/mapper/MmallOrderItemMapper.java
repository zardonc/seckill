package org.seckill.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MmallOrderItem;

import java.util.List;

public interface MmallOrderItemMapper extends BaseMapper<MmallOrderItem> {
    int updateBatch(List<MmallOrderItem> list);

    int updateBatchSelective(List<MmallOrderItem> list);

    int batchInsert(@Param("list") List<MmallOrderItem> list);

    int insertOrUpdate(MmallOrderItem record);

    int insertOrUpdateSelective(MmallOrderItem record);
}