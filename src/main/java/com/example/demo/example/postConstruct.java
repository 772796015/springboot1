package com.example.demo.example;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 此类验证@PostConstruct注解在项目启动时加载相应方法
 */
@Component
public class postConstruct {

    @PostConstruct//此注解在启动时加载
    public void init(){//方法名随便写
        System.out.println("-----------初始化加载的啊啊啊啊----------");
    }
}
