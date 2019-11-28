/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: SrcIp.java</p>
 *
 * @author jiangningning
 * @date 2019/11/27
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/11/27 Create
 */
package com.hh.springbootdev.entity.dto;

import lombok.Data;

import java.util.Objects;

/**
 * <p>Title: SrcIp</p>
 * <p>Description: 路由器端口对应的源IP信息</p>
 *
 * @author jiangningning
 */
@Data
public class SrcIp extends PSInfo {

    // IP
    private String srcIP;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SrcIp srcIp = (SrcIp) o;
        return Objects.equals(srcIP, srcIp.srcIP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(srcIP);
    }
}
