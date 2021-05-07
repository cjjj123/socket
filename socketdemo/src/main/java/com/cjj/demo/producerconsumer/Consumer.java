package com.cjj.demo.producerconsumer;

/**
 * 消费者
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/6
 * Time:16:24
 */
public class Consumer implements Runnable {

    private Baozi baozi;

    public Consumer(Baozi baozi) {
        this.baozi = baozi;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9999; i++) {
            try {
                int eat = baozi.eat();
                System.out.println("吃掉第" + eat + "个包子");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
