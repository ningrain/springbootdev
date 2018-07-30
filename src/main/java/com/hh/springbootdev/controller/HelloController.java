package com.hh.springbootdev.controller;

import com.hh.springbootdev.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/10
 * Time: 16:13
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/sayHello")
    @ResponseBody
    public String sayHello(){
        //日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        return "Hello SpringBoot!";
    }

    @RequestMapping("/index")
    public String index(ModelMap map){
        //int a = 1/0;
        map.addAttribute("host", "http://www.baidu.com");
        throw new CustomException("抛出自定义异常");
        // return "index";
    }

}
