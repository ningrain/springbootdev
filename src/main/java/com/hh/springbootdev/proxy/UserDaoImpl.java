/*
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: UserDaoImpl.java</p>
 *
 * @author jiangningning
 * @date 2020/8/24
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/8/24 Create
 */
package com.hh.springbootdev.proxy;

/**
 * <p>Title: UserDaoImpl</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class UserDaoImpl implements IUserDao {

    @Override
    public void say() {
        System.out.println("say sth......");
    }

    @Override
    public void eat() {
        System.out.println("eat sth......");
    }

}

