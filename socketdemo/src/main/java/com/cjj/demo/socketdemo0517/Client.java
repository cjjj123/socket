package com.cjj.demo.socketdemo0517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/17
 * Time:16:21
 */
public class Client {

    private Socket socket;

    private String serverIP;

    private int port;

    public Client(String serverIP,int port) throws UnknownHostException, IOException {
        this.serverIP = serverIP;
        this.port = port;
    }

    public void run() throws IOException{
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

    public static void main(String[] args) throws UnknownHostException, IOException {
        Client c = new Client("127.0.0.1",8787);
        c.run();
    }

}
