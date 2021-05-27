package com.cjj.demo.socketdemo0521;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private static Queue<Object> queue = new LinkedList<Object>();

    private static int INITSIZE = 2;

    private Lock mutex;

    private Condition condition;

    private Buffer(){
        mutex = new ReentrantLock();
        condition = mutex.newCondition();
    }

    public static Buffer getIntance(){
        return QueueBuffer.instance;
    }

    static class QueueBuffer{
        private static Buffer instance = new Buffer();
    }

    public void setInitSize(int size){
        INITSIZE = size;
    }

    public void produce(String msg){
        mutex.lock();
        try {
            while(queue.size() >= INITSIZE ){
                System.out.println("queue wait to consume");
                condition.await();
            }

            queue.offer(msg);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            condition.signalAll();
            mutex.unlock();
        }

    }

    public Object consume(){
        mutex.lock();
        try {
            while (queue.size() == 0) {
                System.out.println("queue wait to produce");
                condition.await();
            }

            return queue.poll();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } finally {
            condition.signalAll();
            mutex.unlock();
        }
    }

    public int getQueueSize(){
        return queue.size();
    }
}