/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.io;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xionglei01@baidu.com on 2022/8/5.
 */
public class ServerServices {

    public static void main(String[] args) {

        //InetSocketAddress address = new InetSocketAddress("127.0.0.1", 10001);
        System.out.println("ServerServices running.... in " + Thread.currentThread().getName());

        // 用十个thread启动十个客户端链接
        for (int i= 1; i<= 10; i++) {
            startSocketService(i);
        }

    }



    public static void startSocketService(int count) {

        new Thread("service_thread" + count) {
            @Override public void run() {
                System.out.println("start one service thread, thread=" + Thread.currentThread().getName());
                ObjectOutputStream oos = null;
                Socket socket = null;
                try {
                    ServerSocket serverSocket = new ServerSocket(1000 + count);
                    socket = serverSocket.accept();
                    System.out.println("Connected to Client!");
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeUTF("hey, client, you are connected with me, this is my data.....");
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

    }


}
