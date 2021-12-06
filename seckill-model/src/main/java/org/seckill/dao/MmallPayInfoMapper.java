package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MmallPayInfo;

import java.util.List;

public interface MmallPayInfoMapper extends BaseMapper<MmallPayInfo> {
    int updateBatch(List<MmallPayInfo> list);

    int updateBatchSelective(List<MmallPayInfo> list);

    int batchInsert(@Param("list") List<MmallPayInfo> list);

    int insertOrUpdate(MmallPayInfo record);

    int insertOrUpdateSelective(MmallPayInfo record);
}