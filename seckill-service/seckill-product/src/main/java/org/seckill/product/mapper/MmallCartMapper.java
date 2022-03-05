package org.seckill.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MmallCart;

import java.util.List;

public interface MmallCartMapper extends BaseMapper<MmallCart> {
    int updateBatch(List<MmallCart> list);

    int updateBatchSelective(List<MmallCart> list);

    int batchInsert(@Param("list") List<MmallCart> list);

    int insertOrUpdate(MmallCart record);

    int insertOrUpdateSelective(MmallCart record);
}