package com.hh.springbootdev.service;

import com.hh.springbootdev.entity.User;

public interface UserService {

    int getUserNums();

    User getUserById(int id);

    int addUser(User user);

    User getUserFromClusterById(int id);

}
