package com.hh.springbootdev.properties;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/10
 * Time: 16:35
 */
public class PropertiesTests extends SpringbootdevApplicationTests {

    @Autowired
    private BlogProperties blogProperties;

    @Test
    public void propertiesTest(){
        System.out.println(blogProperties.getDes());
    }

}
