package com.cjj.demo.socketdemo0511;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/11
 * Time:19:09
 */
public class Server {

    public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(0511);
            System.out.println("启动服务器");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            String context;
                            while((context = bufferedReader.readLine()) != null){
                                System.out.println("接收到客户端请求的信息：" + context);
                                bufferedWriter.write("haha");
                                bufferedWriter.flush();
                                socket.shutdownOutput();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
    }
}
