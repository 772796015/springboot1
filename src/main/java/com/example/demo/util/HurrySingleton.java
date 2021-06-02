package com.example.demo.util;
//饿汉就是类一旦加载，就把单例初始化完成，保证getInstance的时候，单例是已经存在的了。
/*是否多线程安全：是
* 饿汉式天生就是线程安全的，可以直接用于多线程而不会出现问题，
*
*
* */
public class HurrySingleton {

    //static保证内存只存一份
    private static final HurrySingleton INSTANCE = new HurrySingleton();
    //私有化构造子,阻止外部直接实例化对象，只能通过调用下面getInstance方法创建实例
    private HurrySingleton(){

    }

    /**
     * <B>方法描述：</B>
     * <p style="margin-left:20px;color:#A52A2A;">
     * 获取类的单例实例
     * @return <span style="color: #008080;"> 返回类的唯一实例 </span>
     */
    public static HurrySingleton getInstance(){

        return INSTANCE;
    }

    public void prt(){
        System.out.println("你好");
    }

    public static void main(String[] args) {
        HurrySingleton  HurrySingleton1= HurrySingleton.getInstance();
        HurrySingleton  HurrySingleton2= HurrySingleton.getInstance();
        System.out.println(HurrySingleton1==HurrySingleton2);//true，说明只产生了一个实例
        HurrySingleton1.prt();
    }
}


