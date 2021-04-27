package com.cjj.socketdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocketdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketdemoApplication.class, args);
        System.out.println("Socket服务启动成功");
    }

}
