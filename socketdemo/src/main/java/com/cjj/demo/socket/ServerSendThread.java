package com.cjj.demo.socket;


import com.alibaba.fastjson.JSONObject;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/6
 * Time:10:13
 */
public class ServerSendThread implements Runnable {

    private Socket server;

    public ServerSendThread(Socket socket) {
        this.server = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.print("请输入内容 ：");
                String context = scanner.nextLine();
                JSONObject object = new JSONObject();
                object.put("type", "chat");
                object.put("msg", context);
                oos.writeObject(object);
                oos.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
