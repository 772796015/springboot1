package com.example.demo.thread;

public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Producer(container).start();
        new Consumer(container).start();
        new Consumer(container).start();
    }
}
//生产者
class Producer extends Thread {
    SynContainer container;
    public Producer(SynContainer container) {
        this.container = container;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
           // System.out.println("生产了第"+i+"号鸡"); //注释原因：println应该放在pop和push方法体里面，因为可能正要打印的时候，线程切换了，要保证增改操作和打印是一气呵成的（原子操作）
            container.push(new Chicken(i));
        }
    }
}
//消费者
class Consumer extends Thread {
    SynContainer container;
    public Consumer(SynContainer container) {
        this.container = container;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
           // System.out.println("消费了--> "+container.pop().id + "号鸡");//注释原因：println应该放在pop和push方法体里面，因为可能正要打印的时候，线程切换了，要保证增改操作和打印是一气呵成的（原子操作）
            container.pop();
        }
    }
}
//产品
class Chicken {
    int id;//编号
    public Chicken(int i) {
        this.id = i;
    }
}
//缓冲区
class SynContainer {
    //需要一个容器大小
    Chicken[] chickens = new Chicken[1];
    //容器计数器
    int count = 0;
    //生产者放入产品
    public synchronized void push(Chicken chicken) {

        //如果容器满了，就需要等待消费者消费
        while (count == chickens.length) {//此处和原文档不一致，不用if这块要用while，因为if语句在醒来的线程中不会再一次判断了，while会，这种wait和notify需要注意
            //通知消费者消费，生产者等待生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没有满，我们就需要丢入产品
        chickens[count] = chicken;
        count=count+1;
        System.out.println("生产了第"+chicken.id+"只鸡");
        //可以通知消费者消费了
        this.notifyAll();
    }
    //消费者消费产品
    public synchronized Chicken pop() {
        //判断能否消费
        while (count == 0) {//此处和原文档不一致，不用if这块要用while，因为if语句在醒来的线程中不会再一次判断了，while会，这种wait和notify需要注意
            //等待生产者生产，消费者等待消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果可以消费
        count=count-1;
        Chicken chicken = chickens[count];
        System.out.println("消费了第"+chicken.id+"只鸡");
        //吃完一个，通知生产者生产
        this.notifyAll();
        return chicken;
    }
}
