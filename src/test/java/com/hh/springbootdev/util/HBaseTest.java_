/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: HBaseTest.java</p>
 *
 * @author jiangningning
 * @date 2018/8/17
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/8/17 Create
 */
package com.hh.springbootdev.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;
import java.util.Arrays;

/**
 * <p>Title: HBaseTest</p>
 * <p>Description: </p>
 * @author jiangningning
 */
public class HBaseTest {

    public static void main(String[] args) {
        String createTableName = "mytable2";
        Configuration configuration = HBaseConfiguration.create();
        // configuration.set("hbase.master", "111.231.109.105:60010");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "111.231.109.105");
        //configuration.set("hbase.master", "10.10.2.66:600000");
        System.out.println("start create table ......");
        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
            // HTableDescriptor tableDescriptor = new HTableDescriptor(createTableName);
            // tableDescriptor.addFamily(new HColumnDescriptor("column1"));
            // tableDescriptor.addFamily(new HColumnDescriptor("column2"));
            // tableDescriptor.addFamily(new HColumnDescriptor("column3"));
            // hBaseAdmin.createTable(tableDescriptor);
            System.out.println(Arrays.toString(hBaseAdmin.listTables()));
            hBaseAdmin.close();
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end create table ......");
    }




}
