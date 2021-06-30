/*
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: CustomWebMvcRegistrations.java</p>
 *
 * @author jiangningning
 * @date 2020/9/1
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/9/1 Create
 */
package com.hh.springbootdev.configuration;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * <p>Title: CustomWebMvcRegistrations</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
@Configuration
public class CustomWebMvcRegistrations implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return null;
    }
}
