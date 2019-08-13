/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: DataHandler.java</p>
 *
 * @author jiangningning
 * @date 2019/8/12
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/8/12 Create
 */
package com.hh.springbootdev.tcpsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: DataHandler</p>
 * <p>Description: </p>
 * @author jiangningning
 */
public class DataHandler implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(ServerThread.class);

    private String str;

    public DataHandler(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        //将接收到的字符串前面加上echo，发送到对应的客户端
        logger.info("echo: {}", str);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
