package com.cjj.demo.socketdemo0524;

import java.util.LinkedList;
import java.util.Queue;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/24
 * Time:16:03
 */
public class Message {

    private static Queue<Object> queue = new LinkedList<>();

    private static int SIZE = 2;

    public void add(String msg){
        try{
            // 当前信息数量已等于队列最大值
            if(queue.size() == SIZE){
                System.out.println("信息已满，等待消费");
                wait();
            }
            queue.add(msg);
            notify();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Object poll(){
        try{
            // 当前信息为空，等待生产
            if(queue.size() == 0){
                System.out.println("没有信息，等待生产");
            }
            Object poll = queue.poll();
            notify();
            return poll;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
