package com.cjj.demo.socketpc;

import com.alibaba.fastjson.JSONObject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/7
 * Time:11:22
 */
public class ConsumerClient {

    // 服务器地址
    public static final String IP = "localhost";
    // 服务器端口号
    public static final int PORT = 8888;

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(10);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(IP, PORT);
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    while (true){
                        System.out.println(objectInputStream.readObject());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        pool.submit(runnable);
    }
}
