/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: UserDetailsServiceImpl.java</p>
 *
 * @author jiangningning
 * @date 2018/8/20
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/8/20 Create
 */
package com.hh.springbootdev.service.impl;

import com.hh.springbootdev.dao.SysUserDao;
import com.hh.springbootdev.entity.SysUser;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>Title: UserDetailsServiceImpl</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.findByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException(String.format("没有该用户 '%s'.", username));
        }
        return new SysUser(sysUser.getUid(), sysUser.getUsername(), sysUser.getName(), sysUser.getPassword(),
                sysUser.getState(), sysUser.getRoleList());
    }
}
