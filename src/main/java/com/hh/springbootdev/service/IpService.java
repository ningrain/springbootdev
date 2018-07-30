/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: IpService.java</p>
 *
 * @author jiangningning
 * @date 2018/7/30
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/7/30 Create
 */
package com.hh.springbootdev.service;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: IpService</p>
 * <p>Description: </p>
 * @author jiangningning
 */
public interface IpService {

    List<Map<String, Object>> getIpInfo();

}
