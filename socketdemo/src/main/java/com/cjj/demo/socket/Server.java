package com.cjj.demo.socket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/4/27
 * Time:10:25
 */
public class Server {

    // 监听的端口号
    public static final int PORT = 8888;

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }

    private void init() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("启动服务器");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerListenThread(socket)).start();
                new Thread(new ServerSendThread(socket)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }


    }
}
