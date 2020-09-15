/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: UserService.java</p>
 *
 * @author jiangningning
 * @date 2020/8/1
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/8/1 Create
 */
package com.hh.springbootdev.springtest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <p>Title: UserService</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Service
public class Student implements InitializingBean, ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private Class aClass;


	public Student() {
		System.out.println("Student constructor");
	}

	@PostConstruct
	public void init() {
		System.out.println("Student PostConstruct init");
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println("Student afterPropertiesSet");
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("Student onApplicationEvent");
	}
}
