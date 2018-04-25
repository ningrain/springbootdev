package com.hh.springbootdev.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh.springbootdev.configuration.RedisProperties;
import org.apache.poi.hssf.usermodel.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 11:06
 */
public class RedisUtil {

    private static JedisPool jedisPool;

    public static void init(RedisProperties properties){
        getJedisPool(properties);
    }

    private static void getJedisPool(RedisProperties properties) {
        JedisPoolConfig config = new JedisPoolConfig();
        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        config.setBlockWhenExhausted(true);
        //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
        config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        //是否启用pool的jmx管理功能, 默认true
        config.setJmxEnabled(true);
        //MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默 认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
        config.setJmxNamePrefix("pool");
        //是否启用后进先出, 默认true
        config.setLifo(true);
        //最大空闲连接数, 默认8个
        config.setMaxIdle(8);
        //最大连接数, 默认8个
        config.setMaxTotal(8);
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        config.setMaxWaitMillis(-1);
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        config.setMinEvictableIdleTimeMillis(1800000);
        //最小空闲连接数, 默认0
        config.setMinIdle(0);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        config.setNumTestsPerEvictionRun(3);
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        config.setSoftMinEvictableIdleTimeMillis(1800000);
        //在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(false);
        //在空闲时检查有效性, 默认false
        config.setTestWhileIdle(false);
        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        config.setTimeBetweenEvictionRunsMillis(-1);
        //GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password
        String host = properties.getRedishost();
        int port = properties.getRedisport();
        //String password = support.getRedispassword();
        int timeout = 5000;
        jedisPool = new JedisPool(config, host, port, timeout, null);
    }

    private static Jedis jedis(){
        return jedisPool.getResource();
    }

    private static void close(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }

    public static String get(String key){
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedis();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return value;
    }

    public static String set(String key, String value){
        Jedis jedis = null;
        String ans = null;
        try {
            jedis = jedis();
            ans = jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return ans;
    }

    public static void putBean(String key, Object o){
        set(key, toJsonStr(o));
    }

    public static <T> T getBean(String key, Class<T> cls){
        String jsonStr = get(key);
        return jsonStr2Obj(jsonStr, cls);

    }

    private static String toJsonStr(Object o) {
        String jsonStr = "";
        try {
            ObjectMapper om = new ObjectMapper();
            jsonStr = om.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    private static <T> T jsonStr2Obj(String jsonStr, Class<T> cls) {
        try {
            if (jsonStr != null && !"".equals(jsonStr)) {
                ObjectMapper om = new ObjectMapper();
                return om.readValue(jsonStr, cls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        File file = new File("E:\\" + File.separator + "demo.xls");
        if (file.exists()) {
            file.delete();
        }
        //第一步创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //第二步创建sheet
        HSSFSheet sheet = wb.createSheet("sheet1");

        /*//第三步创建行row:添加表头0行
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //居中


        //第四步创建单元格
        HSSFCell cell = row.createCell(0);         //第一个单元格
        cell.setCellValue("姓名");                  //设定值
        cell.setCellStyle(style);                   //内容居中

        cell = row.createCell(1);                   //第二个单元格
        cell.setCellValue("身份证");
        cell.setCellStyle(style);

        cell = row.createCell(2);                   //第三个单元格
        cell.setCellValue("错误状态");
        cell.setCellStyle(style);

        cell = row.createCell(3);                   //第四个单元格
        cell.setCellValue("错误信息");
        cell.setCellStyle(style);

        //第五步插入数据
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            //创建行
            row = sheet.createRow(i+1);
            //创建单元格并且添加数据
            row.createCell(0).setCellValue(str);
            row.createCell(1).setCellValue(str);
            row.createCell(2).setCellValue(str);
            row.createCell(3).setCellValue(str);
        }
*/
        //第六步将生成excel文件保存到指定路径下
        try {
            FileOutputStream fout = new FileOutputStream("E:\\demo.xls");
            wb.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Excel文件生成成功...");
    }

}
