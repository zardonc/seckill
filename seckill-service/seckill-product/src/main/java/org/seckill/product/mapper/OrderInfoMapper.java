package org.seckill.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.OrderInfo;

import java.util.List;

public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    int updateBatch(List<OrderInfo> list);

    int updateBatchSelective(List<OrderInfo> list);

    int batchInsert(@Param("list") List<OrderInfo> list);

    int insertOrUpdate(OrderInfo record);

    int insertOrUpdateSelective(OrderInfo record);
}