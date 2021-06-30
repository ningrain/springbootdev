/*
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: WsClient.java</p>
 *
 * @author jiangningning
 * @date 2020/12/21
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/12/21 Create
 */
package com.hh.springbootdev.socket;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.sound.midi.Soundbank;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>Title: WsClient</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class WsClient extends WebSocketClient {

    public WsClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("握手成功");
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        System.out.println("连接关闭");
    }

    @Override
    public void onError(Exception arg0) {
        System.out.println("发生错误");
    }

    @Override
    public void onMessage(String arg0) {
        System.out.println("收到消息" + arg0);
    }

    public static void main(String[] args) {
        try {
            WsClient wsClient = new WsClient(new URI("ws://172.16.20.23:5567"));
            wsClient.send("1111");
            System.out.println(wsClient);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
