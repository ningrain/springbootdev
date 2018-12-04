package com.hh.springbootdev.service;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysRoleServiceTest extends SpringbootdevApplicationTests {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void getRolesByUserId() {
        System.out.println(sysRoleService.getRolesByUserId(1));
    }
}