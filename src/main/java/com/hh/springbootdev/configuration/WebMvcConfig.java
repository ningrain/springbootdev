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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Title: WebMvcConfig</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
         * 将classpath 下的 myfile 文件夹注册为资源文件夹
         * */
        registry.addResourceHandler("/myfile/**").addResourceLocations("classpath:/myfile/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "HEAD", "PUT", "OPTIONS")
                .allowCredentials(true).maxAge(3600);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
