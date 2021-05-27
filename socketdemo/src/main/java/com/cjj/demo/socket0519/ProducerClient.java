package com.cjj.demo.socket0519;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生产者
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/7
 * Time:11:21
 */
public class ProducerClient {

    // 服务器地址
    public static final String IP = "localhost";
    // 服务器端口号
    public static final int PORT = 8888;

    private static int count = 0;

    private static int i = 0;

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(10);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("127.0.0.1", 8888);
                    //ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    while (true){
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
        };
        pool.submit(runnable);
    }
}
