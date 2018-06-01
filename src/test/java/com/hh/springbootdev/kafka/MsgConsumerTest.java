package com.hh.springbootdev.kafka;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import com.hh.springbootdev.properties.KafkaProperties;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MsgConsumerTest extends SpringbootdevApplicationTests {

    @Autowired
    private KafkaProperties properties;

    @Test
    public void test1(){
        KafkaConsumer<Object, Object> consumer = MsgConsumer.createConsumer("group1", null, properties);
        consumer.subscribe(Arrays.asList("topic1"));
        MsgConsumer.consumeMsg(consumer);
    }

    @Test
    public void test2(){
        KafkaConsumer<Object, Object> consumer = MsgConsumer.createConsumer("group2", null, properties);
        consumer.subscribe(Arrays.asList("topic2"));
        MsgConsumer.consumeMsg(consumer);
    }

}