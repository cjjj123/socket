package com.cjj.demo.socket0517;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/17
 * Time:18:01
 */
public class ServerMsgAdd extends Thread {

    private Socket socket;

    public ServerMsgAdd(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = reader.readLine();
            System.out.println(s);
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
