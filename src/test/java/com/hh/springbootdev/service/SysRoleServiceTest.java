package com.hh.springbootdev.service;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class SysRoleServiceTest extends SpringbootdevApplicationTests {

    @Resource
    private SysRoleService sysRoleService;

    @Test
    public void getRolesByUserId() {
        System.out.println(sysRoleService.getRolesByUserId(1));
    }
}