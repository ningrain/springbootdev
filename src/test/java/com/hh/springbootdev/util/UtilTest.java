package com.hh.springbootdev.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sun.net.util.IPAddressUtil;

import java.io.*;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", "AAAAAAAAAAA" + i);
            map.put("sort", i);
            list.add(map);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("id", "AAAAAAAAAAA3");
        sort(list, "sort", params);
        System.out.println(list);
    }

    private static void sort(List<Map<String, Object>> list, String sortName, Map<String, Object> params) {
        List<Map<String, Object>> mapList = list.stream().filter(data -> MapUtils.getString(data, "id").equals(MapUtils.getString(params, "id"))).collect(Collectors.toList());
        list.removeAll(mapList);
        list.sort((Comparator<Map>) (o1, o2) -> {
            Long a = MapUtils.getLongValue(o1, sortName);
            Long b = MapUtils.getLongValue(o2, sortName);

            // 升序
            // return a.compareTo(b);

            // 降序
            return b.compareTo(a);
        });
        list.addAll(0, mapList);
    }

    @Test
    public void test15(){
        TreeSet<Integer> rtFlowCTimeSet = new TreeSet<>();
        rtFlowCTimeSet.add(3);
        rtFlowCTimeSet.add(18);
        int interval = 2;
        for (int i = rtFlowCTimeSet.first(); i < rtFlowCTimeSet.last(); i += interval) { // 实时流量填坑
            rtFlowCTimeSet.add(i);
        }
        System.out.println(rtFlowCTimeSet);
    }

    @Test
    public void test16(){
        for (int i = 0; i < 40; i++) {
            long l = (long) (Math.random() * 50000 + 3702259712L);
            System.out.println(l+"L");
        }
        for (int i = 0; i < 40; i++) {
            long l = (long) (Math.random() * 50000 + 3682598912L);
            System.out.println(l+"L");
        }
        for (int i = 0; i < 40; i++) {
            long l = (long) (Math.random() * 50000 + 3728736256L);
            System.out.println(l+"L");
        }
        for (int i = 0; i < 40; i++) {
            long l = (long) (Math.random() * 1000 + 1885601792L);
            System.out.println(l+"L");
        }
    }

    @Test
    public void test17(){
        int duration = 181;
        // 持续时间，单位分钟
        System.out.println((duration % 60) == 0 ? (duration / 60) : (duration / 60 + 1));
    }

    @Test
    public void test18(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("aa", 45);
        map1.put("name", "AA");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("aa", 40);
        map2.put("name", "BB");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("aa", 50);
        map3.put("name", "CC");
        list.add(map1);
        list.add(map2);
        list.add(map3);

        list.stream().filter(data -> MapUtils.getString(data, "name").equals("BB")).forEach(data -> {
            data.put("aa", MapUtils.getIntValue(data, "aa") + 23);
        });
        list.forEach(System.out::println);
    }

    @Test
    public void test19(){
        /*int sTime = DateUtil.parse2Int("2018-09-13 09:05:35");
        int realStartTime = DateUtil.parse2Int("2018-09-13 09:16:35");
        int realEndTime = DateUtil.parse2Int("2018-09-13 09:23:25");
        int eTime = DateUtil.parse2Int("2018-09-13 09:35:25");
        int modelGranularity = 60;
        int duration = (realEndTime - realStartTime) / 60;
        int state = 3;*/

        int sTime = DateUtil.parse2Int("2018-09-13 09:01:35");
        int realStartTime = DateUtil.parse2Int("2018-09-13 09:16:35");
        int realEndTime = DateUtil.parse2Int("2018-09-13 09:35:25");
        int eTime = DateUtil.parse2Int("2018-09-13 09:35:25");
        int modelGranularity = 60;
        int duration = (realEndTime - realStartTime) / 60;
        int state = 2;

        TreeSet<Integer> rtFlowCTimeSet = new TreeSet<>();
        rtFlowCTimeSet.add(DateUtil.parse2Int("2018-09-13 09:13:00"));
        if (state != 3 || duration >= 30) { // 攻击没有结束 或者 持续时间大于30分钟的事件：需在开始之前补足15分钟
            if (rtFlowCTimeSet.size() == 0) { // 开始前15分钟内没有查到实时流量 -> 复制基线时间片
                // rtFlowCTimeSet.addAll(blFlowCTimeSet);
            } else { // 开始前15分钟内查到实时流量（可能有断点）， 取其中一个(此处取最后一个)，向左右补齐
                int last = rtFlowCTimeSet.last();
                for (int i = last; i < realStartTime; i += modelGranularity) { // 从取到的时间点向右补，补至真正开始时间为止
                    rtFlowCTimeSet.add(i);
                }
                for (int i = last; i >= sTime; i -= modelGranularity) { // 从取到的时间点向左补，补至sTime
                    rtFlowCTimeSet.add(i);
                }
            }
        } else { // 攻击状态已经结束并且持续时间小于30分钟 -> 需要将总时长补齐30分钟
            if (rtFlowCTimeSet.size() == 0) { // 开始前和结束后都没有查到实时流量 -> 复制基线时间片
                // rtFlowCTimeSet.addAll(blFlowCTimeSet);
            } else { // 开始前或结束后内查到实时流量（可能有断点）， 取其中一个(此处取最后一个)，向左右补齐
                int last = rtFlowCTimeSet.last();
                for (int i = last; i < eTime; i += modelGranularity) { // 从取到的时间点向右补，补至eTime
                    if (!(realStartTime < i && i < realEndTime)) { // 过滤掉告警时间段
                        rtFlowCTimeSet.add(i);
                    }
                }
                for (int i = last; i > sTime; i -= modelGranularity) { // 从取到的时间点向左补，补至sTime
                    if (!(realStartTime < i && i < realEndTime)) { // 过滤掉告警时间段
                        rtFlowCTimeSet.add(i);
                    }
                }
            }
        }

        rtFlowCTimeSet.add(sTime);
        rtFlowCTimeSet.add(eTime);
        System.out.println("********************************");
        rtFlowCTimeSet.forEach(rtFlowCTime -> System.out.println(DateUtil.parse2StrFormat(rtFlowCTime)));
        System.out.println("********************************");
    }

    @Test
    public void test20(){
        System.out.println(String.format("%.2f", 3.184f));
        System.out.println(String.format("%.2f", 0.009f));
    }

    @Test
    public void test21(){
        System.out.println(PasswordEncodeUtil.encode("pwd"));
    }

    @Test
    public void test22(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("2018-11-19 00:01:19", "AAAA");
        list.add(map1);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("2018-11-19 00:01:21", "CCCC");
        list.add(map3);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("2018-11-19 00:01:20", "BBBB");
        list.add(map2);
        sort(list);
        System.out.println(list);

    }

    private void sort(List<Map<String, Object>> list) {
        list.sort((o1, o2) -> {
            String s1 = o1.keySet().iterator().next();
            String s2 = o2.keySet().iterator().next();
            return s1.compareTo(s2);
        });
    }

    @Test
    public void test23(){
        System.out.println(Integer.toBinaryString(174));
    }

    private Set<Integer> assembleTimeSet(int sTime, int realStartTime, int realEndTime, int eTime) {
        Set<Integer> timeSet = new TreeSet<>();
        timeSet.add(sTime);
        timeSet.add(realStartTime);
        timeSet.add(realEndTime);
        timeSet.add(eTime);
        int modelGranularity = 180;
        for(int i = realStartTime; i > sTime; i -= modelGranularity) {
            int time = i / modelGranularity * modelGranularity;
            if(sTime <= time && time <= eTime) {
                timeSet.add(time);
            }
        }
        for(int i = eTime; i > realEndTime; i -= modelGranularity) {
            int time = i / modelGranularity * modelGranularity;
            if(sTime <= time && time <= eTime) {
                timeSet.add(time);
            }
        }
        return timeSet;
    }

    @Test
    public void test24(){
        int sTime = DateUtil.parse2Int("2018-11-26 13:12:05");
        int realStartTime = DateUtil.parse2Int("2018-11-26 13:27:05");
        int realEndTime = DateUtil.parse2Int("2018-11-26 13:35:07");
        int eTime = DateUtil.parse2Int("2018-11-26 13:50:07");
        Set<Integer> timeSet = assembleTimeSet(sTime, realStartTime, realEndTime, eTime);
        timeSet.forEach(time -> System.out.println(DateUtil.parse2StrFormat(time)));
    }

    @Test
    public void test25(){
        Map<String, Long> dataMap = new HashMap<>();
        dataMap.put("2018-11-29---192.168.0.1", 123456789L);
        dataMap.put("2018-11-29---192.168.0.2", 123456700L);
        exportHBaseOriginalData(dataMap);
    }

    public String exportHBaseOriginalData(Map<String, Long> dataMap) {
        String exportFileName = "D:/" + File.separator +"aaa.xlsx";
        try {
            File exportFile = new File(exportFileName);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }
            // 获取存在的excel文件
            InputStream inputStream = new FileInputStream(exportFileName);
            // 定义excel工作簿的引用
            XSSFWorkbook xs = new XSSFWorkbook(inputStream);
            // 写入dataList数据到excel
            //create sheet
            Sheet sheet = xs.createSheet();
            //遍历数据集，将其写入excel中
            AtomicInteger rowIndex = new AtomicInteger();//标识位，用于标识sheet的行号
            //循环写入主表数据
            dataMap.forEach((key, value) -> {
                rowIndex.getAndIncrement();
                Row row = sheet.createRow(rowIndex.get());
                //create sheet coluum(单元格)
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(key);
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(value);
            });
            System.out.println("主表数据写入完成>>>>>>>>");
            FileOutputStream fos = new FileOutputStream(exportFileName);
            xs.write(fos);
            fos.close();
            System.out.println(exportFileName + "写入文件成功>>>>>>>>>>>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exportFileName;
    }

    @Test
    public void test26(){
        System.out.println("+++++++++++++++");
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println(new Date().getTime());
        System.out.println("+++++++++++++++");
    }

    public static void main(String[] args) {
        /*ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 15; i++) {
            int finalI = i;
            executor.execute(() -> {
                System.out.println(finalI + " start");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // System.out.println(finalI + " end");
            });
        }
        executor.shutdown();*/
        System.out.println("doing something........");
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("ShutdownHook execute");
            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Person{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void run(){
            System.out.println("run……");
        }
        public void eat(){
            System.out.println("eat……");
        }
        public void talk(){
            System.out.println("talk……");
        }
    }

    @Test
    public void test27(){
        Person p = new Person();
        p.setName("zs");
        p.setAge(23);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String s = mapper.writeValueAsString(p);
            // System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(System.getProperties().toString().replaceAll(",", ",\n"));
    }

    @Test
    public void test28(){
        String blBps = "15454.25K";
        System.out.println(blBps.substring(blBps.length() - 1));
        System.out.println("192.168.0.4".contains(""));
    }
}