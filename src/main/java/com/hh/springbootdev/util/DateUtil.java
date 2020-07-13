/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: DateUtil.java</p>
 *
 * @author jiangningning
 * @date 2018/9/13
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/9/13 Create
 */
package com.hh.springbootdev.util;

import lombok.extern.log4j.Log4j;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 *
 * @author jiangningning
 */
@Log4j
public class DateUtil {

    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String SECOND_TIME_FORMAT = "HH:mm:ss";
    public final static String MILLI_TIME_FORMAT = "HH:mm:ss SSS";
    public final static String DATE_SECOND_TIME_FORMAT = DATE_FORMAT + " " + SECOND_TIME_FORMAT;
    public final static String DATE_MILLI_TIME_FORMAT = DATE_FORMAT + " " + MILLI_TIME_FORMAT;

    /**
     * <p>Title:nowDay</p>
     * <p>Description: 当前日期</p>
     * @return java.time.LocalDate
     */
    public static LocalDate nowDay() {
        return LocalDate.now();
    }

    /**
     * <p>Title:nowDay</p>
     * <p>Description: 当前日期</p>
     * @return java.lang.String
     */
    public static String nowDayStr() {
        return nowFormatDay(DATE_FORMAT);
    }

    /**
     * <p>Title:nowFormatDay</p>
     * <p>Description: 获取特定格式日期时间字符串</p>
     * @param pattern 日期格式
     * @return java.lang.String
     */
    public static String nowFormatDay(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * <p>Title:nowTime</p>
     * <p>Description: 获取当前时间点</p>
     * @return java.time.LocalTime
     */
    public static LocalTime nowTime() {
        return LocalTime.now();
    }

    /**
     * <p>Title:nowTimeStr</p>
     * <p>Description: 获取当前时间点字符串</p>
     * @return java.lang.String
     */
    public static String nowTimeStr() {
        return nowFormatTime(SECOND_TIME_FORMAT);
    }

    /**
     * <p>Title:nowFormatTime</p>
     * <p>Description: 获取当前时间点特定格式字符串</p>
     * @param pattern 时间格式
     * @return java.lang.String
     */
    public static String nowFormatTime(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * <p>Title:nowMilli</p>
     * <p>Description: 获取当前毫秒数</p>
     * @return long
     */
    public static long nowMilli() {
        return LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * <p>Title:toMilli</p>
     * <p>Description: 日期时间转毫秒数</p>
     * @param dateTime 日期时间
     * @return long
     */
    public static long toMilli(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * <p>Title:toMilli</p>
     * <p>Description: 日期时间字符串转毫秒数</p>
     * @param dateTimeStr 日期时间字符串
     * @return long
     */
    public static long toMilli(String dateTimeStr) {
        return toMilli(dateTimeStr, DATE_SECOND_TIME_FORMAT);
    }

    /**
     * <p>Title:toMilli</p>
     * <p>Description: 特定格式日期时间字符串转毫秒数</p>
     * @param dateTimeStr 日期时间字符串
     * @param pattern 日期时间格式
     * @return long
     */
    public static long toMilli(String dateTimeStr, String pattern) {
        LocalDateTime dateTime = str2DateTime(dateTimeStr, pattern);
        return toMilli(dateTime);
    }

    /**
     * <p>Title:nowSecond</p>
     * <p>Description: 当前秒数</p>
     * @return int
     */
    public static int nowSecond() {
        return (int) (LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).getEpochSecond());
    }

    /**
     * <p>Title:toSecond</p>
     * <p>Description: 日期时间字符串转秒数</p>
     * @param dateTimeStr 日期时间字符串
     * @return int
     */
    public static int toSecond(String dateTimeStr) {
        return toSecond(dateTimeStr, DATE_SECOND_TIME_FORMAT);
    }

    /**
     * <p>Title:toSecond</p>
     * <p>Description: 特定格式日期时间字符串转秒数</p>
     * @param dateTimeStr 日期时间字符串
     * @param pattern 日期时间格式
     * @return int
     */
    public static int toSecond(String dateTimeStr, String pattern) {
        LocalDateTime dateTime = str2DateTime(dateTimeStr, pattern);
        return toSecond(dateTime);
    }

    /**
     * <p>Title:toSecond</p>
     * <p>Description: 日期时间转秒数</p>
     * @param dateTime 日期时间
     * @return int
     */
    public static int toSecond(LocalDateTime dateTime) {
        return (int) dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * <p>Title:milli2Str</p>
     * <p>Description: 毫秒数转字符串</p>
     * @param milli 毫秒数
     * @return java.lang.String
     */
    public static String milli2Str(long milli) {
        LocalDateTime dateTime = Instant.ofEpochMilli(milli).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_SECOND_TIME_FORMAT));
    }

    /**
     * <p>Title:milli2Str</p>
     * <p>Description: 毫秒数转特定格式字符串</p>
     * @param milli 毫秒数
     * @param pattern 日期时间格式
     * @return java.lang.String
     */
    public static String milli2Str(long milli, String pattern) {
        LocalDateTime dateTime = Instant.ofEpochMilli(milli).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * <p>Title:second2Str</p>
     * <p>Description: 秒数转字符串</p>
     * @param second 秒数
     * @return java.lang.String
     */
    public static String second2Str(int second) {
        LocalDateTime dateTime = Instant.ofEpochSecond(second).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_SECOND_TIME_FORMAT));
    }

    /**
     * <p>Title:second2Str</p>
     * <p>Description: 秒数转特定格式字符串</p>
     * @param second 秒数
     * @param pattern 日期时间格式
     * @return java.lang.String
     */
    public static String second2Str(int second, String pattern) {
        LocalDateTime dateTime = Instant.ofEpochSecond(second).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * <p>Title:toDateTime</p>
     * <p>Description: 字符串转LocalDateTime</p>
     * @param dateTimeStr 日期时间字符串
     * @param pattern 格式
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime str2DateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * <p>Title:dateTime2Str</p>
     * <p>Description: 字符串转LocalDateTime</p>
     * @param dateTime 日期时间字符串
     * @return java.time.LocalDateTime
     */
    public static String dateTime2Str(LocalDateTime dateTime) {
        return dateTime2Str(dateTime, DATE_SECOND_TIME_FORMAT);
    }

    /**
     * <p>Title:dateTime2Str</p>
     * <p>Description: 字符串转LocalDateTime</p>
     * @param dateTime 日期时间字符串
     * @param pattern 格式
     * @return java.time.LocalDateTime
     */
    public static String dateTime2Str(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }


    public static void main(String[] args) {
        System.out.println(nowDay());
        System.out.println(nowDayStr());
        System.out.println(nowFormatDay("yyyyMMdd"));

        System.out.println(nowTime());
        System.out.println(nowTimeStr());
        System.out.println(nowFormatTime("HHmmss"));

        System.out.println(nowMilli());
        System.out.println(toMilli("2020-07-13 17:00:01"));
        System.out.println(toMilli("2020-07-13 17:00:01", DATE_SECOND_TIME_FORMAT));

        System.out.println(nowSecond());
        System.out.println(toSecond("2020-07-13 17:00:01"));
        System.out.println(toSecond("2020-07-13 17:00:01", DATE_SECOND_TIME_FORMAT));

        System.out.println(milli2Str(1594631020166L));
        System.out.println(milli2Str(1594631020166L, DATE_SECOND_TIME_FORMAT));
        System.out.println(second2Str(1594631020));
        System.out.println(second2Str(1594631020, DATE_SECOND_TIME_FORMAT));

    }

}