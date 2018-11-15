/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: WebMvcConfig.java</p>
 *
 * @author jiangningning
 * @date 2018/11/8
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/11/8 Create
 */
package com.hh.springbootdev.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Title: WebMvcConfig</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
