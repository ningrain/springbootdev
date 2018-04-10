package com.hh.springbootdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootdevApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();
        springApplication.setAddCommandLineProperties(false);
        SpringApplication.run(SpringbootdevApplication.class, args);
    }
}
