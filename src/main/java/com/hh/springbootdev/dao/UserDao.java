package com.hh.springbootdev.dao;

import com.hh.springbootdev.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/11
 * Time: 11:58
 */
@Component
@Mapper
public interface UserDao{

    int getUserNums();

    User getById(@Param("id") int id);

    int save(User user);

    long update(Map<String, Object> params);
}
