package com.hh.springbootdev.service;

import com.hh.springbootdev.entity.ResultBean;
import com.hh.springbootdev.entity.User;

import javax.swing.table.TableStringConverter;
import java.util.Map;

public interface UserService {

    int getUserNums();

    User getUserById(int id);

    int addUser(User user);

    User getUserFromClusterById(int id);

    ResultBean<User> getUserById1(int id);

    long update(Map<String, Object> params);
}
