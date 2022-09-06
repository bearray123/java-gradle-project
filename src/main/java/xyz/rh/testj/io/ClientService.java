/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by xionglei01@baidu.com on 2022/8/5.
 */
public class ClientService {

    public static void main(String[] args) {

        System.out.println("ClientService running.... in " + Thread.currentThread().getName());

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 10001);
        Socket socket = new Socket();
        ObjectInputStream inputstream = null;
        ObjectOutputStream outputsteam = null;
        try {
            socket.connect(address);
            inputstream = new ObjectInputStream(socket.getInputStream());
            outputsteam = new ObjectOutputStream(socket.getOutputStream());

            String data = inputstream.readUTF();
            System.out.println("read from server:: " + data);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputstream.close();
                outputsteam.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
