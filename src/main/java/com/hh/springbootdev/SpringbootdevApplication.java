package com.hh.springbootdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootdevApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();
        springApplication.setAddCommandLineProperties(false);
        configSetting();
        SpringApplication.run(SpringbootdevApplication.class, args);
    }

    private static void configSetting(){
        String cfgPath = System.getProperty("cfgPath");
        if (cfgPath == null){
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                cfgPath = "F:/application.properties";
            } else {
                cfgPath = "/root/application.properties";
            }
            System.setProperty("cfgPath", cfgPath);
        }
    }
}
