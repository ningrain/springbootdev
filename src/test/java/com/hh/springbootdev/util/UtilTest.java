package com.hh.springbootdev.util;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
        System.out.println(InetAddress.getByName("::ffff:192.1.56.10").getAddress().length);
    }

}