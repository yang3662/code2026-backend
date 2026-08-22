package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll(String name);

    void deleteById(Integer id);

    void insert(User user);


    User selectByUsername(String username);

    void updateById(User user);


    User selectById(int id);
}