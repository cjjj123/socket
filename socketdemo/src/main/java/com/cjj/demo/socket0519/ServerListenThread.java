package com.cjj.demo.socket0519;



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            String context;
            while((context = ois.readLine()) != null){
                    message.produce((String) ois.readObject());
                    objectOutputStream.writeObject("服务器收到信息");
                    objectOutputStream.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
