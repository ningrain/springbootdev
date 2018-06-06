/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: PostgresqlArrayTest.java</p>
 *
 * @author jiangningning
 * @date 2018/6/6
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/6/6 Create
 */
package com.hh.springbootdev.postgresqlarray;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import com.hh.springbootdev.dao.ITblArrayDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostgresqlArrayTest extends SpringbootdevApplicationTests {

    @Autowired
    private ITblArrayDao iTblArrayDao;

    @Test
    public void test1() {
        Integer[] a = {1, 2};
        String[][] b = {{"1", "2", "3"}, {"4", "5", "6"}};
        Integer c = 15;
        Map<String, Object> map = new HashMap<>();
        map.put("a", a);
        map.put("b", b);
        map.put("c", c);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        list.add(map);
        iTblArrayDao.save(list);
        //iTblArrayDao.save1(map);
        //iTblArrayDao.save2(a, b, c);
    }


    public static void main(String[] args) {
        Integer[] a = {1, 2};
        String[][] b = {{"1", "2", "3"}, {"4", "5", "6"}};
        System.out.println(a);
        System.out.println(b);
    }
}
