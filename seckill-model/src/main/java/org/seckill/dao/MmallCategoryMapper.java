package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MmallCategory;

import java.util.List;

public interface MmallCategoryMapper extends BaseMapper<MmallCategory> {
    int updateBatch(List<MmallCategory> list);

    int updateBatchSelective(List<MmallCategory> list);

    int batchInsert(@Param("list") List<MmallCategory> list);

    int insertOrUpdate(MmallCategory record);

    int insertOrUpdateSelective(MmallCategory record);
}