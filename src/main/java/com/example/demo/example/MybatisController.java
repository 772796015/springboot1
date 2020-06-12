package com.example.demo.example;

import com.example.demo.service.UserService;
import com.example.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示：springboot集成mybatis  浏览器访问地址  localhost:8080/mybatis/5
 * Created by DJ010199 on 2020/5/21.
 */


@RestController
public class MybatisController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/mybatis/{id}")
    public User findUser(@PathVariable int id){
        User user = userService.findUser(id);
        System.out.println(user.toString());
        return user;
    }
}
