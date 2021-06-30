/*
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: Server.java</p>
 *
 * @author jiangningning
 * @date 2020/12/2
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/12/2 Create
 */
package com.hh.springbootdev.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>Title: Server</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            while (true) {
                Socket socket = ss.accept();
                new Thread(new ServerRuns(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerRuns implements Runnable {

        private Socket socket;

        public ServerRuns(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("socket info：" + socket.toString());
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                //读取客户端发送来的消息
                String mess;
                //通过while循环不断读取信息，
                while ((mess = br.readLine()) != null) {
                    //输出打印
                    System.out.println("客户端说:" + mess);
                    bw.write(mess + "\n");
                    bw.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
