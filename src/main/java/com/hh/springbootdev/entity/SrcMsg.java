package com.hh.springbootdev.entity;

import java.util.*;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/6/9
 * Time: 15:06
 */
public class SrcMsg {

    private String ip;

    private Integer port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public SrcMsg(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }
}


class Test {
    public static void parse2Array() {
        SrcMsg srcMsg1 = new SrcMsg("192.168.0.1", 80);
        SrcMsg srcMsg2 = new SrcMsg("192.168.0.1", 81);
        SrcMsg srcMsg3 = new SrcMsg("192.168.0.1", 82);
        SrcMsg srcMsg4 = new SrcMsg("192.168.0.2", 80);
        SrcMsg srcMsg5 = new SrcMsg("192.168.0.2", 83);
        SrcMsg srcMsg6 = new SrcMsg("192.168.0.3", 80);
        SrcMsg srcMsg7 = new SrcMsg("192.168.0.3", 81);
        SrcMsg srcMsg8 = new SrcMsg("192.168.0.3", 82);
        SrcMsg srcMsg9 = new SrcMsg("192.168.0.3", 83);
        SrcMsg srcMsg10 = new SrcMsg("192.168.0.3", 84);
        List<SrcMsg> list = new ArrayList<>();
        list.add(srcMsg1);
        list.add(srcMsg2);
        list.add(srcMsg3);
        list.add(srcMsg4);
        list.add(srcMsg5);
        list.add(srcMsg6);
        list.add(srcMsg7);
        list.add(srcMsg8);
        list.add(srcMsg9);
        list.add(srcMsg10);

        Set<String> ipSets = new HashSet<>();
        list.forEach(srcMsg -> {
            ipSets.add(srcMsg.getIp());
        });
        String[] ips = new String[ipSets.size()];
        Object[] ipss = ipSets.toArray();
        for (int i = 0; i < ipss.length; i++) {
            ips[i] = (String) ipss[i];
        }
        System.out.println(Arrays.toString(ips));

        System.out.println("#############################################");

        Integer portMaxCount = 0; // 一个源ip 对应源端口个数的最大值

        for (String ip : ipSets) {
            Set<Integer> portSet = new HashSet<>();
            list.forEach(srcMsg -> {
                if (srcMsg.getIp().equals(ip)) {
                    portSet.add(srcMsg.getPort());
                }
            });
            if (portMaxCount < portSet.size()) {
                portMaxCount = portSet.size();
            }
        }

        Integer[][] ports = new Integer[ipSets.size()][portMaxCount];
        for (int j = 0; j < ips.length; j++) {
            int a = 0;
            for (SrcMsg aList : list) {
                if (aList.getIp().equals(ips[j])) {
                    ports[j][a] = aList.getPort();
                    a++;
                }
            }
        }

        // 打印二维数组
        for (int i = 0; i < ipSets.size(); i++) {
            for (int j = 0; j < portMaxCount; j++) {
                System.out.print(ports[i][j] + " ");
            }
            System.out.println("\n");
        }

    }

    public static void main(String[] args) {
        parse2Array();
    }
}