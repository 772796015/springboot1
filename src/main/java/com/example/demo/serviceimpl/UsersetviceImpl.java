package com.example.demo.serviceimpl;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //复杂参数查询
    public void ComplexParameters(){
        Map<String,Object> map1=new HashMap<String,Object>();
        List nameList =new ArrayList<>();
        nameList.add("张三");
        nameList.add("李思");
        map1.put("nameList",nameList);
        map1.put("age",5);
       List<User> users=  userMapper.ComplexParameters(map1);


    }

    //返回值查询，封装成map
    public List<Map<String,Object>> findUser1(int id){
        return userMapper.findUser1(id);
    }
}
