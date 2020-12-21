package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.vo.ActionVo;
import com.example.demo.vo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参考文档https://blog.csdn.net/xuforeverlove/article/details/80842148
 *
 * 要引入pom文件
 *<dependency>
 *     <groupId>com.alibaba</groupId>
 *     <artifactId>fastjson</artifactId>
 *     <version>1.2.47</version>
 * </dependency>
 *
 */
public class Json {
    public static void main(String[] args) {
        //注意：JSONObject继承JSON 所以JSON.parseObject等同于JSONObjectparseObject,JSONObject.toJSONString等同于JSON.toJSONString


        //1.JAVA对象,map等转JSON字符串
        User user = new User();
        user.setId(1);
        user.setName("zhangsan");
        user.setAge(10);
        String s = JSONObject.toJSONString(user);
        System.out.println("-------1.1---------toJsonString()方法：s=" + s);


        Map<String, Object> map = new HashMap<>();
        map.put("id", "111");
        map.put("name", "888");
        map.put("age", "888");
        map.put("address", "888");
        map.put("phone", "888");
        String body = JSONObject.toJSONString(map);//     JSON.toJSONString(map);效果一样JSONObject集成了JSON
        System.out.println("----------1.2------------toJsonString()方法：body=" + body);

        //2json字符串转json对象
        String s1 = "{\"action\":\"add\",\"id\":\"1\",\"ordinal\":8,\"organUnitFullName\":\"testJSON\",\"parent\":\"0\",\"suborderNo\":\"58961\"}";

        JSONObject jsonObject = JSONObject.parseObject(s1);
        String action = jsonObject.getString("action");
        String id = jsonObject.getString("id");
        System.out.println("action =" + action);//add
        System.out.println("id =" + id);//1
        System.out.println("------2.1--------jsonObject =" + jsonObject);
        //action =add
        //id =1
        //jsonObject ={"parent":"0","organUnitFullName":"testJSON","action":"add","id":"1","suborderNo":"58961","ordinal":8}


        //3复杂JSON格式字符串与JSONObject之间的转换
        System.out.println("*********************************3start*******************************************************");

        String str3 ="{\"meta\":{\"code\":\"0\",\"message\":\"同步成功!\"},\"data\":{\"orderno\":\"U_2018062790915774\",\"suborderno\":\"SUB_2018062797348039\",\"type\":\"organunit\",\"result\":{\"organunit\":{\"totalCount\":2,\"successCount\":0,\"failCount\":2,\"errors\":[{\"code\":\"UUM70004\",\"message\":\"组织单元名称不能为空888\",\"data\":{\"id\":\"254\",\"suborderNo\":\"SUB_2018062797348039\",\"organUnitType\":\"部门\",\"action\":\"add\",\"parent\":\"10000\",\"ordinal\":0,\"organUnitFullName\":\"组织单元全称\"},\"success\":false},{\"code\":\"UUM70004\",\"message\":\"组织单元名称不能为空\",\"data\":{\"id\":\"255\",\"suborderNo\":\"SUB_2018062797348039\",\"organUnitType\":\"部门\",\"action\":\"add\",\"parent\":\"10000\",\"ordinal\":0,\"organUnitFullName\":\"组织单元全称\"},\"success\":false}]},\"role\":{\"totalCount\":0,\"successCount\":0,\"failCount\":0,\"errors\":[]},\"user\":{\"totalCount\":0,\"successCount\":0,\"failCount\":0,\"errors\":[]}}}}";
        JSONObject jsonObject3 = JSONObject.parseObject(str3);
        JSONObject data3 = jsonObject3.getJSONObject("data");
        JSONObject result3 = data3.getJSONObject("result");

        String organunit3 = result3.getString("organunit");
        System.out.println(organunit3);
        JSONObject organunitObj = result3.getJSONObject("organunit");

        JSONArray errors3 = organunitObj.getJSONArray("errors");

        List<Error> error = JSONObject.parseArray(errors3.toJSONString(), Error.class);//把字符串转换成集合
        for (Error e3 : error) {
            System.out.println(e3.getMessage());
        }
        System.out.println("*********************************3end*******************************************************");







        //4json字符串转java简单对象
        String s2 = "{\"action\":\"add\",\"id\":\"1\",\"ordinal\":8,\"organUnitFullName\":\"testJSON\",\"parent\":\"0\",\"suborderNo\":\"58961\"}";
        ActionVo actionVo = JSONObject.parseObject(s2, ActionVo.class);
        System.out.println("----4.1-----------ActionVo" + actionVo.toString());
        System.out.println("----4.2------------action=" + actionVo.getAction() + "---id=" + actionVo.getId());
        //data对象Data{id='1', suborderNo='58961', organUnitType='null', action='add', parent='0', organUnitFullName='testJSON', ordinal=8}




        //5 数组类型与JAVA对象的转换
        System.out.println("*********************************5start*******************************************************");
        String str = "{\"actionVo\":[{\"action\":\"add\",\"id\":\"1\",\"ordinal\":8,\"organUnitFullName\":\"testJSON\",\"parent\":\"0\",\"suborderNo\":\"58961\"},{\"action\":\"update\",\"id\":\"2\",\"ordinal\":9,\"organUnitFullName\":\"testJSON2\",\"parent\":\"1\",\"suborderNo\":\"888888\"}]}";
        //获取jsonobject对象
        JSONObject jsonObject1 = JSONObject.parseObject(str);
        //把对象转换成jsonArray数组
        JSONArray actionVo1 = jsonObject1.getJSONArray("actionVo");
        String jsonString = JSONObject.toJSONString(actionVo1);//将array数组转换成字符串
        //将字符串转成list集合
        List<ActionVo> actionVos = JSONObject.parseArray(jsonString, ActionVo.class);//把字符串转换成集合
        for (ActionVo e : actionVos) {
            System.out.println("另一种数组转换ActionVo属性=" + e.getAction());
            System.out.println("另一种数组转换ActionVo属性=" + e.getId());
            System.out.println("另一种数组转换ActionVo属性=" + e.getParent());
        }

        System.out.println("*********************************5end*******************************************************");





        //6JAVA对象转JSON对象
        ActionVo actionVo6 = new ActionVo();
        actionVo6.setAction("add");
        actionVo6.setId("1");
        actionVo6.setOrdinal(8L);
        actionVo6.setOrganUnitFullName("testJSON");
        actionVo6.setParent("0");
        actionVo6.setSuborderNo("58961");
        JSONObject jsonObj = (JSONObject)JSONObject.toJSON(actionVo6);
        System.out.println("jsonObj"+jsonObj);


    }

}
