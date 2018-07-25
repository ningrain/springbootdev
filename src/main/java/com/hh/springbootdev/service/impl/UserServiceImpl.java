package com.hh.springbootdev.service.impl;

import com.hh.springbootdev.annotation.TargetDataSource;
import com.hh.springbootdev.dao.UserDao;
import com.hh.springbootdev.entity.CustomizeDS;
import com.hh.springbootdev.entity.ResultBean;
import com.hh.springbootdev.entity.User;
import com.hh.springbootdev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/11
 * Time: 11:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int getUserNums() {
        return userDao.getUserNums();
    }

    @Override
    public User getUserById(int id) {
        return userDao.getById(id);
    }

    @Override
    public int addUser(User user) {
        return userDao.save(user);
    }

    @Override
    @TargetDataSource(CustomizeDS.CLUSTER)
    public User getUserFromClusterById(int id) {
        return userDao.getById(id);
    }

    @Override
    public ResultBean<User> getUserById1(int id) {
        User user = userDao.getById(id);
        return new ResultBean<>(user);
    }

    @Override
    public long update(Map<String, Object> params) {
        return userDao.update(params);
    }
}
