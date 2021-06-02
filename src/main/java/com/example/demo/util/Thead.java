package com.example.demo.util;

/**
 * 此类为了验证懒汉模式的线程不安全问题
 */
public class Thead extends Thread{
    @Override
    public void run() {
        System.out.println(LazySignleton.getInstance());
    }

    public static void main(String[] args) {
       new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();
        new Thead().start();

    }
}
