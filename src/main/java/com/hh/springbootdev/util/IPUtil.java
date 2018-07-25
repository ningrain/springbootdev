/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: IPUtil.java</p>
 *
 * @author jiangningning
 * @date 2018/7/24
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/7/24 Create
 */
package com.hh.springbootdev.util;

import sun.net.util.IPAddressUtil;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <p>Title: IPUtil</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class IPUtil {

    private static final String IPV4PATTERN = "";

    private static final String IPV6PATTERN = "";

    public static String ipStrValidation(String ipStr) {

        String[] ips = ipStr.split(",", -1);
        for (String ip : ips) {
            if (!ip.matches(IPV4PATTERN) || !ip.matches(IPV6PATTERN)) {
                return ip + "非正确的IP地址格式";
            }
        }
        List<String> ipList = getIpList(ips);
        if (ipList.size() != new HashSet<>(ipList).size()) {
            return "IP地址有重复";
        }
        return "";
    }


    /**
     * <p>Title:getIpList</p>
     * <p>Description: 根据传入ip数组获取所有ip(兼容ipv4和ipv6)</p>
     *
     * @param ips ip数组
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getIpList(String[] ips) {
        List<String> ipList = new ArrayList<>();
        List<String> tempList;
        try {
            for (String ip : ips) {
                int index = ip.indexOf("-");
                if (index != -1) { // ip地址段
                    String beginIp = ip.substring(0, index);
                    String endIp = ip.substring(index + 1);
                    if (InetAddress.getByName(beginIp).getAddress().length == 4 && InetAddress.getByName(endIp).getAddress().length == 4) { // ipv4
                        tempList = getIpv4AddrList(beginIp, endIp);
                    } else { // ipv6
                        tempList = getIpv6AddrList(beginIp, endIp);
                    }
                    ipList.addAll(tempList);
                } else { // 单ip
                    ipList.add(ip);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ipList;
    }


    /**
     * 将ipv4字符串转换成long类型
     */
    public static long ipv42long(String strIp) {
        long[] ip = new long[4];
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        // 将每个.之间的字符串转换成long型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3 + 1));
        // 进行左移位处理
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    /**
     * 将long类型的ipv4转换成字符串
     */
    public static String long2ipv4(long ip) {
        // 直接右移24位
        // 将高8位置0，然后右移16
        // 将高16位置0，然后右移8位
        // 将高24位置0
        return String.valueOf(ip >> 24) +
                "." +
                ((ip & 0x00FFFFFF) >> 16) +
                "." +
                ((ip & 0x0000FFFF) >> 8) +
                "." +
                (ip & 0x000000FF);
    }

    /**
     * 根据起始IP和终止IP获取IPv4地址列表
     *
     * @param beginIp
     * @param endIp
     * @return
     */
    private static List<String> getIpv4AddrList(String beginIp, String endIp) {
        List<String> ipAddrList = new ArrayList<>();
        long beginIpLong = ipv42long(beginIp);
        long endIpLong = ipv42long(endIp);
        for (long ip = beginIpLong; ip <= endIpLong; ip++) {
            String ipAddr = long2ipv4(ip);
            ipAddrList.add(ipAddr);
        }
        return ipAddrList;
    }

    /**
     * 根据起始IP和终止IP获取IPv6地址列表
     *
     * @param beginIp
     * @param endIp
     * @return
     */
    private static List<String> getIpv6AddrList(String beginIp, String endIp) {
        List<String> ipAddrList = new ArrayList<>();
        BigInteger beginIpBigInt = ipv6toInt(beginIp);
        BigInteger endIpBigInt = ipv6toInt(endIp);
        for (int i = 0; i <= endIpBigInt.subtract(beginIpBigInt).intValue(); i++) {
            String ipAddr = int2ipv6(beginIpBigInt.add(BigInteger.valueOf(i)));
            ipAddrList.add(ipAddr);
        }
        return ipAddrList;
    }


    public static BigInteger ipv6toInt(String ipv6) {

        int compressIndex = ipv6.indexOf("::");
        if (compressIndex != -1) {
            String part1s = ipv6.substring(0, compressIndex);
            String part2s = ipv6.substring(compressIndex + 1);
            BigInteger part1 = ipv6toInt(part1s);
            BigInteger part2 = ipv6toInt(part2s);
            int part1hasDot = 0;
            char ch[] = part1s.toCharArray();
            for (char c : ch) {
                if (c == ':') {
                    part1hasDot++;
                }
            }
            // ipv6 has most 7 dot
            return part1.shiftLeft(16 * (7 - part1hasDot)).add(part2);
        }
        String[] str = ipv6.split(":");
        BigInteger big = BigInteger.ZERO;
        for (int i = 0; i < str.length; i++) {
            //::1
            if (str[i].isEmpty()) {
                str[i] = "0";
            }
            big = big.add(BigInteger.valueOf(Long.valueOf(str[i], 16))
                    .shiftLeft(16 * (str.length - i - 1)));
        }
        return big;
    }

    public static String int2ipv6(BigInteger big) {
        String str = "";
        BigInteger ff = BigInteger.valueOf(0xffff);
        for (int i = 0; i < 8; i++) {
            str = big.and(ff).toString(16) + ":" + str;

            big = big.shiftRight(16);
        }
        //the last :
        str = str.substring(0, str.length() - 1);

        return str.replaceFirst("(^|:)(0+(:|$)){2,8}", "::");
    }

    public static boolean isIPV6Format(String ip) {
        ip = ip.trim();

        //in many cases such as URLs, IPv6 addresses are wrapped by []
        if (ip.substring(0, 1).equals("[") && ip.substring(ip.length() - 1).equals("]"))

            ip = ip.substring(1, ip.length() - 1);

        return (1 < Pattern.compile(":").split(ip).length)
                //a valid IPv6 address should contains no less than 1,
                //and no more than 7 “:” as separators
                && (Pattern.compile(":").split(ip).length <= 8)

                //the address can be compressed, but “::” can appear only once
                && (Pattern.compile("::").split(ip).length <= 2)

                //if a compressed address
                && (Pattern.compile("::").split(ip).length == 2)

                //if starts with “::” – leading zeros are compressed
                ? (((ip.substring(0, 2).equals("::"))
                ? Pattern.matches("^::([\\da-f]{1,4}(:)){0,4}(([\\da-f]{1,4}(:)[\\da-f]{1,4})|([\\da-f]{1,4})|((\\d{1,3}.){3}\\d{1,3}))", ip)
                : Pattern.matches("^([\\da-f]{1,4}(:|::)){1,5}(([\\da-f]{1,4}(:|::)[\\da-f]{1,4})|([\\da-f]{1,4})|((\\d{1,3}.){3}\\d{1,3}))", ip)))

                //if ends with "::" - ending zeros are compressed
                : ((ip.substring(ip.length() - 2).equals("::"))
                ? Pattern.matches("^([\\da-f]{1,4}(:|::)){1,7}", ip)
                : Pattern.matches("^([\\da-f]{1,4}:){6}(([\\da-f]{1,4}:[\\da-f]{1,4})|((\\d{1,3}.){3}\\d{1,3}))", ip));
    }
}
