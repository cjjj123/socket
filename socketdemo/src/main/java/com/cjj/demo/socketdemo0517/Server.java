package com.cjj.demo.socketdemo0517;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/17
 * Time:16:20
 */
public class Server extends Thread{
    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader;
        try {
            InputStream in = socket.getInputStream();
            reader = new BufferedReader(
                    new InputStreamReader(
                            in));
            handle(socket,reader.readLine());
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void handle(Socket socket, String msg) throws IOException{
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        if (msg != null) {
            if (msg.contains("add")) {
                msg = msg.substring(msg.indexOf("add")+4);
                Buffer.getIntance().produce(msg);
                pw.write("server:add "+ msg +" to queue successfully");
            }else if(msg.contains("poll")){
                String consumeMsg = (String) Buffer.getIntance().consume();
                pw.write("server:remove "+ consumeMsg +" from queue successfully");
            }else if(msg.contains("size")){
                pw.write("server:size is "+ Buffer.getIntance().getQueueSize());
            }else{
                pw.write("server:no such command");
            }
        }else{
            pw.write("server:blank message");
        }
        pw.flush();
        socket.shutdownOutput();
        pw.close();
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8787);
        while(true){
            new Server(serverSocket.accept()).start();
        }

    }
}
