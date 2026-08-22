package com.example.mapper;

import com.example.entity.Carousel;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 操作carousel相关数据接口
 */
public interface CarouselMapper {

    /**
     * 查询
     */
    int insert(Carousel carousel);

    /**
     * 删除
     */
    int deleteById(int id);

    /**
     * 修改
     */
    int updateById(Carousel carousel);

    /**
     * 根据ID查询
     */
    Carousel selectById(int id);

    /**
     * 查询所有
     */
    List<Carousel> selectAll(Carousel carousel);

}
