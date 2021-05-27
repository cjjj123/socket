package com.cjj.demo.socketdemo0521;

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
 * Time:15:33
 */
public class ClientB {
    private Socket socket;

    private String serverIP;

    private int port;

    public ClientB(String serverIP, int port) throws Exception{
        this.serverIP = serverIP;
        this.port = port;
    }

    public void run() throws IOException {
        while (true) {
            socket = new Socket(serverIP, port);
            input();
        }
    }

    @SuppressWarnings("resource")
    public void input() throws IOException{
        Scanner scanner = new Scanner(System.in);
        String servermsg = scanner.nextLine();
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

    public static void main(String[] args) throws Exception {
        ClientB c = new ClientB("127.0.0.1",6666);
        c.run();
    }
}
