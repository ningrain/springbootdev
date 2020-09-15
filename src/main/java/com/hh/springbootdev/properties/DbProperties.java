package com.hh.springbootdev.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 14:36
 */
@Data
// @Component
// @ConfigurationProperties(prefix = "spring.datasource")
public class DbProperties {

    private String url;

    private String driverClassName;

    private String username;

    private String password;

}
