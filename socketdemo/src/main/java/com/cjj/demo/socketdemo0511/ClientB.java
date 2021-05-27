package com.cjj.demo.socketdemo0511;

import java.io.*;
import java.net.Socket;

/**
 * 客户端B
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/11
 * Time:19:44
 */
public class ClientB {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 0511);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                String context = bufferedReader.readLine();
                bufferedWriter.write(context);
                bufferedWriter.write("\n");
                bufferedWriter.flush();
                // 接收信息
                String result = bufferedReader.readLine();
                System.out.println("服务器返回信息：" + result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
