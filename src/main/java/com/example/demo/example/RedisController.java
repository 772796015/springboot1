package com.example.demo.example;

import com.example.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/** 集成redis使用例子     get访问地址：localhost:8082/redis
 * Created by DJ010199 on 2020/6/11.
 */
@RestController
public class RedisController {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @RequestMapping("/redis")
    public String getKeyValue(){
        //操作对象
        User user =new User();
        user.setAge(20);
        user.setId(1);
        redisTemplate.opsForValue().set("user",user);
        User value1=(User)redisTemplate.opsForValue().get("user");
        System.out.println(value1);

        //演示git提交分支合并

        //key*遍历key
        Set<Object> keys1=redisTemplate.keys("myKey*");
        for (Object key:keys1) {
            System.out.println("遍历所有key------"+(String) redisTemplate.opsForValue().get(key.toString()));
        }

        //操作字符串
        redisTemplate.opsForValue().set("myKey","myValue");
        String value=(String)redisTemplate.opsForValue().get("myKey");
        System.out.println(value);
        return  value;
    }
}
