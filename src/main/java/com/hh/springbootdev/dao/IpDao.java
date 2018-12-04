package com.hh.springbootdev.dao;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/11
 * Time: 11:58
 */
@Component
public interface IpDao {

    List<Map<String, Object>> getIpInfo();
}
