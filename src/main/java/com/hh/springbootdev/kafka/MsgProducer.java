/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: Producer.java</p>
 *
 * @author jiangningning
 * @date 2018/5/31
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/5/31 Create
 */
package com.hh.springbootdev.kafka;

import com.hh.springbootdev.properties.KafkaProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MsgProducer {

    public static Producer<Object, Object> getProducer(KafkaProperties properties) {
        Properties props = new Properties();
        props.put("bootstrap.servers", properties.getBootstrapServers());
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");
        props.put("batch.size", 16384);
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        return new KafkaProducer<>(props);
    }

    public static void sendMsg2Kafka(Producer<Object, Object> producer, String topic, String key, String msg) {
        producer.send(new ProducerRecord<>(topic, key, msg));
        producer.close();
    }

    public static void main(String[] args) {
        //MyThread myThread = new MyThread();
        new Thread(new MyThread()).start();
        new Thread(new MyThread()).start();
        new Thread(new MyThread()).start();
        new Thread(new MyThread()).start();
    }

}

class MyThread implements Runnable{

    @Override
    public void run() {
        KafkaProperties kafkaProperties = new KafkaProperties();
        kafkaProperties.setBootstrapServers("111.231.109.105:9092");
        Producer<Object, Object> producer = MsgProducer.getProducer(kafkaProperties);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentStringTimeMillis = String.valueOf(System.currentTimeMillis());
        MsgProducer.sendMsg2Kafka(producer, "topic1",currentStringTimeMillis,
                Thread.currentThread().getName() + " -> Topic1's msg ---->>" + currentStringTimeMillis);
    }

}
