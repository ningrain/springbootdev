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

import com.hh.springbootdev.dao.SysUserDao;
import com.hh.springbootdev.entity.SysUser;
import com.hh.springbootdev.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>Title: SysUserServiceImpl</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public SysUser findByUsername(Map<String, Object> params) {
        return sysUserDao.findByUsername(params);
    }
}
