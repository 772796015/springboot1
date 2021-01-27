package com.example.demo.example;

import com.example.demo.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    //具体实现可参考文章：https://blog.csdn.net/qq_37976540/article/details/105600894
    // 自定义注解示例 postman上访问 url：http://localhost:8080/logController/123?name=zhang  输入以上信息后访问即可
    @Log("-------------------自定义注解实现了!!!------------")
    @GetMapping("/logController/{id}")
    public String demo(@PathVariable(name = "id") String id, @RequestParam(name = "name") String name) {
        System.out.println("id="+id);
        System.out.println("name="+name);
        return id+"------"+name;
    }

}
