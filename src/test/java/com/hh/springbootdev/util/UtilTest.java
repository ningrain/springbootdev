package com.hh.springbootdev.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.com.webxml.MobileCodeWS;
import cn.com.webxml.MobileCodeWSSoap;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.text.csv.*;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import sun.net.util.IPAddressUtil;

import javax.xml.namespace.QName;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/7/17
 * Time: 21:49
 */
@RunWith(JUnit4.class)
@Slf4j
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
    public void test2() {
        Map<String, Object> map = new HashMap<>();
        List<String> strs = new ArrayList<>();
        strs.add("AA");
        strs.add("BB");
        map.put("list", strs);
        System.out.println(((List) map.get("list")).get(0));
    }

    @Test
    public void test3() {
        double d = Double.MIN_VALUE;
        float f = (float) d;
        System.out.println(f);
        System.out.println(String.format("%.4s", "3.58954"));
        Map<String, Boolean> map = new HashMap<>();
        map.put("AA", false);
        System.out.println(MapUtils.getBoolean(map, "AA"));
        float f1 = 1670;
        System.out.println(f1 / 1000);
        System.out.println(1654.5 / 1000.0);
    }

    @Test
    public void test4() {
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
    public void test6() {
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
    public void test9() {
        System.out.println(IPAddressUtil.isIPv6LiteralAddress("::ffff:192.1.56.10"));
        System.out.println(IPAddressUtil.isIPv6LiteralAddress("::"));
        // System.out.println(IPUtil.ipStrValidation("::ffff:192.1.56.10"));
        // IPAddress ipAddress = new IPAddress("::ffff:192.1.56.10");
        // System.out.println(ipAddress.getIPAddress());
        System.out.println(0xff00);
    }

    @Test
    public void test10() {
        // Mythread t1 = ;
        // Mythread t2 = new Mythread();
        for (int i = 0; i < 10; i++) {
            new Thread(new Mythread()).start();
        }
    }


    class Mythread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "-" + new Date().getTime());
        }
    }

    @Test
    public void test11() {
        System.out.println(IPUtil.ipStrValidation("192.168.0.2-:ffff:192.1.56.10"));
    }

    @Test
    public void test12() {
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
    public void test13() {
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
    public void test14() {
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
    public void test15() {
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
    public void test16() {
        for (int i = 0; i < 40; i++) {
            long l = (long) (Math.random() * 50000 + 3702259712L);
            System.out.println(l + "L");
        }
        for (int i = 0; i < 40; i++) {
            long l = (long) (Math.random() * 50000 + 3682598912L);
            System.out.println(l + "L");
        }
        for (int i = 0; i < 40; i++) {
            long l = (long) (Math.random() * 50000 + 3728736256L);
            System.out.println(l + "L");
        }
        for (int i = 0; i < 40; i++) {
            long l = (long) (Math.random() * 1000 + 1885601792L);
            System.out.println(l + "L");
        }
    }

    @Test
    public void test17() {
        int duration = 181;
        // 持续时间，单位分钟
        System.out.println((duration % 60) == 0 ? (duration / 60) : (duration / 60 + 1));
    }

    @Test
    public void test18() {
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
    public void test19() {
        /*int sTime = DateUtil.parse2Int("2018-09-13 09:05:35");
        int realStartTime = DateUtil.parse2Int("2018-09-13 09:16:35");
        int realEndTime = DateUtil.parse2Int("2018-09-13 09:23:25");
        int eTime = DateUtil.parse2Int("2018-09-13 09:35:25");
        int modelGranularity = 60;
        int duration = (realEndTime - realStartTime) / 60;
        int state = 3;*/

        int sTime = DateUtil.toSecond("2018-09-13 09:01:35");
        int realStartTime = DateUtil.toSecond("2018-09-13 09:16:35");
        int realEndTime = DateUtil.toSecond("2018-09-13 09:35:25");
        int eTime = DateUtil.toSecond("2018-09-13 09:35:25");
        int modelGranularity = 60;
        int duration = (realEndTime - realStartTime) / 60;
        int state = 2;

        TreeSet<Integer> rtFlowCTimeSet = new TreeSet<>();
        rtFlowCTimeSet.add(DateUtil.toSecond("2018-09-13 09:13:00"));
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
        rtFlowCTimeSet.forEach(rtFlowCTime -> System.out.println(DateUtil.second2Str(rtFlowCTime)));
        System.out.println("********************************");
    }

    @Test
    public void test20() {
        System.out.println(String.format("%.2f", 3.184f));
        System.out.println(String.format("%.2f", 0.009f));
    }

    @Test
    public void test21() {
        System.out.println(PasswordEncodeUtil.encode("pwd"));
    }

    @Test
    public void test22() {
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
    public void test23() {
        System.out.println(Integer.toBinaryString(174));
    }

    private Set<Integer> assembleTimeSet(int sTime, int realStartTime, int realEndTime, int eTime) {
        Set<Integer> timeSet = new TreeSet<>();
        timeSet.add(sTime);
        timeSet.add(realStartTime);
        timeSet.add(realEndTime);
        timeSet.add(eTime);
        int modelGranularity = 180;
        for (int i = realStartTime; i > sTime; i -= modelGranularity) {
            int time = i / modelGranularity * modelGranularity;
            if (sTime <= time && time <= eTime) {
                timeSet.add(time);
            }
        }
        for (int i = eTime; i > realEndTime; i -= modelGranularity) {
            int time = i / modelGranularity * modelGranularity;
            if (sTime <= time && time <= eTime) {
                timeSet.add(time);
            }
        }
        return timeSet;
    }

    @Test
    public void test24() {
        int sTime = DateUtil.toSecond("2018-11-26 13:12:05");
        int realStartTime = DateUtil.toSecond("2018-11-26 13:27:05");
        int realEndTime = DateUtil.toSecond("2018-11-26 13:35:07");
        int eTime = DateUtil.toSecond("2018-11-26 13:50:07");
        Set<Integer> timeSet = assembleTimeSet(sTime, realStartTime, realEndTime, eTime);
        timeSet.forEach(time -> System.out.println(DateUtil.second2Str(time)));
    }

    @Test
    public void test25() {
        Map<String, Long> dataMap = new HashMap<>();
        dataMap.put("2018-11-29---192.168.0.1", 123456789L);
        dataMap.put("2018-11-29---192.168.0.2", 123456700L);
        exportHBaseOriginalData(dataMap);
    }

    public String exportHBaseOriginalData(Map<String, Long> dataMap) {
        String exportFileName = "D:/" + File.separator + "aaa.xlsx";
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
    public void test26() {
        System.out.println("+++++++++++++++");
        System.out.println(System.currentTimeMillis() / 1000);
        System.out.println(new Date().getTime());
        System.out.println("+++++++++++++++");
    }

    public static void main(String[] args) {
        TraceThreadPoolExecutor traceThreadPoolExecutor = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L,
                TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            traceThreadPoolExecutor.execute(new DivTask(100, 0));
        }
    }

    static class DivTask implements Runnable {
        int a;
        int b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            double re = a / b;
            System.out.println(re);
        }
    }

    static class TraceThreadPoolExecutor extends ThreadPoolExecutor {

        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        public void execute(Runnable task) {
            super.execute(warp(task, clientTrace(), Thread.currentThread().getName()));
        }

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(warp(task, clientTrace(), Thread.currentThread().getName()));
        }

        private Exception clientTrace() {
            return new Exception("Client stack trace");
        }

        private Runnable warp(final Runnable task, final Exception clientStack, String clientThreadName) {
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } catch (Exception e) {
                        clientStack.printStackTrace();
                        throw e;
                    }
                }
            };
        }
    }

    static class Person {
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

        public void run() {
            System.out.println("run……");
        }

        public void eat() {
            System.out.println("eat……");
        }

        public void talk() {
            System.out.println("talk……");
        }
    }

    @Test
    public void test27() {
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
    public void test28() {
        String blBps = "15454.25K";
        System.out.println(blBps.substring(blBps.length() - 1));
        System.out.println("192.168.0.4".contains(""));
    }

    @Test
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

    @Test
    public void testCopyFromLocalFile() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        // 配置在集群上运行
        // configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
        // FileSystem fs = FileSystem.get(configuration);

        FileSystem fs = FileSystem.get(new URI("hdfs://111.231.109.105:9000"), configuration, "root");

        // 2 copy本地文件至hdfs
        fs.copyFromLocalFile(new Path("E:\\hadooptest1.txt"), new Path("/1108/daxian/banzhang"));

        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testCopyToLocalFile() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        // 配置在集群上运行
        // configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
        // FileSystem fs = FileSystem.get(configuration);

        FileSystem fs = FileSystem.get(new URI("hdfs://111.231.109.105:9000"), configuration, "root");

        // 2 copy hdfs文件至本地
        fs.copyToLocalFile(false,
                new Path("/1108/daxian/banzhang/hadooptest.txt"),
                new Path("E:\\hadooptest1.txt"),
                true);

        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testDelete() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        // 配置在集群上运行
        // configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
        // FileSystem fs = FileSystem.get(configuration);

        FileSystem fs = FileSystem.get(new URI("hdfs://111.231.109.105:9000"), configuration, "root");

        // 2 删除
        fs.delete(new Path("/1108"), true);

        // 3 关闭资源
        fs.close();
    }

    @Test
    public void byteArrayTest() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);
            //id
            dos.writeLong(1238L);
            //collecterId
            dos.writeInt(13);
            //msgAttri
            dos.writeBytes("conf");
            dos.write(new byte[6]);
            //time
            dos.writeLong(20190813113925L);
            byte[] data = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            DataInputStream dis = new DataInputStream(bis);
            byte[] bytes = new byte[8];
            dis.read(bytes, 0, 8);
            byte[] bytes1 = new byte[4];
            dis.read(bytes1, 0, 4);
            System.out.println(Arrays.toString(bytes));
            System.out.println(bytesToLong(bytes));
            System.out.println(Arrays.toString(bytes1));
            System.out.println(byteArrayToInt(bytes1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    private static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    @Test
    public void test29() {
        List<Map<String, Integer>> list = new ArrayList<>();
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("start", 8);
        map1.put("end", 15);
        list.add(map1);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("start", 4);
        map2.put("end", 9);
        list.add(map2);
        Map<String, Integer> map3 = new HashMap<>();
        map3.put("start", 11);
        map3.put("end", 18);
        list.add(map3);
        Map<String, Integer> map4 = new HashMap<>();
        map4.put("start", 20);
        map4.put("end", 26);
        list.add(map4);

        List<Map<String, Integer>> resultList = new ArrayList<>();
        resultList.add(list.get(0));
        list.forEach(data -> resultList.forEach(result -> {
            int dataStart = MapUtils.getIntValue(data, "start");
            int dataEnd = MapUtils.getIntValue(data, "end");
            int resultStart = MapUtils.getIntValue(result, "start");
            int resultEnd = MapUtils.getIntValue(result, "end");
            boolean b = dataStart <= resultEnd && dataEnd >= resultStart;
            if (b) {
                result.put("start", dataStart >= resultStart ? dataStart : resultStart);
                result.put("end", dataEnd >= resultEnd ? dataEnd : resultEnd);
                resultList.add(result);
            } else {
                Map<String, Integer> map = new HashMap<>();
                map.put("start", dataStart);
                map.put("end", dataEnd);
                resultList.add(map);
            }
        }));

        System.out.println(resultList);
    }

    @Test
    public void test30() {
        List<Integer> nodes = Arrays.asList(1, 2, 3, 4, 5, 6);
        try {
            supplyAsyncTest1(nodes);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void supplyAsyncTest(List<Integer> nodes) {
        Executor executor = Executors.newFixedThreadPool(10);
        // List<Integer> nodes = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<String> results = new ArrayList<>();
        Set<Integer> failResults = new HashSet<>();
        nodes.forEach(node -> CompletableFuture.supplyAsync(() -> new SSHUtil().excuteCmd(node), executor)
                .whenComplete((result, exp) -> {
                    log.info("result -> " + result);
                    log.info("exception -> " + exp);
                    if (exp != null || !result.equals("0")) {
                        failResults.add(node);
                        result = "1";
                    }
                    results.add(result);
                    if (results.size() == nodes.size()) {
                        if (failResults.size() > 0) {
                            log.info(failResults.size() + "个线程" + failResults.toString() + "执行失败....");
                        } else {
                            log.info("所有线程执行成功....");
                        }
                    }
                }));
        try {
            log.info("主程序继续执行......");
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void supplyAsyncTest1(List<Integer> nodes) {
        Executor executor = Executors.newFixedThreadPool(10);
        // List<Integer> nodes = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<String> results = new ArrayList<>();
        Set<Integer> failResults = new HashSet<>();
        CompletableFuture[] cfs = nodes.stream().map(node -> CompletableFuture.supplyAsync(() -> new SSHUtil().excuteCmd(node), executor)
                .whenComplete((result, exp) -> {
                    log.info("result -> " + result);
                    log.info("exception -> " + exp);
                    if (exp != null || !result.equals("0")) {
                        failResults.add(node);
                        result = "1";
                    }
                    results.add(result);
                    if (results.size() == nodes.size()) {
                        if (failResults.size() > 0) {
                            log.info(failResults.size() + "个线程" + failResults.toString() + "执行失败....");
                        } else {
                            log.info("所有线程执行成功....");
                        }
                    }
                })).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(cfs).completeExceptionally(new RuntimeException("error"));
        try {
            log.info("主程序继续执行......");
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class SSHUtil {

        public String excuteCmd(Integer node) {
            try {
                log.info("node" + node + " 执行");
                Thread.sleep(2 * 1000);
                int i = new Random().nextInt(3);
                if (i == 2) {
                    int a = 1 / 0;
                }
            } catch (Exception e) {
                // e.printStackTrace();
                // return "1";
                throw new RuntimeException("error");
            }
            return "0";
        }

    }


    @Test
    public void test31() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl");
        Object[] objects;
        try {
            objects = client.invoke("getMobileCodeInfo", "15375140921");
            System.out.println(objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test32() throws Exception {

        HttpClient client = new HttpClient();

        PostMethod postMethod = new PostMethod("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo");
        //3.设置请求参数
        postMethod.setParameter("mobileCode", "15375140921");
        postMethod.setParameter("userID", "");
        //4.执行请求 ,结果码
        int code = client.executeMethod(postMethod);
        if (HttpStatus.SC_OK == code) {
            //5. 获取结果
            String result = postMethod.getResponseBodyAsString();

            Document document = DocumentHelper.parseText(result);
            Element rootElement = document.getRootElement();
            String text = rootElement.getText();
            System.out.println(text);
        }

    }

    @Test
    public void test33() {

        String URL = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?op=getMobileCodeInfo";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded");
        headers.setContentType(type);
        HttpEntity<String> formEntity = new HttpEntity<>("mobileCode=15375140921&userID=", headers);
        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.postForObject(URL, formEntity, String.class);
        System.out.println(s);
    }

    @Test
    public void test34() {
        CsvReadConfig csvConfig = new CsvReadConfig();
        csvConfig.setContainsHeader(false);
        CsvReader reader = CsvUtil.getReader(csvConfig);
        String filePath = "C:\\Users\\DELL\\Desktop\\新建文件夹\\ddos_job.csv";
        CsvData data = reader.read(new File(filePath));
        List<List<String>> allData = new ArrayList<>();
        data.getRows().forEach(rows -> {
            List<String> rowData = new ArrayList<>(rows);
            allData.add(rowData);
        });

        String newFilePath = "C:\\Users\\DELL\\Desktop\\新建文件夹\\ddos_job1.csv";
        try {
            CsvWriteConfig csvWriteConfig = new CsvWriteConfig();
            csvWriteConfig.setAlwaysDelimitText(true);
            // csvWriteConfig.setLineDelimiter(new char[]{CharUtil.LF});
            CsvWriter writer = CsvUtil.getWriter(new FileWriter(newFilePath), csvWriteConfig);
            writer.write(allData);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test35() {
        MobileCodeWS mobileCodeWS;
        try {
            mobileCodeWS = new MobileCodeWS(new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl"));
            MobileCodeWSSoap mobileCodeWSSoap = mobileCodeWS.getPort(MobileCodeWSSoap.class);
            String mobileCodeInfo = mobileCodeWSSoap.getMobileCodeInfo("15375140921", null);
            System.out.println(mobileCodeInfo);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test36() {
        // 接口地址
        // 代理工厂
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        // 设置代理地址
        jaxWsProxyFactoryBean.setAddress("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
        // 设置接口类型
        // jaxWsProxyFactoryBean.setServiceClass(MobileCodeWSSoap.class);
        // 创建一个代理接口实现
        MobileCodeWSSoap mobileCodeWSSoap = jaxWsProxyFactoryBean.create(MobileCodeWSSoap.class);
        // 数据准备
        // String userName = "Leftso";
        // 调用代理接口的方法调用并返回结果
        String result = mobileCodeWSSoap.getMobileCodeInfo("15375140921", null);
        System.out.println("返回结果:" + result);
    }

    @Test
    public void test37() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects;
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("getMobileCodeInfo", "15375140921", null);
            System.out.println("返回类型：" + objects[0].getClass());
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test38() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://suggest.taobao.com/sug?code=utf-8&q=%E6%B1%BD%E8%BD%A6&callback=cb");
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(response1.getStatusLine());
            org.apache.http.HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            // EntityUtils.consume(entity1);
            String s = EntityUtils.toString(entity1);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response1.close();
        }


    }

    @Test
    public void test39() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        System.out.println("start......");
        // new Thread(new MyThread1()).start();
        service.execute(new MyThread1());
        System.out.println("end......");
    }

    public class MyThread1 implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(5 * 1000);
                System.out.println("MyThread1 excute finish......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class MyCallable1 {

        public Integer get() {
            try {
                log.info("MyCallable1 ... call ....");
                Thread.sleep(2 * 1000);
                int random = new Random().nextInt(5) + 1;
                if (random == 2) {
                    int a = 1 / 0;
                }
            } catch (Exception e) {
                return 1;
            }
            return 0;
        }
    }

    @Test
    public void test40() {
        // 线程池
        ExecutorService exec = Executors.newFixedThreadPool(5);
        List<Integer> results = new ArrayList<>();
        List<Integer> nodes = Arrays.asList(3, 5, 7, 9);
        CompletableFuture[] cfs = nodes.stream().map(object -> CompletableFuture.supplyAsync(() -> new MyCallable1().get(), exec)
                .whenComplete((result, e) -> {
                    //执行线程执行完以后的操作。
                    System.out.println("result ......" + result);
                    results.add(result);
                })).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(cfs).join();
        if (results.contains(1)) {
            System.out.println("存在失败线程***********");
        }
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test41() throws IOException, InterruptedException {
        Connection connection = new Connection("10.10.24.101", 22);
        connection.connect();
        if (connection.authenticateWithPassword("root", "123456")) {
            Session session = connection.openSession(); // 打开一个会话
            session.execCommand("sh /opt/installCold/nodeRestart.sh 10.10.24.103 root"); // 执行命令
            String result = processStdout(session.getStdout());
            String error = processStdout(session.getStderr());
            Integer exitStatus = session.getExitStatus();
            System.out.println(exitStatus);
        }
    }

    private static String processStdout(InputStream in) {
        InputStream stdout = new StreamGobbler(in);
        StringBuilder buffer = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            String line;
            while((line = br.readLine()) != null) {
                buffer.append(line).append("\n");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    @Test
    public void test42() {
        // 线程池
        ExecutorService exec = Executors.newFixedThreadPool(5);
        List<Integer> nodes = Arrays.asList(3, 5, 7, 9);
        List<Integer> results = new ArrayList<>();
        CompletableFuture[] cfs = nodes.stream().map(object -> CompletableFuture.supplyAsync(() -> new MyCallable1().get(), exec)
                .whenComplete((result, e) -> {
                    //执行线程执行完以后的操作。
                    log.info("result ......" + result);
                    results.add(result);
                })).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(cfs).whenComplete((result, exp) -> {
            log.info("results：" + results);
        });
        log.info("主线程继续执行***********");
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test43() {
        Integer a1 = 127;
        Integer a2 = 127;
        Integer a3 = 128;
        Integer a4 = new Integer(128);
        System.out.println(a1 == a2);
        System.out.println(a3 == a4);
    }

    @Test
    public void test44() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", "北京");
        HttpRequest request = HttpUtil.createRequest(Method.POST, "https://www.baidu.com");
        String result= HttpUtil.post("https://www.baidu.com", paramMap);
        System.out.println(result);
    }

    @Test
    public void test45() {
        CsvReadConfig csvReadConfig = new CsvReadConfig();
        csvReadConfig.setContainsHeader(true);
        csvReadConfig.setFieldSeparator('^');
        CsvReader csvReader = new CsvReader(csvReadConfig);
        CsvData csvRows = csvReader.read(FileUtil.file("E:\\DNV\\test.csv"));
        System.out.println();
    }

    @Test
    public void test46() {
        AtomicLong counter = new AtomicLong(0);
        String smallFilePath = "E:\\DNV\\test.csv";
        String bigFilePath = "E:\\DNV\\i_w_lkpt_00001_20210526.csv";
        BigFileReader.Builder builder = new BigFileReader.Builder(smallFilePath, new IFileHandle() {
            @Override
            public void handle(String line) {
                System.out.println(String.format("total record: %s,line is: %s", counter.incrementAndGet(), line));
            }
        });
        BigFileReader bigFileReader = builder
                .threadPoolSize(100)
                .charset(StandardCharsets.UTF_8)
                .bufferSize(1024 * 1024).build();
        bigFileReader.start();
    }

    @Test
    public void test47() {
        SFTPConfig sftpConfig = new SFTPConfig("111.231.109.105", 22, "sftp_user", "123");
        try {
            SFTPUtil.download(sftpConfig, "/", "test.csv", "e:", "test1.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test48() {
        try {
            SFTPUtil.download("/", "test.csv", "e:", "test1.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test49() {
        LineNumberReader reader = null;
        String str = "";
        int line = 1;
        int errorCount = 0;
        BufferedWriter bw = null;
        try {
            File file = new File("E:\\DNV\\资管IP对接\\merge_test.csv");
            reader = new LineNumberReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

            File newFile = new File("E:\\DNV\\资管IP对接\\merge_test_ipv4.csv");
            // if file doesnt exists, then create it
            if (newFile.exists()) {
                newFile.delete();
            }
            newFile.createNewFile();

            FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            // 一次读入一行，直到读入null为文件结束
            while ((str = reader.readLine()) != null) {
                if (reader.getLineNumber() == 1) {
                    continue;
                }
                String[] strings = str.split("\\^");
                String ipWithMask = strings[2];
                String ip;
                String mask;
                if (!(ipWithMask.contains(":") || ipWithMask.contains("."))) {
                    errorCount++;
                    continue;
                }
                if (ipWithMask.contains(":")) {
                    ip = StringUtils.substringBefore(ipWithMask, "/");
                    mask = StringUtils.substringAfter(ipWithMask, "/");
                    if (StringUtils.isEmpty(mask)) {
                        mask = "128";
                    }
                    if (!IPUtil.isIPv6(ip)) {
                        log.error("[{}]行数据出错，内容：[{}]", line, str);
                        errorCount++;
                        continue;
                    }
                    try {
                        int intMask = Integer.parseInt(mask);
                        if (intMask < 1 || intMask > 128) {
                            log.error("[{}]行数据出错，内容：[{}]", line, str);
                            errorCount++;
                            continue;
                        }
                    } catch (Exception e) {
                        log.error("[{}]行数据出错，内容：[{}]", line, str);
                        errorCount++;
                        continue;
                    }
                } else {
                    ip = StringUtils.substringBefore(ipWithMask, "/").trim();
                    mask = StringUtils.substringAfterLast(ipWithMask, "/").trim();
                    if (StringUtils.isEmpty(mask)) {
                        mask = "32";
                    }
                    if (!IPUtil.isIPv4(ip)) {
                        log.error("[{}]行数据出错，内容：[{}]", line, str);
                        errorCount++;
                        continue;
                    }
                    try {
                        int intMask = Integer.parseInt(mask);
                        if (intMask < 1 || intMask > 32) {
                            log.error("[{}]行数据出错，内容：[{}]", line, str);
                            errorCount++;
                            continue;
                        }
                    } catch (Exception e) {
                        log.error("[{}]行数据出错，内容：[{}]", line, str);
                        errorCount++;
                        continue;
                    }
                }
                String newStr = line + "," + ip + "," + mask + "\r";
                line++;
                bw.write(newStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取文件出错，[{}]行数据出错，内容：[{}]", line, str);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("共计[{}]行记录，[{}]行错误记录。。。", line, errorCount);
    }


}