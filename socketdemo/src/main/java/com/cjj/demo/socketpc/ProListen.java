package com.cjj.demo.socketpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/14
 * Time:16:50
 */
public class ProListen implements Runnable {

    private Socket socket;

    public ProListen(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println(objectInputStream.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
