package com.hh.springbootdev.activemq;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Destination;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 16:41
 */
public class ActiveMQTests extends SpringbootdevApplicationTests {

    @Autowired
    private Producter producter;

    @Test
    public void test1(){
        Destination p2pMsg = new ActiveMQQueue("msg.p2p.queue");
        Destination p2pMsg1 = new ActiveMQQueue("msg.p2p.queue1");
        Destination p2pMsg2 = new ActiveMQQueue("msg.p2p.queue2");
        producter.sendMessage(p2pMsg, "hello activemp");
        producter.sendMessage(p2pMsg1, "hello activemp1");
        producter.sendMessage(p2pMsg2, "hello activemp2");
    }

}
