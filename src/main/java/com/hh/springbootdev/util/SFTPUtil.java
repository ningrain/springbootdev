/*
 * Copyright (c) 2008-2021 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: SFTPUtil.java</p>
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
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * <p>Title: SFTPUtil</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class SFTPUtil {

    private final static Logger log = LoggerFactory.getLogger(SFTPUtil.class);

    private static final SFTPConfig SFTP_CONFIG = new SFTPConfig();

    static {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = SFTPUtil.class.getClassLoader().getResourceAsStream("sftp.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取key对应的value值
        SFTP_CONFIG.setHost(properties.getProperty("host"));
        SFTP_CONFIG.setUserName(properties.getProperty("username"));
        SFTP_CONFIG.setPassword(properties.getProperty("password"));
        SFTP_CONFIG.setPort(Integer.parseInt(properties.getProperty("port")));
    }

    /**
     * 连接ftp/sftp服务器
     */
    public static SFTPServer getConnect() throws Exception {
        return getConnect(SFTP_CONFIG);
    }

    /**
     * 连接ftp/sftp服务器
     * @param sftpConfig sftp服务器连接配置信息
     */
    public static SFTPServer getConnect(SFTPConfig sftpConfig) throws Exception {
        /* 密钥的密码  */
        // String privateKey ="key";
        // /* 密钥文件路径  */
        // String passphrase ="path";
        /* 主机 */
        String host = sftpConfig.getHost();
        /* 端口 */
        int port = sftpConfig.getPort();
        /* 用户名 */
        String username = sftpConfig.getUserName();
        /* 密码 */
        String password = sftpConfig.getPassword();
        Session session;
        Channel channel;
        ChannelSftp sftp;// sftp操作类
        JSch jsch = new JSch();
        //设置密钥和密码
        //支持密钥的方式登陆，只需在jsch.getSession之前设置一下密钥的相关信息就可以了
        // if (privateKey != null && !"".equals(privateKey)) {
        //     if (passphrase != null && "".equals(passphrase)) {
        //      //设置带口令的密钥
        //         jsch.addIdentity(privateKey, passphrase);
        //     } else {
        //      //设置不带口令的密钥
        //         jsch.addIdentity(privateKey);
        //     }
        // }
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no"); // 不验证 HostKey
        session.setConfig(config);
        try {
            session.connect();
        } catch (Exception e) {
            if (session.isConnected())
                session.disconnect();
            log.error("连接服务器失败,请检查主机[" + host + "],端口[" + port
                    + "],用户名[" + username + "],密码是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
        }
        channel = session.openChannel("sftp");
        try {
            channel.connect();
        } catch (Exception e) {
            if (channel.isConnected())
                channel.disconnect();
            log.error("连接服务器失败,请检查主机[" + host + "],端口[" + port
                    + "],用户名[" + username + "],密码是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
        }
        sftp = (ChannelSftp) channel;
        return new SFTPServer(session, channel, sftp);
    }

    /**
     * 断开连接
     */
    public static void disConnect(SFTPServer sftpServer) {
        ChannelSftp sftp = sftpServer.getSftp();
        if(null != sftp){
            sftp.disconnect();
            sftp.exit();
        }
        Channel channel = sftpServer.getChannel();
        if(null != channel){
            channel.disconnect();
        }
        Session session = sftpServer.getSession();
        if(null != session){
            session.disconnect();
        }
    }

    /**
     * <p>Title:download</p>
     * <p>Description: 使用默认配置的sftp服务器下载文件</p>
     * @param remoteDir sftp服务器目录
     * @param remoteFileName sftp服务器上文件名
     * @param localDir 本地文件夹
     * @param localFileName 本地文件名
     */
    public static void download(String remoteDir, String remoteFileName,
                                String localDir, String localFileName) throws Exception {
        download(SFTP_CONFIG, remoteDir, remoteFileName, localDir, localFileName);
    }


    /**
     * <p>Title:download</p>
     * <p>Description: 使用自定义sftp服务器下载文件</p>
     * @param sftpConfig sftp配置信息
     * @param remoteDir sftp服务器目录
     * @param remoteFileName sftp服务器上文件名
     * @param localDir 本地文件夹
     * @param localFileName 本地文件名
     */
    public static void download(SFTPConfig sftpConfig,
                                String remoteDir, String remoteFileName,
                                String localDir, String localFileName) throws Exception {
        SFTPServer sftpServer = getConnect(sftpConfig);//建立连接
        ChannelSftp sftp = sftpServer.getSftp();// sftp操作类
        try {
            // 1、设置远程FTP目录
            if (remoteDir != null && !remoteDir.equals("")) {
                sftp.cd(remoteDir); //进入目录
                log.info("change to work directory:[" + remoteDir + "]");
            }
            OutputStream out = new FileOutputStream(new File(localDir, localFileName));
            sftp.get(remoteFileName, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        } finally {
            disConnect(sftpServer);
        }
    }

    /*
     * <p>Title:ls</p>
     * <p>Description: ls指定目录文件</p>
     * @param remoteDir
     * @return java.util.List<java.lang.String>
     */
    public static List<String> ls(String remoteDir) throws Exception {
        SFTPServer sftpServer = getConnect(SFTP_CONFIG);//建立连接
        ChannelSftp sftp = sftpServer.getSftp();
        Vector ls = sftp.ls(remoteDir);
        return new ArrayList<>();
    }

}
