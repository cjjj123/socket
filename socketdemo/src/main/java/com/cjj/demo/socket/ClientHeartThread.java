package com.cjj.demo.socket;

import com.alibaba.fastjson.JSONObject;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端发心跳包给服务器
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/6
 * Time:12:01
 */
public class ClientHeartThread implements Runnable {

    private Socket heart;

    private ObjectOutputStream oos;

    public ClientHeartThread(Socket heart, ObjectOutputStream oos) {
        this.heart = heart;
        this.oos = oos;
    }


    @Override
    public void run() {
        try {
            System.out.println("心跳包县城已启动");
            while (true){
                Thread.sleep(5000);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", "heart");
                jsonObject.put("msg", "心跳包");
                oos.writeObject(jsonObject);
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
