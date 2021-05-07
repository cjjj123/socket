package com.cjj.demo.producerconsumer;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/6
 * Time:16:50
 */
public class MainDemo {


    public static void main(String[] args) {
        Baozi baozi = new Baozi();
        Consumer consumer = new Consumer(baozi);
        Producter producter = new Producter(baozi);
        new Thread(producter).start();
        new Thread(consumer).start();

    }
}
