package com.example.demo.mapper;

import com.example.demo.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by DJ010199 on 2020/4/17.
 */
@Mapper
public interface  UserMapper {
        User findUser(int id);
        void updateUser(@Param("id")int id, @Param("age")int age);
        List<User> ComplexParameters(Map map1);
}
