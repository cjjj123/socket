package com.cjj.demo.socketpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 服务端
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/7
 * Time:11:08
 */
public class Server {

    // 监听的端口号
    public static final int PORT = 8888;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Message message = new Message();
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerListenThread(socket, message)).start();
                new Thread(new ServerSendThread(socket, message)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
