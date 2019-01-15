/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: LocalEnvironmentPostProcessor.java</p>
 *
 * @author jiangningning
 * @date 2018/12/5
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/12/5 Create
 */
package com.hh.springbootdev.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title: LocalEnvironmentPostProcessor</p>
 * <p>Description: 配置文件扩展</p>
 *
 * @author jiangningning
 */
public class LocalEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String filePath = System.getProperty("cfgPath");
        if (filePath != null) {
            File file = new File(filePath);
            if (file.exists()){
                System.out.println("**********加载外部扩展文件：" + filePath + "**********");
                try {
                    InputStream is = new FileInputStream(file);
                    Properties properties = new Properties();
                    properties.load(is);
                    MutablePropertySources propertySource = environment.getPropertySources();
                    propertySource.addFirst(new PropertiesPropertySource("localconfig", properties));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
