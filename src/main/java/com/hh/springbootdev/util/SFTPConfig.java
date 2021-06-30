/*
 * Copyright (c) 2008-2021 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: SFTPServer.java</p>
 *
 * @author jiangningning
 * @date 2021/6/21
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2021/6/21 Create
 */
package com.hh.springbootdev.util;

/**
 * <p>Title: SFTPServer</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class SFTPConfig {

    /**
     * sftp服务器地址
     **/
    private String host;

    /** sftp 端口号 默认22 */
    private int port = 22;

    /** ftp服务器用户名 */
    private String userName;

    /** ftp服务器密码 */
    private String password;

    /**
     * 连接超时时间，单位ms
     */
    private Integer timeOut = 30000;

    public SFTPConfig() {
    }

    public SFTPConfig(String host, int port, String userName, String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

}
