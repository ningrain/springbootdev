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

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <p>Title: IPUtil</p>
 * <p>Description: IP地址工具类</p>
 *
 * @author jiangningning
 */
public class IPUtil {

    private IPUtil() {
    }

    private static final String IPV4PATTERN = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";

    private static final String IPV6PATTERN = "^\\s*((([0-9A-Fa-f]{1,4}:){7}(([0-9A-Fa-f]{1,4})|:))|(([0-9A-Fa-f]{1,4}:){6}(:|((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})|(:[0-9A-Fa-f]{1,4})))|(([0-9A-Fa-f]{1,4}:){5}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:)(:[0-9A-Fa-f]{1,4}){0,4}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(:(:[0-9A-Fa-f]{1,4}){0,5}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})))(%.+)?\\s*$";

    public static String ipStrValidation(String ipStr) {

        String[] ips = ipStr.split(",", -1);
        List<String> ipList = new ArrayList<>();
        for (String ip : ips) {
            ip = ip.trim();
            if (ip.contains("-")) {
                String[] splitIp = ip.split("-");
                for (String s : splitIp) {
                    if (s.contains("/")) {
                        s = s.substring(0, s.indexOf("/"));
                    }
                    ipList.add(s);
                }
            } else {
                if (ip.contains("/")) {
                    ip = ip.substring(0, ip.indexOf("/"));
                }
                ipList.add(ip);
            }
        }
        for (String ip : ipList) {
            if (!ip.matches(IPV4PATTERN) && !ip.matches(IPV6PATTERN)) {
                return ip + "非正确的IP地址格式";
            }
        }
        List<String> totalIpList = getIpList(ips);
        if (totalIpList.size() == 0) {
            return "IP地址段不正确";
        }
        if (totalIpList.size() != new HashSet<>(totalIpList).size()) {
            return "IP地址有重复";
        }
        return "success";
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
        for (String ip : ips) {
            int index = ip.indexOf("-");
            if (index != -1) { // ip地址段
                String beginIp = ip.substring(0, index);
                String endIp = ip.substring(index + 1);
                if (ip.contains(":")) { // ipv6
                    tempList = getIpv6AddrList(beginIp, endIp);
                } else { // ipv4
                    tempList = getIpv4AddrList(beginIp, endIp);
                }
                ipList.addAll(tempList);
            } else { // 单ip
                if (ip.contains("/")) {
                    ip = ip.substring(0, ip.indexOf("/"));
                }
                ipList.add(ip);
            }
        }
        return ipList;
    }

    /**
     * 根据起始IP和终止IP获取IPv4地址列表
     *
     * @param beginIp 起始ip
     * @param endIp 结尾ip
     * @return iplist
     */
    private static List<String> getIpv4AddrList(String beginIp, String endIp) {
        List<String> ipAddrList = new ArrayList<>();
        long beginIpLong = ipv4toLong(beginIp);
        long endIpLong = ipv4toLong(endIp);
        for (long ip = beginIpLong; ip <= endIpLong; ip++) {
            String ipAddr = long2ipv4(ip);
            ipAddrList.add(ipAddr);
        }
        return ipAddrList;
    }

    /**
     * 根据起始IP和终止IP获取IPv6地址列表
     *
     * @param beginIp 起始ip
     * @param endIp 结尾ip
     * @return iplist
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

    public static String int2ipv6(BigInteger bigIntIp) {
        try {
            return hexToIP(bigIntIp.toString(16));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BigInteger ipv6toInt(String strIp) {
        return new BigInteger(ipToHex(strIp), 16);
    }

    public static String long2ipv4(Long longIp) {
        try {
            return hexToIP(Long.toHexString(longIp));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long ipv4toLong(String beginIp) {
        return Long.parseLong(ipToHex(beginIp), 16);
    }


    /**
     * 将字符串形式的ip地址转换为hex形式字符串，支持IPV4和IPV6两个版本
     *
     * @param ip 字符串形式的ip地址
     * @return hex字符串
     */
    public static String ipToHex(String ip) {
        ip = ip.replace(" ", "");
        int endIndex = ip.indexOf("/");
        if (endIndex > 0) {
            ip = ip.substring(0, endIndex);
        }
        String hexs;
        if (ip.contains(":")) {
            hexs = ipv6ToHex(ip);
        } else {
            hexs = ipv4ToHex(ip);
        }
        return hexs;
    }

    /**
     * 将字符串形式的ip地址转换为byte数组，支持IPV4和IPV6两个版本
     *
     * @param ip 字符串形式的ip地址
     * @return hex字符串
     */
    private static byte[] ipToBytes(String ip) {
        ip = ip.replace(" ", "");
        int endIndex = ip.indexOf("/");
        if (endIndex > 0) {
            ip = ip.substring(0, endIndex);
        }
        byte[] bytes;
        if (ip.contains(":")) {
            bytes = ipv6ToBytes(ip);
        } else {
            bytes = ipv4ToBytes(ip);
        }
        return bytes;
    }

    /**
     * 将hex形式 的ip数据转成字符串形式的ip地址，支持IPV4和IPV6两个版本
     *
     * @param hex 将hex形式 的ip数据
     * @return ip地址
     */
    public static String hexToIP(String hex) throws UnknownHostException {
        byte[] unsignedBytes = ByteUtil.hexToBytes(hex);
        // 去除符号位
        try {
            String ip = InetAddress.getByAddress(unsignedBytes).toString();
            return ip.substring(ip.indexOf('/') + 1).trim();
        } catch (UnknownHostException e) {
            throw e;
        }
    }

    private static String byteToIP(byte[] bytes) throws UnknownHostException {
        // 去除符号位
        try {
            String ip = InetAddress.getByAddress(bytes).toString();
            return ip.substring(ip.indexOf('/') + 1).trim();
        } catch (UnknownHostException e) {
            throw e;
        }
    }

    private static String ipv6ToHex(String ipv6) {
        byte[] bytes = ipv6ToBytes(ipv6);
        return ByteUtil.bytesToHexString(bytes);
    }

    /**
     * 字符串形式的ipv6地址hex形式的字符串
     *
     * @param ipv6 字符串形式的IP地址
     * @return 长度为32的hex形式的字符串
     */
    private static byte[] ipv6ToBytes(String ipv6) {
        byte[] ret = new byte[16];
        // ret[0] = 0;
        int ib = 15;
        // ipv4混合模式标记
        boolean comFlag = false;
        // 去掉开头的冒号
        if (ipv6.startsWith(":")) {
            ipv6 = ipv6.substring(1);
        }
        String groups[] = ipv6.split(":");
        // 反向扫描
        for (int ig = groups.length - 1; ig > -1; ig--) {
            if (groups[ig].contains(".")) {
                // 出现ipv4混合模式
                byte[] temp = ipv4ToBytes(groups[ig]);
                ret[ib--] = temp[3];
                ret[ib--] = temp[2];
                ret[ib--] = temp[1];
                ret[ib--] = temp[0];
                comFlag = true;
            } else if ("".equals(groups[ig])) {
                // 出现零长度压缩,计算缺少的组数
                int zlg = 9 - (groups.length + (comFlag ? 1 : 0));
                // 将这些组置0
                while (zlg-- > 0) {
                    ret[ib--] = 0;
                    ret[ib--] = 0;
                }
            } else {
                int temp = Integer.parseInt(groups[ig], 16);
                ret[ib--] = (byte) temp;
                ret[ib--] = (byte) (temp >> 8);
            }
        }
        return ret;
    }

    private static String ipv4ToHex(String ipv4) {
        byte[] bytes = ipv4ToBytes(ipv4);
        return ByteUtil.bytesToHexString(bytes);
    }

    /**
     * ipv4地址转有符号byte[5]
     *
     * @param ipv4 字符串的IPV4地址
     * @return big integer number
     */
    private static byte[] ipv4ToBytes(String ipv4) {
        String[] groups = ipv4.split("\\.");
        byte[] ret = new byte[4];
        for (int i = 0; i < 4; i++) {
            ret[i] = (byte) Integer.parseInt(groups[i]);
        }
        return ret;
    }
}
