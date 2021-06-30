/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: FTPServer.java
 *
 * Description：
 *
 * History:
 * v1.0.0, hhszg, 2017年5月16日, Create
 */
package com.hh.springbootdev.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * 
 * @author hhszg
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class FTPServer
{
    private Logger logger = LoggerFactory.getLogger(FTPServer.class);
    
    /** 
     * ftp服务器地址
     **/  
    private String host;
    
    /** ftp 端口号 默认21 */  
    private int port = 21;
    
    /** ftp服务器用户名 */  
    private String userName;  
    
    /** ftp服务器密码 */  
    private String password;
    
    /**
     * 连接超时时间，单位ms
     */
    private Integer timeOut = 30000;
    
    /**
     * 编码格式
     */
    private String encoding = "utf-8";
    
    /**
     * FTPClient
     */
    private FTPClient client;

    public FTPServer()
    {
    }
    
    public FTPServer(String host, String userName, String password)
    {
        this.host = host;
        this.userName = userName;
        this.password = password;
    }

    public FTPServer(String host, int port, String userName, String password)
    {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public FTPServer(String host, int port, String userName, String password, Integer timeOut, String encoding)
    {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.timeOut = timeOut;
        this.encoding = encoding;
    }

    public FTPClient getClient()
    {
        return client;
    }

    public void setClient(FTPClient client)
    {
        this.client = client;
    }

    public void connect() throws Exception
    {
        this.client = new FTPClient();
        this.client.setBufferSize(8192);
        //设置超时时间
        client.setConnectTimeout(this.getTimeOut());
        try
        {  
            // 1、连接服务器  
            if (!client.isConnected())
            {
                // 如果采用默认端口，可以使用client.connect(host)的方式直接连接FTP服务器  
                client.connect(this.getHost(), this.getPort());  
                // 登录  
                client.login(this.getUserName(), this.getPassword());
                // 获取ftp登录应答码  
                int reply = client.getReplyCode();
                // 验证是否登陆成功  
                if (!FTPReply.isPositiveCompletion(reply)) 
                {
                    logger.info("connect FTP fail,user or password error");  
                    client.disconnect();
                    throw new Exception("connect FTP fail,user or password error");  
                }
                else
                {
                    logger.info("FTP connect success, IP:" + this.getHost() + ",PORT:" + this.getPort());  
                }  
                // 2、设置连接属性  
//                client.setControlEncoding(ftpServer.getEncoding());  
                // 设置以二进制方式传输  
                client.setFileType(FTPClient.BINARY_FILE_TYPE);    
                //client.enterLocalPassiveMode();
                client.setRemoteVerificationEnabled(true);
            }
        }
        catch (SocketException e)
        {  
            try
            {  
                client.disconnect();
            }
            catch (IOException e1)
            {
                logger.error("connect ftp fail:" + e1.getMessage());
            }
            logger.error("connect ftp fail:" + e.getMessage());  
            throw new Exception("connect ftp fail:" + e.getMessage());  
        }
        catch (IOException e)
        {
            logger.error("connect ftp fail:" + e.getMessage());  
            throw new Exception("connect ftp fail:" + e.getMessage()); 
        }  
    }
    
    public void closeConnect()
    {  
        try
        {
            this.client.disconnect();
            logger.info("close connect success!!!");  
        }
        catch (IOException e)
        {  
            logger.error("close connect fail!!!", e);  
        }
    }
    
    public List<FTPFile> existFiles(String remoteDir)
    {
        List<FTPFile> files = new ArrayList<FTPFile>();
        try
        {
            if (remoteDir != null && !remoteDir.equals(""))
            {
                this.client.changeWorkingDirectory(remoteDir);
                logger.info("change work directory[" + remoteDir + "]");  
            }
            FTPFile[] ftpFiles = this.client.listFiles();
            if (ftpFiles != null && ftpFiles.length > 0)
            {
                for (FTPFile ftpFile : ftpFiles)
                {
                    logger.info("find file [" + ftpFile.getName() + "]");
                    files.add(ftpFile);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("find files error:" + e.getMessage());  
            //throw new Exception("find files error:" + e.getMessage());  
        }
        return files;
    }
    
    public void changeToParentDirectory()
    {
        try
        {
            this.client.changeToParentDirectory();
            logger.info("change to parent directory success");
        }
        catch (Exception e)
        {
            logger.error("change to parent directory fail:" + e.getMessage());  
        }
    }
    
    public List<File> download(String localDir, String remoteDir, String tmpPostfix, Set<String> needDownloads, boolean isDelete) throws Exception
    {
        List<File> files = new ArrayList<File>();
        InputStream is = null;  
        File downloadFile = null;  
        try
        {  
            // 1、设置远程FTP目录  
            if (remoteDir != null && !remoteDir.equals(""))
            {
                client.changeWorkingDirectory(remoteDir);
                logger.info("change to work directory:[" + remoteDir + "]");  
            }
            // 2、读取远程文件  
            FTPFile[] ftpFiles = client.listFiles(); 
            if (ftpFiles.length == 0)
            {
                logger.warn("no files!!!");
                return null;
            }
            logger.info("========== download files [START] ==========");  
            // 3、保存文件到本地  
            for (FTPFile file : ftpFiles) 
            {
                if (needDownloads != null && !needDownloads.contains(file.getName()))
                { // 只下载包含的文件
                    continue;
                }
                is = client.retrieveFileStream(file.getName());  
                if (is == null)
                {
                    throw new Exception(file.getName() + " download fail, check it");
                }
                String fileName = localDir + File.separator + file.getName();
                File targetFile = new File(fileName);
                if(targetFile.exists()){
                    targetFile.delete();
                }
                downloadFile = new File(fileName + tmpPostfix);
                FileOutputStream fos = FileUtils.openOutputStream(downloadFile);  
                IOUtils.copy(is, fos);
                client.completePendingCommand();
                IOUtils.closeQuietly(is);
                IOUtils.closeQuietly(fos);
                downloadFile.renameTo(targetFile); // 重命名
                files.add(downloadFile);
                if (isDelete)
                {
                    // 如果需要删除则删除，删除的是FTP上的
                    client.deleteFile(file.getName());
                }
            }
            logger.info("file download success,path:" + localDir);  
            return files;
        }
        catch (IOException e)
        {
            logger.error("file download error:" + e.getMessage());  
            throw new Exception("file download error:" + e.getMessage());  
        }
    }
    
    public List<File> download(String localDir, String remoteDir, String tmpPostfix, boolean isDelete) throws Exception
    {
        return download(localDir, remoteDir, tmpPostfix, null, isDelete);
    }
    
    public boolean upload(String path, String filename, InputStream input)
    {
        boolean success = false;
        try
        {
            if (path != null && path.length() > 0)
            {
                this.client.changeWorkingDirectory(path);
            }
            this.client.storeFile(filename, input);
            input.close();  
            success = true;
            logger.info("file upload success, file is " + filename);
        }
        catch (Exception e)
        {
            logger.error("file upload fail:" + e.getMessage());  
        }
        return success;
    }
    
    public long getFileSize(String remoteDir, String fileName)
    {
        long fileSize = 0L;
        InputStream is = null;
        try
        {
            // 1、设置远程FTP目录  
            if (remoteDir != null && !remoteDir.equals(""))
            {
                client.changeWorkingDirectory(remoteDir);
                logger.info("change to work directory:[" + remoteDir + "]");  
            }
            is = client.retrieveFileStream(fileName);
            if (is == null)
            {
                throw new Exception(fileName + " check size fail, check it");
            }
            int ch = 0;
            byte[] bytes = new byte[1024];
            while ((ch = is.read(bytes)) != -1)
            {
                fileSize += ch;
            }
            /*
            int size = 0;
            // read()方法返回的是下一个字节的内容
            while ((ch = is.read()) >= 0)
            { 
                fileSize += ch; 
                size++;
            }
            System.out.println(size);
            */
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                    client.completePendingCommand();
                }
                catch (IOException e)
                {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return fileSize;
    }
    
    public boolean isConnected()
    {
        if (client != null && client.isAvailable())
        {
            return client.isConnected();
        }
        else 
        {
            return false;
        }
    }
    
    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getTimeOut()
    {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut)
    {
        this.timeOut = timeOut;
    }

    public String getEncoding()
    {
        return encoding;
    }

    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }

    /**
     * 切换目录，如果目录不存在则创建目录
     * @param targetPath
     * @return boolean
     */
    public boolean changeAndMakeDir(String targetPath)
    {
        if (!StringUtils.isNotBlank(targetPath)) return true;
        try
        {
        	this.client.dele(targetPath); // 删除服务器上的指定文件
            //尝试切入目录
            if (this.client.changeWorkingDirectory(targetPath)) return true;
            //目前前后不能有/
            String[] arr =  targetPath.split("/");
            StringBuffer sbfDir = new StringBuffer();
            //循环生成子目录
            for (String s : arr)
            {
                if (StringUtils.isNotBlank(sbfDir.toString()))
                {
                    sbfDir.append("/");
                }
                sbfDir.append(s);
                //尝试切入目录
                if (this.client.changeWorkingDirectory(sbfDir.toString()))
                    continue;
                if (!this.client.makeDirectory(sbfDir.toString()))
                {
                    logger.error("make directory:[" + sbfDir.toString() + "] error");
                    return false;
                }
                logger.error("make directory:[" + sbfDir.toString() + "] success");
            }
            //将目录切换至指定路径
            return this.client.changeWorkingDirectory(targetPath);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean changeFileType(Integer fileType)
    {
        try
        {
            return this.client.setFileType(fileType);
        }
        catch (IOException e)
        {
            logger.error("change file type error");
            return false;
        }
    }

}
