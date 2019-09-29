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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: SysUserServiceImpl</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
@Service
@EnableCaching
@CacheConfig(cacheNames = "users")
public class SysUserServiceImpl implements SysUserService {

    private final SysUserDao sysUserDao;

    @Autowired
    public SysUserServiceImpl(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    @Override
    @Cacheable(cacheNames = "users")
    public List<SysUser> findAll() {
        return sysUserDao.findAll();
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void save(SysUser user) {
        sysUserDao.save(user);
    }

    @Override
    public void batchInsert(List<SysUser> users) {
        sysUserDao.batchInsert(users);
    }

    @Override
    public List<SysUser> selectAllUserWithRole() {
        return sysUserDao.selectAllUserWithRole();
    }
}
