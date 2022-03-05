package org.seckill.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MmallUser;

import java.util.List;

public interface MmallUserMapper extends BaseMapper<MmallUser> {
    int updateBatch(List<MmallUser> list);

    int updateBatchSelective(List<MmallUser> list);

    int batchInsert(@Param("list") List<MmallUser> list);

    int insertOrUpdate(MmallUser record);

    int insertOrUpdateSelective(MmallUser record);
}