package com.hh.springbootdev.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/23
 * Time: 13:13
 */
class ProducerTest {

    public static void sendMsgtoKafka(String topic, String msg) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "111.231.109.105:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>(topic, String.valueOf(System.currentTimeMillis()), msg + ">>>>>>>>>>>>>>"
                + String.valueOf(System.currentTimeMillis())));
        producer.close();
    }

    public static String createTopic(String topic) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "111.231.109.105:9092");
        AdminClient adminClient = AdminClient.create(prop);
        List<NewTopic> topics = new ArrayList<>();
        NewTopic newTopic = new NewTopic(topic, 1, (short) 1);
        topics.add(newTopic);
        CreateTopicsResult result = adminClient.createTopics(topics);
        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return topic;
    }

}

class Thread1 implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(3000);
                ProducerTest.sendMsgtoKafka("topic1", Thread.currentThread().getName() + " -> Topic1's msg ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Main {
    public static void main(String[] args) {
        /*ProducerTest producerTest = new ProducerTest();
        producerTest.createTopic("topic1");*/
        Thread1 t1 = new Thread1();
        new Thread(t1).start();
        new Thread(t1).start();
        new Thread(t1).start();
        new Thread(t1).start();
    }
}
