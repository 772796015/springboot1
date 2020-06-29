package com.example.demo.example;

import com.example.demo.service.UserService;
import com.example.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by DJ010199 on 2020/5/21.
 */


@RestController
public class MybatisController {
    @Autowired
    private UserService userService;

    //演示：springboot集成mybatis  浏览器访问地址  localhost:8080/mybatis/5
    @GetMapping(value = "/mybatis/{id}")
    public User findUser(@PathVariable int id){
        User user = userService.findUser(id);
        System.out.println(user.toString());
        return user;
    }

    //实现事务控制。    修改id为1和为5用户的年龄，减5岁，当且仅当两个都修改完成且未抛错，更新成功，sql脚本见resources/document/t_user.sql。
    // 访问地址http://localhost:8082/mybatis/Transactional
    @GetMapping(value = "/mybatis/Transactional")
    public void findUser(){
        userService.updateUser (1,5,5);
    }

}
