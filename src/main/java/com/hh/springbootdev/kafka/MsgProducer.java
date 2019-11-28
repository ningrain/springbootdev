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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MsgProducer {

    private final static Logger logger = LoggerFactory.getLogger(MsgProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MsgProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMsg2Kafka(String topic, String msg) {
        logger.info(">>>>>------producer msg: [{}]", msg);
        kafkaTemplate.send(topic, msg);
    }

}
