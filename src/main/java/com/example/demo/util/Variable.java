package com.example.demo.util;

import com.example.demo.vo.User;
//变量作用域例子：  全局变量：随着对象创建到对象死亡。局部变量：作用域最近括号内。静态变量：从程序开始到程序结束。

//下面演示全局变量例子
public class Variable {
    int a;
    User user1=new User();

    public int add(){
        return a=10;
    }
    public int add1(){
        return a=20+a;
    }



    public void add2(){
        user1.setPhone("555");
    }
    public void add3(){
        user1.setAddress("8888");
        System.out.println(user1.getPhone()+"---"+user1.getAddress());
    }


    public static void main(String[] args) {
        Variable t=new Variable();
        System.out.println(t.add());
        System.out.println(t.add1());



        t.add2();
        t.add3();
    }
}
