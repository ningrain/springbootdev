package com.hh.springbootdev.service;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class IpServiceTest extends SpringbootdevApplicationTests {

    @Autowired
    private IpService ipService;

    @Test
    public void getIpInfo() {
        List<Map<String, Object>> ipInfo = ipService.getIpInfo();
        ipInfo.forEach(System.out::println);
    }
}