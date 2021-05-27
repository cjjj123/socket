package com.cjj.demo.socketpc;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/14
 * Time:16:54
 */
public class ProSend implements Runnable {

    private Socket socket;

    public ProSend(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            JSONObject object = new JSONObject();
            object.put("type", "chat");
            object.put("msg", "生产者生产信息");
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        }catch (Exception e){
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
