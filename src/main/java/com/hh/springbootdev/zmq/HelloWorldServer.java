/*
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: HelloWorldServer.java</p>
 *
 * @author jiangningning
 * @date 2020/12/7
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/12/7 Create
 */
package com.hh.springbootdev.zmq;

/**
 * <p>Title: HelloWorldServer</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class HelloWorldServer
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.DEALER);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);

                // Print the message
                System.out.println(
                        "Received: [" + new String(reply, ZMQ.CHARSET) + "]"
                );

                // Send a response
                String response = "Hello, world!";
                socket.send(response.getBytes(ZMQ.CHARSET), 0);
            }
        }
    }
}
