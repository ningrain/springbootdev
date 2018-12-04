/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: CustomException.java</p>
 *
 * @author jiangningning
 * @date 2018/7/26
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/7/26 Create
 */
package com.hh.springbootdev.exception;

/**
 * <p>Title: CustomException</p>
 * <p>Description: 自定义异常</p>
 *
 * @author jiangningning
 */
public class CustomException extends RuntimeException {

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }
}
