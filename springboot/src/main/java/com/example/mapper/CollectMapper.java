package com.example.mapper;

import com.example.entity.Collect;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 操作collect相关数据接口
 */
public interface CollectMapper {

    /**
     * 查询
     */
    int insert(Collect collect);

    /**
     * 删除
     */
    int deleteById(int id);

    /**
     * 修改
     */
    int updateById(Collect collect);

    /**
     * 根据ID查询
     */
    Collect selectById(int id);

    /**
     * 查询所有
     */
    List<Collect> selectAll(Collect collect);

}
