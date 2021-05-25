package com.example.demo.thread;

//信号灯法：通过标志位解决
public class TestPC2 {
    public static void main(String[] args) {
        Show show =  new Show();
        new Actor(show).start();
        new Audience(show).start();
    }
}
//生产者-->演员
class Actor extends Thread {
    Show show;
    public Actor (Show show) {
        this.show = show;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i%2 == 0) {
                this.show.record("这就是街舞");
            }else {
                this.show.record("青春有你");
            }
        }
    }
}
//消费者-->观众
class Audience extends Thread{
    Show show;
    public Audience (Show show) {
        this.show = show;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            show.watch();
        }
    }
}
//产品 -->电视节目
class Show extends Thread {
    //演员录制，观众等待
    //观众观看，演员等待
    String program;//录制的节目
    boolean flag  = true;
    //节目录制
    public synchronized void record(String program) {
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员录制了："+program);
        //通知观众观看
        this.notifyAll();
        this.program = program;
        this.flag = !this.flag;
    }
    //观众观看
    public synchronized void watch(){
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了-->"+program);
        //通知演员录制：
        this.notifyAll();
        this.flag = !this.flag;
    }
}
