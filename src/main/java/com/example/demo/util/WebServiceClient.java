package com.example.demo.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * 调用webservice接口
 */
public class WebServiceClient {

    private static void client() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8088/services/helloWebservice?wsdl");
        try {
            //invoke(方法名,参数1,参数2,.....)
            Object[] objects = client.invoke("hello","你好啊!!!!");
            //输出调用结果
            System.out.println(objects[0].getClass());
            System.out.println(objects[0].toString());
        } catch (java.lang.Exception e) {
            e.printStackTrace();

        }

    }

    public static void main(String[] args) {
        WebServiceClient.client();
    }
}