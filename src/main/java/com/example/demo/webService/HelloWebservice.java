package com.example.demo.webService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "HelloWebservice", // 与接口名称一致
        targetNamespace = "http://webService.demo.example.com") // 与接口中的命名空间一致,一般是接口的包名倒)
public interface HelloWebservice {
    @WebMethod
   public String hello(String message);
}
