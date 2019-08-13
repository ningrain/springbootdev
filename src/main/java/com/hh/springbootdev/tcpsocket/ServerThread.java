/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: ServerThread.java</p>
 *
 * @author jiangningning
 * @date 2019/8/12
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/8/12 Create
 */
package com.hh.springbootdev.tcpsocket;

import com.hh.springbootdev.util.ThreadPoolUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * <p>Title: ServerThread</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class ServerThread implements Runnable {

    private Socket client;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            ThreadPoolUtil pool = ThreadPoolUtil.init();
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag = true;
            while (flag) {
                //接收从客户端发送过来的数据
                String str = buf.readLine();
                if (str == null || "".equals(str)) {
                    flag = false;
                } else {
                    if ("bye".equals(str)) {
                        flag = false;
                    } else {
                        pool.submit(new DataHandler(str));
                    }
                }
            }
            out.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
