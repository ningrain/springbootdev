package com.hh.springbootdev.kafka;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import com.hh.springbootdev.properties.KafkaProperties;
import org.apache.kafka.clients.producer.Producer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MsgProducerTest extends SpringbootdevApplicationTests {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Test
    public void test1(){
        System.out.println("================");
        System.out.println(Thread.currentThread().getName());
        System.out.println("================");
        Producer<Object, Object> producer = MsgProducer.getProducer(kafkaProperties);
        String currentStringTimeMillis = String.valueOf(System.currentTimeMillis());
        MsgProducer.sendMsg2Kafka(producer, "topic1", currentStringTimeMillis,
                Thread.currentThread().getName() + " -> Topic1's msg ---->>" + currentStringTimeMillis);
        Producer<Object, Object> producer1 = MsgProducer.getProducer(kafkaProperties);
        MsgProducer.sendMsg2Kafka(producer1, "topic2", currentStringTimeMillis,
                Thread.currentThread().getName() + " -> Topic2's msg >>>>>>" + currentStringTimeMillis);

        //new Thread(new MyThread()).start();
    }
}