package com.hh.springbootdev.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/10
 * Time: 16:32
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "com.hh.jn.blog")
public class BlogProperties {

    //@Value("${com.hh.jn.blog.name}")
    private String name;

    //@Value("${com.hh.jn.blog.title}")
    private String title;

    //@Value("${com.hh.jn.blog.des}")
    private String des;

}
