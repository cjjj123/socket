package com.cjj.demo.socket;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/5
 * Time:18:12
 */
public class ClientReadThread implements Runnable {

    private Socket client;

    public ClientReadThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            while (true){
                System.out.println(ois.readObject());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
