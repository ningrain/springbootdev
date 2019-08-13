/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: HdfsClient.java</p>
 *
 * @author jiangningning
 * @date 2019/5/28
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/5/28 Create
 */
package com.hh.springbootdev.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>Title: HdfsClient</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class HdfsClient {

    public void testMkdirs() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        // 配置在集群上运行
        // configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
        // FileSystem fs = FileSystem.get(configuration);

        FileSystem fs = FileSystem.get(new URI("hdfs://111.231.109.105:9000"), configuration, "root");

        // 2 创建目录
        fs.mkdirs(new Path("/1108/daxian/banzhang"));

        // 3 关闭资源
        fs.close();
    }
}