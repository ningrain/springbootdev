/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: PasswordEncodeUtil.java</p>
 *
 * @author jiangningning
 * @date 2018/11/15
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/11/15 Create
 */
package com.hh.springbootdev.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>Title: PasswordEncodeUtil</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class PasswordEncodeUtil {

    public static String encode(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password.trim());
    }

}
