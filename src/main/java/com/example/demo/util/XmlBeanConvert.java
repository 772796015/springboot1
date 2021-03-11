package com.example.demo.util;

import com.example.demo.vo.ClassA;
import com.example.demo.vo.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;



//参考文章:http://www.bubuko.com/infodetail-3272729.html
public class XmlBeanConvert {

        public static void main(String[] args) {

            //转换后的格式
          /*  <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<classA>
    <file1>hello string</file1>
    <file2>hello int</file2>
    <user>
        <address>北京</address>
        <age>10</age>
        <id>1</id>
        <name>张三</name>
        <phone>154444</phone>
    </user>
</classA>*/



            User user=new User();
            user.setId(1);
            user.setAge(10);
            user.setName("张三");
            user.setAddress("北京");
            user.setPhone("154444");
            // bean转换成xml
            ClassA a=new ClassA();
            a.setFile1("hello string");
            a.setFile2("hello int");
            a.setUser(user);

            System.out.println(a);
            try {
                JAXBContext context = JAXBContext.newInstance(ClassA.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.marshal(a,System.out);
            } catch (JAXBException e) {
                e.printStackTrace();
            }






            printLine();//分割两个方法输出结果，便于观察

            // xml转换成bean,类似于开发过程中webservice传来的XML格式数据（其实就是String类型，直接String接收就行），然后转换。
            String xmlStr="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><classA><file1>hello string</file1><file2>hello int</file2><user><address>北京</address><age>10</age><id>1</id><name>张三</name><phone>154444</phone></user></classA>";
            try {
                JAXBContext context = JAXBContext.newInstance(ClassA.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                ClassA a2 = (ClassA) unmarshaller.unmarshal(new StringReader(xmlStr));
                System.out.println(a2);
            } catch (JAXBException e) {
                e.printStackTrace();
            }

        }
        public static void printLine(){
            System.out.println("===============================");
        }

}
