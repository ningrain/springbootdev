/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: Thread1.java</p>
 *
 * @author jiangningning
 * @date 2018/5/28
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/5/28 Create
 */
package com.hh.springbootdev.thread;

import com.github.pagehelper.PageInfo;

import java.util.Arrays;
import java.util.List;

class Thread1 implements Runnable {

    private int tickets = 5;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (tickets > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ticket = " + tickets--);
            }
            //System.out.println("Thread1 run…… >>>>>>" + i);
        }
    }
}

class Thread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("MyThread run…… >>>>>>" + i);
        }
    }
}

class Main{
    public static void main(String[] args) {
        /*Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        new Thread(t1).start();
        new Thread(t1).start();*/
        //new Thread(t1).start();
        //new Thread(t2).start();
        PageInfo pageinfo = new PageInfo(Arrays.asList("AAA", "BBB", "CCC", "DDD", "EEE"), 3);
        List list = pageinfo.getList();
        list.forEach(System.out::println);
    }
}

