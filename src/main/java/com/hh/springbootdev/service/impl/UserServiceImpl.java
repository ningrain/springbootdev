package com.hh.springbootdev.service.impl;

import com.hh.springbootdev.dao.UserDao;
import com.hh.springbootdev.entity.User;
import com.hh.springbootdev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addUser(User user) {
        userDao.save(user);
    }
}
