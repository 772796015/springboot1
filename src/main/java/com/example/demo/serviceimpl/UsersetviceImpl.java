package com.example.demo.serviceimpl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by DJ010199 on 2020/5/20.
 */
@Service
public class UsersetviceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(int id) {
        return userMapper.findUser(id);
    }
}
