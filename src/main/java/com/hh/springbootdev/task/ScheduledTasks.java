/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: ScheduledTasks.java</p>
 *
 * @author jiangningning
 * @date 2018/12/7
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/12/7 Create
 */
package com.hh.springbootdev.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: ScheduledTasks</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Component
public class ScheduledTasks {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    // @Scheduled(cron = "*/5 * * * * *")
    private void reportCurrTime(){
        System.out.println(sdf.format(new Date()));
    }
}
