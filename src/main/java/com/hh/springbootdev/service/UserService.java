package com.hh.springbootdev.service;

import com.hh.springbootdev.entity.User;

public interface UserService {

    int getUserNums();

    User getUserById(int id);

    void addUser(User user);

}
