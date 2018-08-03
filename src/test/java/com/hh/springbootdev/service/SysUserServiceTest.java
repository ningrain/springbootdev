package com.hh.springbootdev.service;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SysUserServiceTest extends SpringbootdevApplicationTests {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void findByUsername() {
        Map<String, Object> params = new HashMap<>();
        params.put("username", "root");
        System.out.println(sysUserService.findByUsername(params).toString());
    }
}