package com.hh.springbootdev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/10
 * Time: 16:13
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "Hello SpringBoot!";
    }

}
