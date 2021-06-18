package com.example.demo.util;

import com.example.demo.vo.User;

public class Fanxing {
        public static void main(String[] args) {
            User user1=new User();
            Fanxing f=new Fanxing();
            User x2 = (User)f.get(User.class,user1);
            System.out.println(x2);
        }

        public   Object get(Class<?> clz,Object o){//cla 传进来就是个类,可以使用类的相关方法,而不是user可用的方法， clz.getid()是用不了的
            if(clz.isInstance(o)){
                return clz.cast(o);
            }
            return null;
        }
}
