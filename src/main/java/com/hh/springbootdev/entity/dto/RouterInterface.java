/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: RouterPortInfo.java</p>
 *
 * @author jiangningning
 * @date 2019/11/27
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/11/27 Create
 */
package com.hh.springbootdev.entity.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Title: RouterPortInfo</p>
 * <p>Description: 路由器接口信息</p>
 * <p>该类中路由器端口一进一出一一对应</p>
 *
 * @author jiangningning
 */
@Data
public class RouterInterface extends PSInfo{

    // id，格式：ip-inPort-inIp
    private String id;
    // 路由器管理IP
    private String ip;
    // 出端口
    private int outPort;
    // 出端口对应IP
    private String outIP;
    // 进端口
    private int inPort;
    // 进端口对应IP
    private String inIP;
    // 该入端口源IP信息
    private Set<SrcIp> srcIps = new HashSet<>();

    public void setId() {
        this.id = this.ip + "-" + this.inPort + "-" + this.inIP;;
    }

    public void setSrcIps(List<SrcIp> srcIps) {
        srcIps.forEach(newSrcIp -> {
            // 重写了equals和hashCode
            if (this.srcIps.contains(newSrcIp)) {
                SrcIp oldSrcIp = getSrcIp(newSrcIp);
                SrcIp result = new SrcIp();
                result.setSrcIP(newSrcIp.getSrcIP());
                result.setAvgBps((oldSrcIp.getAvgBps() + newSrcIp.getAvgBps()) / 2);
                result.setMaxBps(Math.max(oldSrcIp.getMaxBps(), newSrcIp.getMaxBps()));
                this.srcIps.remove(oldSrcIp);
                this.srcIps.add(result);
            } else {
                this.srcIps.add(newSrcIp);
            }
        });
    }

    /**
     * <p>Title:getSrcIp</p>
     * <p>Description: 从RouterInterface对象中根据具体IP信息获取srcIP对象</p>
     *
     * @param srcIp String IP
     * @return com.hh.ddos.entity.dto.SrcIp
     */
    private SrcIp getSrcIp(SrcIp srcIp) {
        return this.srcIps.stream().filter(src -> src.equals(srcIp)).findFirst().get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouterInterface that = (RouterInterface) o;
        return ip.equals(that.ip) &&
                outPort == that.outPort &&
                outIP.equals(that.outIP) &&
                inPort == that.inPort &&
                inIP.equals(that.inIP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, outPort, outIP, inPort, inIP);
    }

}
