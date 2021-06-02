package com.example.demo.service;

import com.example.demo.vo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by DJ010199 on 2020/5/20.
 */
public interface UserService {
    User findUser(int id);
    void updateUser(int id1, int id2,int age);
    void ComplexParameters();
    List<Map<String,Object>> findUser1(int id);

}
