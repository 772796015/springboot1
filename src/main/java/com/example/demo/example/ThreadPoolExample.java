package com.example.demo.example;

import com.example.demo.service.ThreadPoolservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//请求地址： http://localhost:8088/threadPoolExample?name=zhangsan   会看到控制台有一个
// 2021-08-23 16:22:08.894  INFO 14972 --- [        hello-1] 线程被启动
@RestController
public class ThreadPoolExample {
    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolExample.class);

    @Autowired
    private  ThreadPoolservice threadPoolservice;
    @GetMapping("/threadPoolExample")
    public void sayHello(@RequestParam(name = "name") String name)  {
        threadPoolservice.sayhello(name);
        logger.info("主线程执行完毕~~~");
    }
}
