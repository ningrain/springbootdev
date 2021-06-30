/*
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: Test.java</p>
 *
 * @author jiangningning
 * @date 2020/12/7
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2020/12/7 Create
 */
package com.hh.springbootdev.stream;

import com.haohan.system.domain.HPortalMsg;
import com.hh.springbootdev.util.ByteUtil;
import com.hh.springbootdev.util.DateUtil;
import lombok.Data;

import java.io.*;
import java.util.Arrays;

/**
 * <p>Title: Test</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HPortalMsg.ServerUsageInfo.Builder builder = HPortalMsg.ServerUsageInfo.newBuilder();
        HPortalMsg.ServerUsageInfo serverUsageInfo = builder.setTime(DateUtil.nowSecond())
                .setCpuInfo(30)
                .setMemInfo(40)
                .setBandwidthDown(50)
                .setBandwidthUp(60)
                .build();
        Person person = new Person();
        person.setName("jiangningning");
        person.setAge(23);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(person);
        byte[] personByte = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(personByte));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(0x05);
        dos.write(personByte);
        byte[] bytes = bos.toByteArray();
        System.out.println(Arrays.toString(bytes));

        ByteArrayInputStream bArray = new ByteArrayInputStream(bytes);
        byte[] head = new byte[4];
        bArray.read(head);
        System.out.println("head：" + ByteUtil.getInt(head));
        int bodyLength = bytes.length - 4;
        byte[] body = new byte[bodyLength];
        bArray.read(body);
        System.out.println(Arrays.toString(body));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        System.out.println("body：" + objectInputStream.readObject());
        //释放资源
        bArray.close();
    }

    @Data
    static class Person implements Serializable{
        private String name;
        private int age;
    }

}
