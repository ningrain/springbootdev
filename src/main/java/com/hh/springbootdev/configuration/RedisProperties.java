package com.hh.springbootdev.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 13:00
 */
@Component
@ConfigurationProperties(prefix = "support")
public class RedisProperties {

    private String redishost;

    private int redisport;

    private String redispassword;

    public String getRedishost() {
        return redishost;
    }

    public void setRedishost(String redishost) {
        this.redishost = redishost;
    }

    public int getRedisport() {
        return redisport;
    }

    public void setRedisport(int redisport) {
        this.redisport = redisport;
    }

    public String getRedispassword() {
        return redispassword;
    }

    public void setRedispassword(String redispassword) {
        this.redispassword = redispassword;
    }
}
