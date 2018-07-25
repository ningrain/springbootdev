package com.hh.springbootdev.service;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import com.hh.springbootdev.entity.ResultBean;
import com.hh.springbootdev.entity.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest extends SpringbootdevApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void getUserById1() {
        ResultBean<User> resultBean = userService.getUserById1(1);
        System.out.println(resultBean.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("A", new User());
        map.put("B", 1);
        ResultBean<Map> resultBean1 = new ResultBean<>(map);
        System.out.println(resultBean1.toString());
    }

    @Test
    public void update() {
        Map<String, Object> params = new HashMap<>();
        params.put("age", 43);
        params.put("name", "master");
        long updatesNum = userService.update(params);
        System.out.println(updatesNum);
    }
}