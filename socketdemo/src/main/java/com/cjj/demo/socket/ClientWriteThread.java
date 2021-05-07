package com.cjj.demo.socket;




import com.alibaba.fastjson.JSONObject;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/5
 * Time:18:47
 */
public class ClientWriteThread implements Runnable{

    private Socket client;

    private ObjectOutputStream oos;

    public ClientWriteThread(Socket client, ObjectOutputStream oos) {
        this.client = client;
        this.oos = oos;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true){
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
            Client.connecttion_state = false;
            // 重新连接
            Client.reconnect();
        }
    }
}
