package com.hh.springbootdev.properties;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.core.env.Environment;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/10
 * Time: 16:35
 */
//@EnableConfigurationProperties(BlogProperties.class)
/*
* 若使用@EnableConfigurationProperties(BlogProperties.class)注解，
* 则BlogProperties类上面就不用标示 @Component注解了
* */
public class PropertiesTests extends SpringbootdevApplicationTests {

    @Autowired
    private BlogProperties blogProperties;

    @Autowired
    private KafkaProperties kafkaProperties;

    @Autowired
    private Environment env;

    @Test
    public void propertiesTest(){
        System.out.println(env.getProperty("spring.datasource.username"));
        System.out.println(blogProperties.getDes());
        System.out.println(kafkaProperties.getBootstrapServers());
    }

}
