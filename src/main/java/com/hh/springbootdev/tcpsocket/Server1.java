/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: Server1.java</p>
 *
 * @author jiangningning
 * @date 2019/8/12
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/8/12 Create
 */
package com.hh.springbootdev.tcpsocket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>Title: Server1</p>
 * <p>Description: </p>
 * @author jiangningning
 */
public class Server1 {

    public static void main(String[] args) throws Exception{
        //服务端在20006端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(20006);
        Socket client = null;
        boolean f = true;
        while(f){
            //等待客户端的连接，如果没有获取连接
            client = server.accept();
            System.out.println("与客户端连接成功！");
            //为每个客户端连接开启一个线程
            new Thread(new ServerThread(client)).start();
        }
        server.close();
    }
}
