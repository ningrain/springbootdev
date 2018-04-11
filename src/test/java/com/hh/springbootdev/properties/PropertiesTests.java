package com.hh.springbootdev.properties;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/10
 * Time: 16:35
 */
public class PropertiesTests extends SpringbootdevApplicationTests {

    @Autowired
    private BlogProperties blogProperties;

    @Autowired
    private Environment env;

    @Test
    public void propertiesTest(){
        System.out.println(env.getProperty("spring.datasource.username"));
        System.out.println(blogProperties.getDes());
    }

}
