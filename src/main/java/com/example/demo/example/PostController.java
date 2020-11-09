package com.example.demo.example;

import com.example.demo.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示postman post请求示例 详见聚合项目2.3章节
 * Created by DJ010199 on 2020/5/21.
 */

@RestController
public class PostController {
    private Logger logger = LoggerFactory.getLogger(PostController.class);

// post请求示例  postman上访问 url：localhost:8080/test1，params栏填 key：username，value：111.key：password，value：222    输入以上信息后访问即可
@PostMapping(value = "/test1")
public String test1(@RequestParam(name = "username") String username,
                    @RequestParam(name = "password") String password) {
    logger.info(username+""+password);
    System.out.println(username+""+password);
    return "success";
}

    // post请求示例  类似于表单提交 postman上访问 url：localhost:8080/testVO，
    // params栏填 key：id，value：1.key：name，value：张三 .key：age，value：1.key：address，value：1 . key：phone，value：18510808831
    //以上就是实体类User属性，输入以上信息后访问即可
    @PostMapping(value = "/testVO")
    public String testVO(User user) {
        logger.info(user.getName()+"---"+user.getPhone());
        System.out.println(user.getName()+"---"+user.getPhone());
        return "success";
    }

    //  post请求示例  举例：ajaxjson串请求 postman上访问 url：localhost:8080/testJson，
    //body页签选择raw模式，数据类型选择json（application/json），  传参：{	"id":"1",	"name":"张三",	"age":"10",	"address":"20",	"phone":"18510808831"}

    @PostMapping(value = "/testJson")
    public String testJson(@RequestBody User user) {
        logger.info(user.getName()+"---"+user.getPhone());
        System.out.println(user.getName()+"---"+user.getPhone());
        return "success";
    }

}
