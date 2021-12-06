package org.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    int updateBatch(List<User> list);

    int updateBatchSelective(List<User> list);

    int batchInsert(@Param("list") List<User> list);

    int insertOrUpdate(User record);

    int insertOrUpdateSelective(User record);
}