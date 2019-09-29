package com.hh.springbootdev.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 13:00
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "support")
public class RedisProperties {

    private String redishost;

    private int redisport;

    private String redispassword;

}
