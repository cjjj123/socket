package com.cjj.demo.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/6
 * Time:16:26
 */
public class Baozi {

    private Queue<Integer> queue = new LinkedList<>();

    private int size = 5;

    /**
     * 生产包子
     * @param
     */
    public synchronized void make(int val) throws InterruptedException {
        if(queue.size() == size){
            System.out.println("包子太多了，等待消费");
            wait();
        }
        // 包子不够，要生产
        queue.add(val);
        notify();
    }

    /**
     * 吃包子
     * @return
     */
    public synchronized int eat() throws InterruptedException {
        if(queue.size() == 0){
            System.out.println("没有包子，等待生产");
            wait();
        }
        Integer poll = queue.poll();
        Thread.sleep((long) (Math.random() * 1000));
        notify();
        return poll;
    }

}
