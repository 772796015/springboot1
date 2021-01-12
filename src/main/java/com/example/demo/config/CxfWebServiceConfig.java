package com.example.demo.config;

import com.example.demo.webService.HelloWebservice;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfWebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private HelloWebservice helloWebservice;

    /**
     * 配置cxf服务发布，默认服务在host:port/services/* 路径下
     * @return
     */
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, helloWebservice);
        endpoint.publish("/helloWebservice");//接口发布在/helloWebservice目录下，就是访问路径，localhost:8088/services/helloWebservice
        return endpoint;
    }
}
