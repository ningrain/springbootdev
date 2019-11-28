/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: DstIpTraceInfo.java</p>
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
import java.util.Set;

/**
 * <p>Title: DstIpTraceInfo</p>
 * <p>Description: 目标IP汇聚信息</p>
 *
 * @author jiangningning
 */
@Data
public class DstIpTraceInfo extends PSInfo{

    // inIP
    private String ip;
    // 节点信息
    private Set<Node> nodes;
    // 路径信息
    private Set<NodePath> paths;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DstIpTraceInfo that = (DstIpTraceInfo) o;
        return Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}
