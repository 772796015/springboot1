package com.example.demo.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示postman post请求示例
 * Created by DJ010199 on 2020/5/21.
 */

// post请求示例  postman上访问 url：localhost:8080/test1，params栏填 key：username，value：111.key：password，value：222    输入以上信息后访问即可
@RestController
public class PostController {
    private Logger logger = LoggerFactory.getLogger(PostController.class);


@PostMapping(value = "/test1")
public String test1(@RequestParam(name = "username") String username,
                    @RequestParam(name = "password") String password) {
    logger.info(username+""+password);
    System.out.println(username+""+password);
    return "成功";
}
}
