package com.hh.springbootdev.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 14:36
 */
@ConfigurationProperties(prefix = "spring.datasource")
public class DbProperties {

    private String url;

    private String driverClassName;

    private String username;

    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
