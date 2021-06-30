/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: MyFactoryBean.java</p>
 *
 * @author jiangningning
 * @date 2020/8/7
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/8/7 Create
 */
package com.hh.springbootdev.springtest;

import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.stereotype.Component;

/**
 * <p>Title: MyFactoryBean</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Component
public class MyFactoryBean implements SmartFactoryBean {

    @Override
    public Object getObject() {
        return new BeanService();
    }

    @Override
    public java.lang.Class<?> getObjectType() {
        return BeanService.class;
    }

    @Override
    public boolean isEagerInit() {
        return true;
    }
}
