/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: SysUserService.java</p>
 *
 * @author jiangningning
 * @date 2018/8/3
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/8/3 Create
 */
package com.hh.springbootdev.service;

import com.hh.springbootdev.entity.SysUser;

import java.util.Map;

/**
 * <p>Title: SysUserService</p>
 * <p>Description: </p>
 * @author jiangningning
 */
public interface SysUserService {

    SysUser findByUsername(Map<String, Object> params);

}
