package com.hh.springbootdev.service.impl;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import com.hh.springbootdev.annotation.TargetDataSource;
import com.hh.springbootdev.entity.User;
import com.hh.springbootdev.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImplTest extends SpringbootdevApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void getUserNums() {
        System.out.println(userService.getUserNums());
    }

    @Test
    public void getUserById(){
        User user = userService.getUserById(3);
        System.out.println(user);
    }
    @Test
    public void addUser(){
        int id = userService.addUser(new User("bbb", 14));
        System.out.println(id);
    }
    @Test
    public void getUserFromClusterById(){
        User user = userService.getUserFromClusterById(1);
        System.out.println(user.toString());
    }
}