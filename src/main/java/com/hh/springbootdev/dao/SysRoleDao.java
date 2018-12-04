/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: SysRoleDao.java</p>
 *
 * @author jiangningning
 * @date 2018/11/7
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/11/7 Create
 */
package com.hh.springbootdev.dao;

import com.hh.springbootdev.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Title: SysRoleDao</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
@Component
@Mapper
public interface SysRoleDao {

    List<SysRole> getRolesByUserId(long id);

}
