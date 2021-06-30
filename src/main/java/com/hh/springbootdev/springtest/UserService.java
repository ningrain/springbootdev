/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: UserService.java</p>
 *
 * @author jiangningning
 * @date 2020/8/4
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/8/4 Create
 */
package com.hh.springbootdev.springtest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * <p>Title: UserService</p>
 * <p>Description: </p>
 * @author jiangningning
 */

public class UserService implements InitializingBean {

    public UserService() {
        System.out.println("UserService Constructor");
    }

    private void init() {
        System.out.println("UserService init");
    }

    @PostConstruct
    public void postConstruct () {
        System.out.println("UserService postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserService afterPropertiesSet");
    }
}
