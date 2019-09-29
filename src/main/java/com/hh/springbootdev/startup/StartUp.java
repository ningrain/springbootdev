/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: StartUp.java</p>
 *
 * @author jiangningning
 * @date 2019/9/5
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/9/5 Create
 */
package com.hh.springbootdev.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>Title: StartUp</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Component
public class StartUp implements CommandLineRunner {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void run(String... args) {
        kafkaTemplate.send("users", String.valueOf(System.currentTimeMillis()));
    }
}
