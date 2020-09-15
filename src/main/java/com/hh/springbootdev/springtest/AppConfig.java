/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: AppConfig.java</p>
 *
 * @author jiangningning
 * @date 2020/8/1
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/8/1 Create
 */
package com.hh.springbootdev.springtest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: AppConfig</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Configuration
@ComponentScan("com.hh.springbootdev.springtest")
public class AppConfig {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		// Student student = context.getBean(Student.class);
	}

}
