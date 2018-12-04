package com.hh.springbootdev.service;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import com.hh.springbootdev.entity.SysUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class SysUserServiceTest extends SpringbootdevApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void findByUsername() {
        System.out.println(sysUserService.findByUsername("root").toString());
    }

    @Test
    public void test2(){
        SysUser user = sysUserService.findByUsername("user");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
        user.setPassword(encodedPassword);

        sysUserService.save(user);
    }

    @Test
    public void selectAllUserWithRole(){
        List<SysUser> sysUsers = sysUserService.selectAllUserWithRole();
        System.out.println(sysUsers);
    }
}