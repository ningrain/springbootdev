package com.hh.springbootdev.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 16:34
 */
//@Component
public class Producter {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    @JmsListener(destination = "back.queue")
    public void consumerBackMsg(String content){
        System.out.println(content);
    }

}
