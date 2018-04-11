package com.hh.springbootdev.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Desc: 用于处理404
 * User: jiangningning
 * Date: 2018/4/11
 * Time: 10:50
 */
@Controller
public class GlobalErrorContrller implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public String handleError(){
        return "404";
    }
}
