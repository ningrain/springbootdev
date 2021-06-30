/*
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: UserDaoProxy.java</p>
 *
 * @author jiangningning
 * @date 2020/8/24
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/8/24 Create
 */
package com.hh.springbootdev.proxy;

import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Title: UserDaoProxy</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class UserDaoProxy implements InvocationHandler {

    private Object target;

    public UserDaoProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before...");
        Object invoke = method.invoke(target, args);
        System.out.println("after...");
        return invoke;
    }

}
