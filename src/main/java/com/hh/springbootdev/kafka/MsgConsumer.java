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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// @Component
public class MsgConsumer {

    private final static Logger logger = LoggerFactory.getLogger(MsgConsumer.class);

    @KafkaListener(topics = "users", groupId = "users-group1")
    public static void consumeMsg(String msg) {
        logger.info("<<<<<------consumer msg:[{}]", msg);
    }
}
