package com.cjj.demo.socketpc;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Queue;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/6
 * Time:10:09
 */
public class ServerListenThread implements Runnable {

    private Socket socket;

    private Message message;

    public ServerListenThread(Socket socket, Message message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while(true){
                message.add(ois.readObject());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
