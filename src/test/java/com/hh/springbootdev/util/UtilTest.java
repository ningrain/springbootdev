package com.hh.springbootdev.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
}