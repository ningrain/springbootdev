package com.hh.springbootdev.kafka;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;


/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/23
 * Time: 11:48
 */
public class Main {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "111.231.109.105:9092");
        AdminClient adminClient = AdminClient.create(prop);
        List<NewTopic> topics = new ArrayList<>();
        NewTopic newTopic = new NewTopic("topic-test", 1, (short) 1);
        topics.add(newTopic);
        CreateTopicsResult result = adminClient.createTopics(topics);
        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
