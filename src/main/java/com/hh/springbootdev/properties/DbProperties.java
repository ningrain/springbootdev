package com.hh.springbootdev.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 14:36
 */
@Data
@ToString
@ConfigurationProperties(prefix = "spring.datasource")
public class DbProperties {

    private String url;

    private String driverClassName;

    private String username;

    private String password;

}
