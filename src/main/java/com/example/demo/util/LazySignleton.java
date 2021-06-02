package com.example.demo.util;

/*
懒汉式单例：只有当调用getInstance的时候，才回去初始化这个单例。
懒汉式本身是非线程安全的，为了实现线程安全加了synchronized锁。
*/
public class LazySignleton {
    private static LazySignleton INSTANCE = null;

    //私有化构造子,阻止外部直接实例化对象
    private LazySignleton(){

    }

    /**
     * <B>方法描述：改造的为线程安全</B>
     * <p style="margin-left:20px;color:#A52A2A;">
     * 获取类的单例实例
     * @return <span style="color: #008080;"> 返回类的唯一实例 </span>
     */
    public static LazySignleton getInstance(){


        if(INSTANCE == null){
            synchronized (LazySignleton.class) {
                if(INSTANCE == null){
                    INSTANCE = new LazySignleton();
                }
            }
        }
        return INSTANCE;
    }

//懒汉式本身是非线程安全的,未加synchronized为非线程安全
/*    public static LazySignleton getInstance(){



                if(INSTANCE == null){
                    INSTANCE = new LazySignleton();
                }

        return INSTANCE;
    }*/




    public static void main(String[] args) {
        System.out.println(LazySignleton.getInstance());
        System.out.println(LazySignleton.getInstance());
    }
}
