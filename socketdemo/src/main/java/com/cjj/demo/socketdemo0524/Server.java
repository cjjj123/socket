package com.cjj.demo.socketdemo0524;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/24
 * Time:17:02
 */
public class Server extends Thread{

    private Socket socket;

    private Message message;

    public Server(Socket socket, Message message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            String msg = reader.readLine();
            if(msg != null){
                // 添加信息（生产）
                if(msg.contains("add")){
                    synchronized (message){
                        message.add(msg);
                    }
                    pw.write("信息添加成功，信息为：" + msg);
                }else if(msg.contains("poll")){
                    String newMsg;
                    synchronized (message){
                        newMsg = (String) message.poll();
                    }
                    pw.write("读取信息成功，信息为：" + newMsg);
                }else{
                    pw.write("错误指令");
                }
            }else{
                System.out.println("传入信息为空");
            }
            pw.flush();
            socket.shutdownOutput();
            pw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Message message = new Message();
            while (true){
                new Server(serverSocket.accept(), message).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
