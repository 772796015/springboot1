package com.example.demo.thread;

/*
此处实现了Runnable接口,使用了synchronized (this)。 synchronized (ob)的使用可以参考Station

原则：Synchronized(this)锁当前对象，两个不同线程持有同一个this执行会锁掉一个
Synchronized(obj)锁Obj对象,this本身就是一个Object对象，而有时候两个线程持有的this本身就不同，你又想同步，可以通过新建一个两个线程共同拥有的对象锁住


 */
public class Station1 implements Runnable {

    // 为了保持票数的一致，票数要静态
    static int tick = 20;

    // 重写run方法，实现买票操作
    @Override
    public void run() {
        while (tick > 0) {
            synchronized (this) {// 对象锁
                if (tick > 0) {
                    System.out.println( Thread.currentThread().getName()+"卖出了第" + tick + "张票");
                    tick--;
                } else {
                    System.out.println("票卖完了");
                }
            }
            try {
                Thread.sleep(1000);//休息一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    /*
    原则：Synchronized(this)锁当前对象，两个不同线程持有同一个this执行会锁掉一个
Synchronized(obj)锁Obj对象,this本身就是一个Object对象，而有时候两个线程持有的this本身就不同，你又想同步，可以通过新建一个两个线程共同拥有的对象锁住
     */
    public static void main(String[] args) {
        //此处只new了一个对象，然后多个线程同时操作同一个对象，所以synchronized (this)可以锁住。
        Station1 mt=new Station1();
        Thread t1=new Thread(mt);
        t1.setName("黄牛A");
        t1.start();

        Thread t2=new Thread(mt);
        t2.setName("黄牛B");
        t2.start();

        Thread t3=new Thread(mt);
        t3.setName("黄牛C");
        t3.start();
    }
}
