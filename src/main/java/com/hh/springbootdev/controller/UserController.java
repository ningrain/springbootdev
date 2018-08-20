/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: UserController.java</p>
 *
 * @author jiangningning
 * @date 2018/8/20
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/8/20 Create
 */
package com.hh.springbootdev.controller;

import com.hh.springbootdev.entity.SysUser;
import com.hh.springbootdev.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER')")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @PreAuthorize("hasRole('ADMIN')")//有ROLE_ADMIN权限的用户可以访问
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<SysUser> getUsers() {
        return sysUserService.findAll();
    }

}
