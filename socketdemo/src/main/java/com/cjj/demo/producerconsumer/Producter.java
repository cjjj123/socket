package com.cjj.demo.producerconsumer;

/**
 * 生产者
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/6
 * Time:16:21
 */
public class Producter implements Runnable {

    private Baozi baozi;

    public Producter(Baozi baozi) {
        this.baozi = baozi;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9999; i++) {
            try {
                baozi.make(i);
                System.out.println("生产第" + i + "个包子");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
