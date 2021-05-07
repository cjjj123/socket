package com.cjj.demo.socket;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/4/27
 * Time:10:25
 */
public class Client {

    private static Socket socket;

    public static boolean connecttion_state = false;

    private static ObjectOutputStream oos;

    public static final String IP = "localhost";// 服务器地址
    public static final int PORT = 8888;// 服务器端口号

    public static void main(String[] args) {
        while (!connecttion_state){
            connect();
        }
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void connect() {
        try {
            // 实例化一个Socket，并指定服务器地址和端口
            socket = new Socket(IP, PORT);
            connecttion_state = true;
            if(connecttion_state){
                oos = new ObjectOutputStream(socket.getOutputStream());
                new Thread(new ClientReadThread(socket)).start();
                new Thread(new ClientWriteThread(socket, oos)).start();
                new Thread(new ClientHeartThread(socket, oos)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            connecttion_state = false;
        }
    }

    public static void reconnect(){
        while (!connecttion_state){
            System.out.println("正在尝试重新连接...");
            connect();
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}