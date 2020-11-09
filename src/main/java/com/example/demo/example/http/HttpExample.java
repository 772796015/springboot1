package com.example.demo.example.http;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * 演示http请求，详细见12HTTP请求章节
 */
public class HttpExample {
        //post请求
        public  void postExample() {
            Map<String,Object> map = new HashMap<>();
            map.put("id", "111");
            map.put("name", "888");
            map.put("age", "888");
            map.put("address", "888");
            map.put("phone", "888");

            String body = JSONObject.toJSONString(map);//请求参数
            String url="http://localhost:5656/testJson";//请求地址
            String result = HttpUtil.post(url, body);
            System.out.println(result);
        }

        //get请求
        public void getExample(){
            String url="http://localhost:5656/getController/123?name=zhang";//请求地址
            String result = HttpUtil.get(url);
            System.out.println(result);
        }


    public static void main(String[] args) {
        HttpExample http=new  HttpExample();
        http.getExample();//get请求
        http.postExample();//post请求
    }
}
