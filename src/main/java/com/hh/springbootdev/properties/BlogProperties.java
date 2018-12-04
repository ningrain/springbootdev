package com.hh.springbootdev.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/10
 * Time: 16:32
 */
@Component
@ConfigurationProperties(prefix = "com.hh.jn.blog")
public class BlogProperties {

    //@Value("${com.hh.jn.blog.name}")
    private String name;

    //@Value("${com.hh.jn.blog.title}")
    private String title;

    //@Value("${com.hh.jn.blog.des}")
    private String des;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "BlogProperties{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
