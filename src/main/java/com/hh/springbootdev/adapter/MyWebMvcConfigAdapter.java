package com.hh.springbootdev.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Desc: 注册自定义文件夹为资源文件夹
 * User: jiangningning
 * Date: 2018/4/11
 * Time: 13:57
 */
@Configuration
public class MyWebMvcConfigAdapter implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
        * 将classpath 下的 myfile 文件夹注册为资源文件夹
        * */
        registry.addResourceHandler("/myfile/**").addResourceLocations("classpath:/myfile/");
    }
}
