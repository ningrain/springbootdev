package com.hh.springbootdev.Redis;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import com.hh.springbootdev.util.RedisUtil;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 9:35
 */
public class RedisTests extends SpringbootdevApplicationTests{

    @Test
    public void test1(){
        RedisUtil.set("aaa", "AAA");
        System.out.println(RedisUtil.get("aaa"));
        RedisUtil.remove("aaa");
    }
}
