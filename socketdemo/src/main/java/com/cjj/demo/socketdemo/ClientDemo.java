package com.cjj.demo.socketdemo;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/7
 * Time:10:09
 */
public class ClientDemo {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            // 发送信息给服务器
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            JSONObject object = new JSONObject();
            object.put("type", "chat");
            object.put("msg", "客户端请求信息");
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            // 接收服务器返回的信息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println(objectInputStream.readObject());
            objectOutputStream.close();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
