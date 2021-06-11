package com.example.demo.util;

public class Fanxing2 {
    public static void main(String[] args) {
        Object x = "abc";
        String x2 = get(String.class,x);
        System.out.println(x2);
    }

    public static  <T> T get(Class<T> clz,Object o){// cla 传进来就是个类,可以使用类的相关方法
        if(clz.isInstance(o)){
            return clz.cast(o);
        }
        return null;
    }

}

