package com.example.demo.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 集成redis使用例子     get访问地址：localhost:8082/redis
 * Created by DJ010199 on 2020/6/11.
 */
@RestController
public class RedisController {
    @Autowired
    private RedisTemplate<String,String>redisTemplate;

    @RequestMapping("/redis")
    public String getKeyValue(){
        redisTemplate.opsForValue().set("myKey","myValue");
        String value=(String)redisTemplate.opsForValue().get("myKey");
        System.out.println(value);
        return  value;
    }
}
