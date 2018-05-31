/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: MsgConsumer.java</p>
 *
 * @author jiangningning
 * @date 2018/5/31
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/5/31 Create
 */
package com.hh.springbootdev.kafka;

import com.hh.springbootdev.properties.KafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

public class MsgConsumer {

    public static KafkaConsumer<Object, Object> createConsumer(String groupId, String clientId, KafkaProperties properties) {
        Properties props = new Properties();
        props.put("bootstrap.servers", properties.getBootstrapServers());
        props.put("group.id", groupId);
        //props.put("client.id", clientId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "false");
        //一次从kafka中poll出来的数据条数
        //max.poll.records条数据需要在在session.timeout.ms这个时间内处理完
        props.put("max.poll.records","100");
        props.put("session.timeout.ms", "100000");
        props.put("request.timeout.ms", "120000");
        return new KafkaConsumer<>(props);
    }

    public static void consumeMsg(KafkaConsumer<Object, Object> consumer){
        while (true) {
            ConsumerRecords<Object, Object> records = consumer.poll(1000);
            for (ConsumerRecord<Object, Object> record : records) {
                System.out.println(record.key());
                System.out.println(record.value());
            }
        }
    }
}
