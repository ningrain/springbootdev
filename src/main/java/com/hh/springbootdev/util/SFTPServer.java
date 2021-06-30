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

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

/**
 * <p>Title: SFTPServer</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class SFTPServer {

    /**
     * 会话
     */
    private Session session;

    /**
     * 连接通道
     */
    private Channel channel;

    /**
     * sftp操作类
     */
    private ChannelSftp sftp;

    public SFTPServer() {
    }

    public SFTPServer(Session session, Channel channel, ChannelSftp sftp) {
        this.session = session;
        this.channel = channel;
        this.sftp = sftp;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ChannelSftp getSftp() {
        return sftp;
    }

    public void setSftp(ChannelSftp sftp) {
        this.sftp = sftp;
    }
}
