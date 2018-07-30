package com.hh.springbootdev.service;

import com.hh.springbootdev.SpringbootdevApplicationTests;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class IpServiceTest extends SpringbootdevApplicationTests {

    @Resource
    private IpService ipService;

    @Test
    public void getIpInfo() {
        List<Map<String, Object>> ipInfo = ipService.getIpInfo();
        ipInfo.forEach(System.out::println);
    }
}