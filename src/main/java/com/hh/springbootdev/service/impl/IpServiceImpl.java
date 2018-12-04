/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: IpServiceImpl.java</p>
 *
 * @author jiangningning
 * @date 2018/7/30
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/7/30 Create
 */
package com.hh.springbootdev.service.impl;

import com.hh.springbootdev.annotation.TargetDataSource;
import com.hh.springbootdev.dao.IpDao;
import com.hh.springbootdev.entity.CustomizeDS;
import com.hh.springbootdev.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: IpServiceImpl</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
@Service
public class IpServiceImpl implements IpService {

    private final IpDao ipDao;

    @Autowired
    public IpServiceImpl(IpDao ipDao) {
        this.ipDao = ipDao;
    }

    @Override
    @TargetDataSource(CustomizeDS.CLUSTER)
    public List<Map<String, Object>> getIpInfo() {
        return ipDao.getIpInfo();
    }
}
