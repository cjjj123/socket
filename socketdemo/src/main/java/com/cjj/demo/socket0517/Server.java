package com.cjj.demo.socket0517;

import com.cjj.demo.socketdemo0517.Buffer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/17
 * Time:17:35
 */
public class Server extends Thread{

    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = reader.readLine();
            System.out.println(msg);
            Buffer.getIntance().produce(msg);
            String consumeMsg = (String) Buffer.getIntance().consume();
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.write("服务器收到信息，信息为：" + consumeMsg);
            pw.flush();
            socket.shutdownOutput();
            pw.close();
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true){
                new Server(serverSocket.accept()).start();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
