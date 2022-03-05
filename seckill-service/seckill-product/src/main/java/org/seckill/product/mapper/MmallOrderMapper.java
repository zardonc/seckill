package org.seckill.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MmallOrder;

import java.util.List;

public interface MmallOrderMapper extends BaseMapper<MmallOrder> {
    int updateBatch(List<MmallOrder> list);

    int updateBatchSelective(List<MmallOrder> list);

    int batchInsert(@Param("list") List<MmallOrder> list);

    int insertOrUpdate(MmallOrder record);

    int insertOrUpdateSelective(MmallOrder record);
}