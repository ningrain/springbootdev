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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: UserService</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Service
public class Class {

	@Autowired
	private Student student;

	public Class() {
		System.out.println("Class constructor");
	}
}
