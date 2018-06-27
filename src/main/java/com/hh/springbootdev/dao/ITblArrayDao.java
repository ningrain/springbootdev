/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: ITblArrayDao.java</p>
 *
 * @author jiangningning
 * @date 2018/6/6
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/6/6 Create
 */
package com.hh.springbootdev.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ITblArrayDao {

    int save(List<Map<String, Object>> list);

    int save1(Map map);

    int save2(@Param("a") int[] a,@Param("b") String[][] b, @Param("c") int c);
}
