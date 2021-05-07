package com.cjj.demo.socketpc;

import com.alibaba.fastjson.JSONObject;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生产者
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/7
 * Time:11:21
 */
public class ProducerClient {

    // 服务器地址
    public static final String IP = "localhost";
    // 服务器端口号
    public static final int PORT = 8888;

    private static int count = 0;

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(10);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(IP, PORT);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    //while (true){
                        for (int i = 0; i < 999999; i++) {
                        JSONObject object = new JSONObject();
                        object.put("type", "chat");
                        object.put("msg", "生产者生产信息" + i);
                        objectOutputStream.writeObject(object);
                        objectOutputStream.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        pool.submit(runnable);
    }
}
