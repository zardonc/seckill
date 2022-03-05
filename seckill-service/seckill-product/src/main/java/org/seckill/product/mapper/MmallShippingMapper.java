package org.seckill.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MmallShipping;

import java.util.List;

public interface MmallShippingMapper extends BaseMapper<MmallShipping> {
    int updateBatch(List<MmallShipping> list);

    int updateBatchSelective(List<MmallShipping> list);

    int batchInsert(@Param("list") List<MmallShipping> list);

    int insertOrUpdate(MmallShipping record);

    int insertOrUpdateSelective(MmallShipping record);
}