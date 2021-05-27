package com.cjj.demo.socketpc;

import java.util.LinkedList;
import java.util.Queue;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/7
 * Time:14:35
 */
public class Message {

    private static Queue<Object> queue = new LinkedList<>();

    private final static int size = 5;

    public void add(Object object){
        try {
            if(queue.size() == size){
                System.out.println("信息满了，等待读取");
                wait();
            }
            queue.add(object);
            System.out.println("添加信息成功,信息內容：" + object);
            Thread.sleep((long) (Math.random() * 3000));
            notify();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object send() throws InterruptedException{
            if(queue.size() == 0){
                System.out.println("沒有信息，等待發送");
                wait();
            }
            Object poll = queue.poll();
            System.out.println("發送信息成功,信息內容：" + poll);
            Thread.sleep((long) (Math.random() * 3000));
            notify();
            return poll;
    }
}
