package com.cjj.demo.socket0517;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/17
 * Time:17:35
 */
public class ServerDemo {


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true){
                Socket socket = serverSocket.accept();
                new ServerMsgAdd(socket).start();
                new ServerMsgPoll(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
