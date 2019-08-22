package com.hh.springbootdev.service;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import com.hh.springbootdev.entity.SysUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class SysUserServiceTest extends SpringbootdevApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void findByUsername() {
        System.out.println(sysUserService.findByUsername("root").toString());
    }

    @Test
    public void selectAllUserWithRole(){
        List<SysUser> sysUsers = sysUserService.selectAllUserWithRole();
        System.out.println(sysUsers);
    }

    @Test
    public void test3(){
        for (int i = 1; i <= 200000; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setUsername("test" + i);
            sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getUsername()));
            sysUser.setRealname("test" + i);
            sysUser.setUserState(true);
            sysUserService.save(sysUser);
        }
    }

    @Test
    public void test4(){
        for (int i = 0; i < 100; i++) {
            batchInsert();
            System.out.println("===========  " + i + "  =============");
        }
    }

    @Test
    public void findAllTest() {
        System.out.println("第一次查询......");
        System.out.println(sysUserService.findAll());
        System.out.println("第二次查询......");
        System.out.println(sysUserService.findAll());
        SysUser sysUser = new SysUser();
        sysUser.setUsername("AAA");
        sysUser.setPassword("AAA");
        sysUser.setRealname("AAA");
        sysUser.setUserState(true);
        sysUserService.save(sysUser);
        System.out.println("第三次查询......");
        System.out.println(sysUserService.findAll());
    }

    @Test
    public void saveUserTest() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("AAA");
        sysUser.setPassword("AAA");
        sysUser.setRealname("AAA");
        sysUser.setUserState(true);
        sysUserService.save(sysUser);
    }

    private void batchInsert(){
        List<SysUser> list = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setUsername("test" + i);
            sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getUsername()));
            sysUser.setRealname("test" + i);
            sysUser.setUserState(true);
            list.add(sysUser);
        }
        sysUserService.batchInsert(list);
    }
}