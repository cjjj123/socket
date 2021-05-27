package com.cjj.demo.socketdemo0511;

import java.io.*;
import java.net.Socket;

/**
 * 客户端A
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/11
 * Time:19:09
 */
public class ClientA {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 0511);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 发送信息
            String context = "你好，这是我第一条信息";
            bufferedWriter.write(context);
            bufferedWriter.flush();
            socket.shutdownOutput();
            // 接收信息
            String result = bufferedReader.readLine();
            System.out.println("服务器返回信息：" + result);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
