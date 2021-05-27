package com.cjj.demo.socket0517;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2021/5/17
 * Time:18:01
 */
public class ServerMsgPoll extends Thread {

    private Socket socket;

    public ServerMsgPoll(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.write("服务器收到信息，信息为：" );
            pw.flush();
            pw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
