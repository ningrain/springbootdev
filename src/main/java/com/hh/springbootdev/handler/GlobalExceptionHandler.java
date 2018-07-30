package com.hh.springbootdev.handler;

import com.hh.springbootdev.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc:  处理通用异常
 * User: jiangningning
 * Date: 2018/4/11
 * Time: 10:13
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.addObject("exception", e.getMessage());
        mv.addObject("url", request.getRequestURL());
        return mv;
    }

    @ExceptionHandler(CustomException.class)
    public ModelAndView CustomErrorHandler(HttpServletRequest request, Exception e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.addObject("exception", e.getMessage());
        mv.addObject("url", request.getRequestURL());
        return mv;
    }

}
