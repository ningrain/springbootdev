/*
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: IpAddrUtil.java</p>
 *
 * @author jiangningning
 * @date 2021/4/27
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2021/4/27 Create
 */
package com.hh.springbootdev.util;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import java.io.File;
import java.lang.reflect.Method;

/**
 * <p>Title: IpAddrUtil</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class IpAddrUtil {

    public static String getRegionInfo(String ip) {
        //db
        String dbPath = IpAddrUtil.class.getResource("/ip2region.db").getPath();
        File file = new File(dbPath);
        if (!file.exists()) {
            System.out.println("Error: Invalid ip2region.db file");
        }
        //查询算法
        int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
        //DbSearcher.BINARY_ALGORITHM //Binary
        //DbSearcher.MEMORY_ALGORITYM //Memory
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);

            //define the method
            Method method = null;
            switch (algorithm) {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }

            DataBlock dataBlock;
            if (!Util.isIpAddress(ip)) {
                System.out.println("Error: Invalid ip address");
            }
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            return dataBlock.getRegion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCountryInfo(String ip) {
        String regionInfo = getRegionInfo(ip);
        if (regionInfo != null) {
            String[] split = regionInfo.split("\\|");
            return split[0];
        }
        return null;
    }

    public static String getProvinceInfo(String ip) {
        String regionInfo = getRegionInfo(ip);
        if (regionInfo != null) {
            String[] split = regionInfo.split("\\|");
            return split[2];
        }
        return null;
    }

    public static String getCityInfo(String ip) {
        String regionInfo = getRegionInfo(ip);
        if (regionInfo != null) {
            String[] split = regionInfo.split("\\|");
            return split[3];
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        // String ip = "47.108.184.171";
        // String ip = "60.174.248.98";
        // String ip = "8.8.8.8";
        String ip = "36.106.167.131";
        String regionInfo = getRegionInfo(ip);
        System.out.println(regionInfo);
        String countryInfo = getCountryInfo(ip);
        System.out.println(countryInfo);
        String provinceInfo = getProvinceInfo(ip);
        System.out.println(provinceInfo);
        String cityIpString = getCityInfo(ip);
        System.out.println(cityIpString);
    }

}
