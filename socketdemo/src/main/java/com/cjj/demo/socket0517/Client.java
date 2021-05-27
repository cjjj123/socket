package com.cjj.demo.socket0517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/17
 * Time:17:37
 */
public class Client {

    private static int i = 0;

    public static void main(String[] args) {
        try {
            while (true){
                Socket socket = new Socket("127.0.0.1", 8888);
                //Scanner scanner = new Scanner(System.in);
                String servermsg = "" + i++;
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.write(servermsg);
                pw.flush();
                socket.shutdownOutput();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg;
                while ((msg = br.readLine()) != null) {
                    System.out.println(msg);
                }
                pw.close();
                br.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
