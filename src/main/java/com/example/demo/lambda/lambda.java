package com.example.demo.lambda;

import java.util.ArrayList;
import java.util.List;

public class lambda {

    public static void main(String[] args) {

/*    针对上述基础语法的精简
1.参数类型精简*/
        /**
         * 语法精简
         * 1.参数类型
         * 由于在接口的抽象方法中，已经定义了参数的数量类型 所以在Lambda表达式中参数的类型可以省略
         * 备注：如果需要省略类型，则每一个参数的类型都要省略，千万不要一个省略一个不省略
         */
/*    LambdaNoneReturnMutipleParmeter lambda1=(int a,int b)-> {
        System.out.println("hello world");
    };*/
        //可以精简为:
        LambdaNoneReturnMutipleParmeter lambda1=(a,b)-> {
            System.out.println("hello world");
        };
       lambda1.test(5,6);
//2.参数小括号精简
        /**
         * 2.参数小括号
         * 如果参数列表中，参数的数量只有一个 此时小括号可以省略
         */
/*    LambdaNoneReturnSingleParmeter lambda2=(a)->{
        System.out.println("hello world");
    };*/
        //可以精简为:
        LambdaNoneReturnSingleParmeter lambda2= a->{
            System.out.println("hello world");
        };
//3.方法大括号精简
        /**
         * 3.方法大括号
         * 如果方法体中只有一条语句，此时大括号可以省略
         */
/*    LambdaNoneReturnSingleParmeter lambda3=a->{
        System.out.println("hello world");
    };*/
        //可以精简为:
        LambdaNoneReturnSingleParmeter lambda3=a->System.out.println("hello world");
//4.大括号精简补充
        /**
         * 4.如果方法体中唯一的一条语句是一个返回语句
         * 贼省略大括号的同时 也必须省略return
         */
/*    LambdaSingleReturnNoneParmeter lambda4=()->{
        return 10;
    };*/
        // 可以精简为:
        LambdaSingleReturnNoneParmeter lambda4=()->10;
//5.多参数，有返回值 精简
/*    LambdaSingleReturnNoneParmeter lambda5=(a,b)->{
        return a+b;
    };*/
        //   可以精简为:
        LambdaSingleReturnMutipleParmeter lambda5=(a,b)->a+b;




        //6 foreach用法
        List<String> k=new ArrayList<>();
        k.add("1");
        k.add("2");
        k.forEach(s-> System.out.println(s));

    }






}
