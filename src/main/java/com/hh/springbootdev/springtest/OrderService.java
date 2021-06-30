/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: OrderService.java</p>
 *
 * @author jiangningning
 * @date 2020/8/4
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/8/4 Create
 */
package com.hh.springbootdev.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>Title: OrderService</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Service
public class OrderService {

    @Autowired
    private UserService userService;

}
