/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: SysUserServiceImpl.java</p>
 *
 * @author jiangningning
 * @date 2018/8/3
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/8/3 Create
 */
package com.hh.springbootdev.service.impl;

import com.hh.springbootdev.dao.SysRoleDao;
import com.hh.springbootdev.entity.SysRole;
import com.hh.springbootdev.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: SysRoleServiceImpl</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleDao sysRoleDao;

    @Autowired
    public SysRoleServiceImpl(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }

    @Override
    public List<SysRole> getRolesByUserId(long id) {
        return sysRoleDao.getRolesByUserId(id);
    }
}
