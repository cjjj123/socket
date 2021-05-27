package com.cjj.demo.socketdemo0524;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/24
 * Time:18:03
 */
public class ClientB {

    public static void main(String[] args) {
        try {
            while (true){
                Socket socket = new Socket("127.0.0.1", 8888);
                Scanner scanner = new Scanner(System.in);
                String msg = scanner.nextLine();
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.write(msg);
                pw.flush();
                System.out.println("发送信息到服务器，信息：" + msg);
                socket.shutdownOutput();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String returnMsg;
                while ((returnMsg = br.readLine()) != null){
                    System.out.println("接收服务器信息：" + returnMsg);
                }
                pw.close();
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
