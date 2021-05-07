package com.cjj.demo.socketdemo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/7
 * Time:10:06
 */
public class ServerDemo {

    public static void main(String[] args) {
        try {
            System.out.println("启动服务器");
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket accept = serverSocket.accept();
            // 服务器接收客户端发送的信息
            ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream());
            System.out.println(objectInputStream.readObject());
            // 服务器返回信息给客户端
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
            objectOutputStream.writeObject("服务器已收到信息");
            objectOutputStream.flush();
            objectInputStream.close();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
