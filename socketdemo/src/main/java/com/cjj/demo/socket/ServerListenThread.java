package com.cjj.demo.socket;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/6
 * Time:10:09
 */
public class ServerListenThread implements Runnable {

    private Socket server;

    public ServerListenThread(Socket socket) {
        this.server = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
            while(true){
                System.out.println(ois.readObject());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                server.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
