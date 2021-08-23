package com.example.demo.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
    private final static Logger logger = LoggerFactory.getLogger(GetController.class);
    // get请求示例  postman上访问 url：http://localhost:8080/getController/123?name=zhang  输入以上信息后访问即可
    @GetMapping("/getController/{id}")
    public String demo(@PathVariable(name = "id") String id, @RequestParam(name = "name") String name) {
        logger.info("id="+id);
        logger.info("name="+name);
        return id+"------"+name;
    }

}
