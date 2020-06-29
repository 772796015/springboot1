package com.example.demo.serviceimpl;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //事务：加上@Transactional注解打开事务 ，当程序出现异常后会回滚事务。
   @Transactional
    public void updateUser (int id1, int id2,int age){
        userMapper.updateUser(id1, age); //修改用户1的年龄
          int i = 1/0;  //抛出异常，
        userMapper.updateUser(id2, age); //修改用户2的年龄
    }
}
