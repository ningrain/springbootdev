package com.hh.springbootdev.util;

import com.alibaba.druid.support.http.util.IPAddress;
import com.hh.springbootdev.exception.CustomException;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sun.net.util.IPAddressUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/7/17
 * Time: 21:49
 */
@RunWith(JUnit4.class)
public class UtilTest {

    @Test
    public void test1() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key1", "AAmap1");
        map1.put("key2", 56);
        map1.put("key3", new Date().getTime());
        Map<String, Object> map2 = new HashMap<>();
        map2.put("key1", "AAmap2");
        map2.put("key2", 45);
        map2.put("key3", new Date().getTime());
        Map<String, Object> map3 = new HashMap<>();
        map3.put("key1", "AAmap3");
        map3.put("key2", 34);
        map3.put("key3", new Date().getTime());
        list.add(map1);
        list.add(map2);
        list.add(map3);
        System.out.println(list);
        sort(list, "key2");
        System.out.println(list);
        Map<String, Long> map = new HashMap<>();
        map.put("a", 15L);
        map.put("b", 15L);
        map.put("c", 15L);
        System.out.println(map.values().stream().mapToLong(rtBps -> rtBps).average().getAsDouble());
    }

    private static void sort(List<Map<String, Object>> data, String sortName) {

        data.sort((Comparator<Map>) (o1, o2) -> {
            Integer a = (Integer) o1.get(sortName);
            Integer b = (Integer) o2.get(sortName);

            // 升序
            return a.compareTo(b);

            // 降序
            // return b.compareTo(a);
        });
    }

    @Test
    public void test2(){
        Map<String, Object> map = new HashMap<>();
        List<String> strs = new ArrayList<>();
        strs.add("AA");
        strs.add("BB");
        map.put("list", strs);
        System.out.println(((List) map.get("list")).get(0));
    }

    @Test
    public void test3(){
        double d = Double.MIN_VALUE;
        float f = (float) d;
        System.out.println(f);
        System.out.println(String.format("%.4s", "3.58954"));
        Map<String, Boolean> map = new HashMap<>();
        map.put("AA", false);
        System.out.println(MapUtils.getBoolean(map, "AA"));
        float f1 = 1670;
        System.out.println(f1/1000);
        System.out.println(1654.5/1000.0);
    }

    @Test
    public void test4(){
        InputStream in = new ByteArrayInputStream("abcdefghijk".getBytes());
        byte[] buffer1 = new byte[4];
        byte[] buffer2 = new byte[2];
        try {
            in.read(buffer1, 2, 4);
            in.read(buffer2, 0, 2);
            System.out.println(new String(buffer1));
            System.out.println(new String(buffer2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() throws UnsupportedEncodingException {
        Integer a = 1;
        System.out.println(a.compareTo(2));
        System.out.println(Math.round(45.3));
        float f = (float) (Math.round(45.623 * 10000)) / 10000;
        System.out.println(f);
        String s1 = "a";
        String s2 = "中国";
        System.out.println(s2.getBytes("GBK").length);
        System.out.println(s2.getBytes("UTF-8").length);
    }

    @Test
    public void test6(){
        Integer num = 4;
        System.out.println(Integer.toBinaryString(num));
        // 192.168.0.1
        num = num << 3;
        System.out.println(num);
        System.out.println((192L << 24) + (168L << 16) + (0L << 8) + 1L);
    }

    @Test
    public void test7() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("34e5:2319:56da:632a:c3d5:9087:b3c3:60e2");
        byte[] address = inetAddress.getAddress();
        System.out.println(address.length);
    }

    @Test
    public void test8() throws UnknownHostException {
        List<String> ipList1 = IPUtil.getIpList(new String[]{"192.168.0.1", "192.168.0.2-192.168.0.45"});
        List<String> ipList2 = IPUtil.getIpList(new String[]{"34e5:2319:56da:632a:c3d5:9087:b3c3:60e2",
                "34e5:2319:56da:632a:c3d5:9087:b3c3:60e2-34e5:2319:56da:632a:c3d5:9087:b3c3:60f3"});
        /*ipList2.forEach(System.out::println);
        String s = IPUtil.ipStrValidation("34e5:2319:56da:632a:c3d5:9087:b3c3:60e2,34e5:2319:56da:632a:c3d5:9087:b3c3:60e2-34e5:2319:56da:632a:c3d5:9087:b3c3:60f3");
        System.out.println(s);*/
        // System.out.println(InetAddress.getByName("::ffff:192.1.56.10").getAddress().length);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(1531670400 * 3);
        System.out.println(1531670400 * 3L);
    }

    @Test
    public void test9(){
        System.out.println(IPAddressUtil.isIPv6LiteralAddress("::ffff:192.1.56.10"));
        System.out.println(IPAddressUtil.isIPv6LiteralAddress("::"));
        // System.out.println(IPUtil.ipStrValidation("::ffff:192.1.56.10"));
        // IPAddress ipAddress = new IPAddress("::ffff:192.1.56.10");
        // System.out.println(ipAddress.getIPAddress());
        System.out.println(0xff00);
    }

    @Test
    public void test10(){
        // Mythread t1 = ;
        // Mythread t2 = new Mythread();
        for (int i = 0; i < 10; i++) {
            new Thread(new Mythread()).start();
        }
    }


    class Mythread implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "-" + new Date().getTime());
        }
    }

    @Test
    public void test11(){
        System.out.println(IPUtil.ipStrValidation("192.168.0.2-:ffff:192.1.56.10"));
    }

    @Test
    public void test12(){
        try {
            String s = IPUtil.ipToHex("fe80:0:0:0:0204:61ff:192.0.0.1");
            System.out.println(s);
            System.out.println(new BigInteger(s, 16));
            BigInteger bigInteger = new BigInteger(String.valueOf("338288524927261089654164245680396173313"));
            System.out.println(bigInteger.toString(16));
            System.out.println(IPUtil.hexToIP(bigInteger.toString(16)));


            String s1 = IPUtil.ipToHex("192.0.0.1");
            System.out.println(s1);


            // System.out.println(IPUtil.ipv6toInt(IPUtil1.byteToIP(IPUtil1.ipToBytes("fe80:0:0:0:0204:61ff:192.0.0.1"))));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test13(){
        /*String ipStr = "192.48.45.124/24-192.48.45.128/24" +
                ",fe80:0:0:0:0204:61ff:192.0.0.1/64-fe80:0:0:0:0204:61ff:192.0.1.1,fe80::0204:61ff";
        System.out.println(IPUtil.ipStrValidation(ipStr));
        IPUtil.getIpList(ipStr.split(",")).forEach(System.out::println);

        try {
            System.out.println(IPUtil.hexToIP(Long.toHexString(3224382844L)));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/
        System.out.println(IPUtil.long2ipv4(1018099810L));
        System.out.println(IPUtil.ipv4toLong("60.174.248.98"));
        System.out.println(IPUtil.ipv6toInt("fe80:0:0:0:0204:61ff:192.0.0.1"));
        System.out.println(IPUtil.int2ipv6(new BigInteger("338288524927261089654164245680396173313")));
    }

    @Test
    public void test14(){
    }

}