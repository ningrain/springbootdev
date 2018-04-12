package com.hh.springbootdev.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 16:38
 */
@Component
public class Consumer {

    @JmsListener(destination = "msg.p2p.queue1")
    @SendTo("back.queue")
    public String processMessage1(String content){
        System.out.println("Consumer Receive a message: " + content);
        return "Return message: " + content;
    }

    @JmsListener(destination = "msg.p2p.queue")
    @SendTo("back.queue")
    public void processMessage(String content){
        System.out.println("Consumer Receive a message: " + content);
    }

    @JmsListener(destination = "msg.p2p.queue2")
    @SendTo("back.queue")
    public void processMessage2(String content){
        System.out.println("Consumer Receive a message: " + content);
    }

}
