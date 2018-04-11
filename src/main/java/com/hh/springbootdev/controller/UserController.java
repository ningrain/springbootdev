package com.hh.springbootdev.controller;

import com.hh.springbootdev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/11
 * Time: 15:28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/getUser/{id}")
    public String getUserById(@PathVariable int id){
        return userService.getUserById(id).toString();
    }

}
