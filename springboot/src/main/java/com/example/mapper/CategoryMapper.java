package com.example.mapper;

import com.example.entity.Category;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 操作category相关数据接口
 */
public interface CategoryMapper {

    /**
     * 查询
     */
    int insert(Category category);

    /**
     * 删除
     */
    int deleteById(int id);

    /**
     * 修改
     */
    int updateById(Category category);

    /**
     * 根据ID查询
     */
    Category selectById(int id);

    /**
     * 查询所有
     */
    List<Category> selectAll(Category category);

}
