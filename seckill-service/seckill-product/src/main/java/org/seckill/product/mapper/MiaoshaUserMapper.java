package org.seckill.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.MiaoshaUser;

import java.util.List;

public interface MiaoshaUserMapper extends BaseMapper<MiaoshaUser> {
    int updateBatch(List<MiaoshaUser> list);

    int updateBatchSelective(List<MiaoshaUser> list);

    int batchInsert(@Param("list") List<MiaoshaUser> list);

    int insertOrUpdate(MiaoshaUser record);

    int insertOrUpdateSelective(MiaoshaUser record);
}