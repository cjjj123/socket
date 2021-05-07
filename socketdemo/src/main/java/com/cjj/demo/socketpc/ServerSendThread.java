package com.cjj.demo.socketpc;


import com.alibaba.fastjson.JSONObject;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/6
 * Time:10:13
 */
public class ServerSendThread implements Runnable {

    private Socket socket;

    private Message message;

    public ServerSendThread(Socket socket, Message message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            while(true){
                Object object = message.send();
                oos.writeObject(object);
                oos.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
