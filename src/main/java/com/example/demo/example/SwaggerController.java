package com.example.demo.example;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示get请求  postman上访问 url：localhost:8080/hello/5
 * Created by DJ010199 on 2020/4/9.
 */





@Api(tags = "获取用户id接口")
@RestController
 public class SwaggerController {

    @ApiOperation(value="获取用户信息", notes="根据id来获取用户详细信息")
    @ApiImplicitParam(name="id", value="用户ID", required=true, dataType="String")
    @RequestMapping(value = "/hello/{id}")
    public String helloSwagger(@PathVariable  int id){
       return "swagger-----"+id;
    }
}
