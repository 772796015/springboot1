package com.example.demo.webService.impl;


import com.example.demo.webService.HelloWebservice;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@WebService(serviceName = "HelloWebservice", // 与接口名称一致
        targetNamespace = "http://webService.demo.example.com",// 与接口中的命名空间一致,一般是接口的包名倒)
        endpointInterface = "com.example.demo.webService.HelloWebservice")//接口类位置

@Component
public class HelloWebServiceImpl implements HelloWebservice {
    @Override
    public String hello(String message) {
        return message;
    }
}
