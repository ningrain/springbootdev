/*
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: Client.java</p>
 *
 * @author jiangningning
 * @date 2020/12/2
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/12/2 Create
 */
package com.hh.springbootdev.socket;

import com.haohan.system.domain.HPortalMsg;
import com.hh.springbootdev.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <p>Title: Client</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
@Slf4j
public class Client {

    public static void main(String[] args) {
        try {

            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("172.16.20.23", 5568));

            HPortalMsg.UserInfoConf.Builder userInfoConfBuilder = HPortalMsg.UserInfoConf.newBuilder();
            HPortalMsg.UserInfoConf.UserInfo.Builder userInfoBuilder = HPortalMsg.UserInfoConf.UserInfo.newBuilder();
            userInfoBuilder.setActType(0);
            userInfoBuilder.setUserId(1);
            userInfoBuilder.setToken("dadafq231r41fwvebh");
            userInfoBuilder.setClientIp("172.16.20.23");
            userInfoBuilder.setLimitConn(300);
            userInfoBuilder.setLimitBandwidth(2);
            userInfoBuilder.setLimitBytes(1024 * 1024 * 1024);
            userInfoBuilder.setRemainBytes(800 * 1024 * 1024);
            userInfoBuilder.addAllWhitelist(Arrays.asList("172.16.21.10", "172.16.21.11"));
            userInfoConfBuilder.addUserinfoList(userInfoBuilder.build());
            HPortalMsg.UserInfoConf userInfoConf = userInfoConfBuilder.build();
            byte[] userInfoConfByte = userInfoConf.toByteArray();

            HPortalMsg.ServerUsageInfo.Builder serverUsageInfoBuilder = HPortalMsg.ServerUsageInfo.newBuilder();
            serverUsageInfoBuilder.setTime(DateUtil.nowSecond());
            serverUsageInfoBuilder.setCpuInfo(35);
            serverUsageInfoBuilder.setMemInfo(50);
            serverUsageInfoBuilder.setBandwidthUp(300);
            serverUsageInfoBuilder.setBandwidthDown(10223);
            HPortalMsg.ServerUsageInfo serverUsageInfo = serverUsageInfoBuilder.build();
            byte[] serverUsageInfoByte = serverUsageInfo.toByteArray();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);
            dos.writeByte(0x83);
            dos.writeByte(0);
            dos.write(serverUsageInfoByte);
            byte[] bytes = bos.toByteArray();
            System.out.println("bytes: " + Arrays.toString(bytes));
            OutputStream out = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            String in;
            new Thread(new ServerResponseHandle(socket)).start();
            do {
                in = scanner.next();
                System.out.println(in);
                ByteBuffer header = ByteBuffer.allocate(2);
                header.putShort((short) (bytes.length + 2));
                out.write(header.array());
                out.write(bytes);
                out.flush();
            } while (!in.equals("bye"));
            scanner.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerResponseHandle implements Runnable {

        private Socket socket;

        public ServerResponseHandle(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                //读取服务端返回的消息
                String mess;
                //通过while循环不断读取信息，
                while ((mess = br.readLine()) != null) {
                    //输出打印
                    System.out.println("服务端返回的消息:" + mess);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
