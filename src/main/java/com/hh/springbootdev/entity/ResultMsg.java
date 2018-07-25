/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: ResultMsg.java</p>
 *
 * @author jiangningning
 * @date 2018/7/16
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/7/16 Create
 */
package com.hh.springbootdev.entity;

import lombok.Data;

/**
 * <p>Title: ResultMsg</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Data
public class ResultMsg<T> {

    private int code;

    private String msg;

    private T data;

    public ResultMsg() {
    }

    public ResultMsg(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
