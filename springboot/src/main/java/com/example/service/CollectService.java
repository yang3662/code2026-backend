package com.example.service;


import com.example.entity.Collect;
import com.example.mapper.CollectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 业务处理
 */
@Service
public class CollectService {

    @Resource
    private CollectMapper collectMapper;

    /**
     * 新增
     */
    public void add(Collect collect){
        collectMapper.insert(collect);
    }

    /**
     * 删除
     */
    public void deleteById(int id){
        collectMapper.deleteById(id);
    }

    /**
     * 修改
     */
    public void updateById(Collect collect){
        collectMapper.updateById(collect);
    }

    /**
     * 根据ID查询
     */
    public Collect selectById(int id){
        return collectMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Collect> selectAll(Collect collect){
        return collectMapper.selectAll(collect);
    }

    /**
     * 分页查询
     */
    public PageInfo<Collect> selectPage(Collect collect,int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Collect> list = collectMapper.selectAll(collect);
    return PageInfo.of(list);
    }

}