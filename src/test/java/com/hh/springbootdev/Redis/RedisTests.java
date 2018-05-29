package com.hh.springbootdev.Redis;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import com.hh.springbootdev.entity.User;
import com.hh.springbootdev.util.RedisUtil;
import org.junit.Test;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 9:35
 */
public class RedisTests extends SpringbootdevApplicationTests{

    @Test
    public void test1(){

        RedisUtil.putBean("user1", new User(1, "aaa", 23));
        System.out.println(RedisUtil.getBean("user1", User.class));
    }
}
