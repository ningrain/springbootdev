/*
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: Main.java</p>
 *
 * @author jiangningning
 * @date 2020/8/24
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/8/24 Create
 */
package com.hh.springbootdev.proxy;

import java.lang.reflect.Proxy;

/**
 * <p>Title: Main</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class Main {

    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImpl();
        UserDaoProxy proxy = new UserDaoProxy(userDao);
        IUserDao iUserDao = (IUserDao) Proxy.newProxyInstance(
                userDao.getClass().getClassLoader(),
                userDao.getClass().getInterfaces(),
                proxy);
        iUserDao.say();
        System.out.println("===============================");
        iUserDao.eat();
    }

}
