/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: SysUserDao.java</p>
 *
 * @author jiangningning
 * @date 2018/8/3
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/8/3 Create
 */
package com.hh.springbootdev.dao;

import com.hh.springbootdev.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>Title: SysUserDao</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Component
@Mapper
public interface SysUserDao {

    SysUser findByUsername(Map<String, Object> params);

}
