package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MmallProduct;

import java.util.List;

public interface MmallProductMapper extends BaseMapper<MmallProduct> {
    int updateBatch(List<MmallProduct> list);

    int updateBatchSelective(List<MmallProduct> list);

    int batchInsert(@Param("list") List<MmallProduct> list);

    int insertOrUpdate(MmallProduct record);

    int insertOrUpdateSelective(MmallProduct record);
}