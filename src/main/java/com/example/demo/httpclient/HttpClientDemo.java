package com.example.demo.httpclient;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpClientDemo {
    /**
     * GET---无参测试
     *
     * @date 2018年7月13日 下午4:18:50
     */
    public void doGetTestOne() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://localhost:8088/doGetControllerOne");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * GET---有参测试 (方式一:手动在url后面加上参数)
     *
     * @date 2018年7月13日 下午4:19:23
     */
    public void doGetTestWayOne() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        StringBuilder params = new StringBuilder();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("name=").append(URLEncoder.encode("&", "utf-8"));
            params.append("&");
            params.append("age=24");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://localhost:8088/doGetControllerTwo" + "?" + params);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                // 主动设置编码，来防止响应乱码
                String responseStr = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                System.out.println("响应内容为:" + responseStr);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * GET---有参测试 (方式二:将参数放入键值对类中,再放入URI中,从而通过URI得到HttpGet实例)
     *
     * @date 2018年7月13日 下午4:19:23
     */
    public void doGetTestWayTwo() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("name", "&"));
            params.add(new BasicNameValuePair("age", "18"));
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("http").setHost("localhost")
                    .setPort(8088).setPath("/doGetControllerTwo")
                    .setParameters(params).build();
            System.out.println("url------------"+uri);
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        // 创建Get请求
        HttpGet httpGet = new HttpGet(uri);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * POST---无参测试
     *
     * @date 2018年7月13日 下午4:18:50
     */
    public void doPostTestOne() {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost("http://localhost:8088/doPostControllerOne");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * POST---有参测试(对象参数)
     *
     * @date 2018年7月13日 下午4:18:50
     */
    public void doPostTestTwo() {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost("http://localhost:8088/doPostControllerTwo");
        User user = new User();
        user.setName("潘晓婷");
        user.setAge(18);
        user.setGender("女");
        user.setMotto("姿势要优雅~");
        // 我这里利用阿里的fastjson，将Object转换为json字符串;
        // (需要导入com.alibaba.fastjson.JSON包)
        String jsonString = JSON.toJSONString(user);


        StringEntity entity = new StringEntity(jsonString, "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        //此处声明请求内容类型为application/json,所以需要传json字符串
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * POST---有参测试(项目中实战解析)
     *
     * @date 2018年7月13日 下午4:18:50
     */

    /**请求报文
     * {
     *     "Header":{
     *         "SeriaNo":"XXXX000000000000001",
     *         "TransCode":"Risk001",
     *         "TransDate":"2020-01-01",
     *         "TransTime":"19:00:00",
     *         "SysCode":"B005"
     *     },
     *     "ProductInfoReq":{
     *          "PackageCode":"PKL11208"
     *     }
     *
     * }
     */

    /**
     * 响应报文
     * {
     *     "Header":{
     *         "RetCode":"0000",
     *         "RetMsg":"成功",
     *         "TransDate":"2020-01-01",
     *         "TransTime":"19:00:00",
     *         "SeriaNo":"XXXX000000000000001",
     *         "TransCode":"XXXX"
     *     },
     *     "ProductInfoResp":{
     *         "ResultFlag":"1",
     *         "Reason":"成功",
     *         "PackageCodeList":[{
     *            "PackageCode":"PKL11209",
     *            "PackageName":"大家爱家重大疾病保险",
     *            "PlanInfoList":[{
     *              "PlanCode":"PKL11209",
     *              "PlanName":"大家爱家重大疾病保险",
     *              "ProductInfoList":[
     *               {
     *                 "ProductCode":"L11209",
     *                 "ProductName":"大家爱家重大疾病保险"
     *               },{
     *                 "ProductCode":"L11211",
     *                 "ProductName":"大家附加两全保险"
     *               },{
     *                 "ProductCode":"L11201",
     *                 "ProductName":"大家附加投保人豁免保险费重大疾病保险"
     *               }
     *            ]
     *      }]
     *    }]
     *     }
     * }
     */
    public void doPostTestTwoActual () {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost("http://10.219.112.24:58929/msfrest/json");
      /*  User user = new User();
        user.setName("潘晓婷");
        user.setAge(18);
        user.setGender("女");
        user.setMotto("姿势要优雅~");*/
        // 我这里利用阿里的fastjson，将Object转换为json字符串;
        // (需要导入com.alibaba.fastjson.JSON包)
     /*   String jsonString = JSON.toJSONString(user);*/


        //请求头封装为对象 start
        RequsetHeader requsetHeader=new RequsetHeader();
        Header header=new Header();
        header.setSeriaNo("XXXX000000000000001");
        header.setTransCode("Risk001");
        header.setTransDate("2020-01-01");
        header.setTransTime("19:00:00");
        header.setSysCode("B005");
        requsetHeader.setHeader(header);
        String jsonString = JSON.toJSONString(requsetHeader);
        //请求头封装为对象 end



   //     String jsonString ="{\"Header\":{\"SeriaNo\":\"XXXX000000000000001\",\"TransCode\":\"Risk001\",\"TransDate\":\"2020-01-01\",\"TransTime\":\"19:00:00\",\"SysCode\":\"B005\"}}";

        StringEntity entity = new StringEntity(jsonString, "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        //此处声明请求内容类型为application/json,所以需要传json字符串
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
/*
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
*/
                //响应内容做处理 start
               String responseStr=EntityUtils.toString(responseEntity);
                JSONObject data = JSONObject.parseObject(responseStr);
                JSONArray JPackageCodeList= data.getJSONObject("ProductInfoResp").getJSONArray("PackageCodeList");
                for(int i = 0; i < JPackageCodeList.size(); i++){
                    JSONObject jsonObj = JPackageCodeList.getJSONObject(i);
                    JSONArray jPlanInfoList = jsonObj.getJSONArray("PlanInfoList");
                    for(int k = 0; k < jPlanInfoList.size(); k++){
                        JSONObject jsonObj2=jPlanInfoList.getJSONObject(k);
                        JSONArray jProductInfoList= jsonObj2.getJSONArray("ProductInfoList");
                        for(int j = 0; j < jProductInfoList.size(); j++){
                            JSONObject jsonObj1=jProductInfoList.getJSONObject(j);
                            String productName= jsonObj1.getString("ProductName");
                            String productCode= jsonObj1.getString("ProductCode");
                            System.out.println(productCode+"----"+productName);
                        }
                    }
                }
                //响应内容做处理 end
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public void dopost(){
        //请求头封装为对象 start
        RequsetHeader requsetHeader=new RequsetHeader();
        Header header=new Header();
        header.setSeriaNo("XXXX000000000000001");
        header.setTransCode("Risk001");
        header.setTransDate("2020-01-01");
        header.setTransTime("19:00:00");
        header.setSysCode("B005");
        requsetHeader.setHeader(header);
        //请求头封装为对象 end
        String str=JSONObject.toJSONString(requsetHeader);

        HttpResponse httpResponse = HttpRequest.post("http://10.219.112.24:58929/msfrest/json").header("header", "application/json").body(str)
                .execute();
        //System.out.println("权益传承回调接口返回报文：" + httpResponse);
        if (HttpStatus.HTTP_OK == httpResponse.getStatus()) {
            String body = httpResponse.body();

        } else {

        }




    }





    /**
     * POST---有参测试(基本参数 + 对象参数)
     *
     * @date 2018年7月13日 下午4:18:50
     */
    public void doPostTestThree() {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("flag", "4"));
            params.add(new BasicNameValuePair("meaning", "这是什么鬼？"));
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8088)
                    .setPath("/doPostControllerThree").setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        HttpPost httpPost = new HttpPost(uri);
        // HttpPost httpPost = new
        // HttpPost("http://localhost:12345/doPostControllerThree1");

        // 创建user参数
        User user = new User();
        user.setName("潘晓婷");
        user.setAge(18);
        user.setGender("女");
        user.setMotto("姿势要优雅~");

        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(user), "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * POST---有参测试(基本参数)
     *
     * @date 2018年7月13日 下午4:18:50
     */
    public void doPostTestFour() {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        StringBuilder params = new StringBuilder();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("name=").append(URLEncoder.encode("&", "utf-8"));
            params.append("&");
            params.append("age=24");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // 创建Post请求
        HttpPost httpPost = new HttpPost("http://localhost:8088/doPostControllerFour" + "?" + params);

        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        //httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        HttpClientDemo ht=new HttpClientDemo();
        //ht.doGetTestOne(); //get无参
        //ht.doGetTestWayOne();//get 有参一
        //ht.doGetTestWayTwo(); //get 有参二
        //ht.doPostTestOne();//post 无参
       // ht.doPostTestTwo();//post 有参一
        //ht.doPostTestThree();
/*
        ht.doPostTestFour();
*/
        ht.dopost();



      

    }
}
