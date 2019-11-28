/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: RouterDto.java</p>
 *
 * @author jiangningning
 * @date 2019/11/27
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/11/27 Create
 */
package com.hh.springbootdev.entity.dto;

import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Title: RouterDto</p>
 * <p>Description: </p>
 *
 * @author jiangningning
 */
@Data
public class RouterDto extends PSInfo{

    // 路由器管理IP
    private String ip;
    // 本路由器的级别 (0：未知、1：CDN/IDC机房边界、2：城域网边界、3：省网骨干、4：省网边界、5：骨干、6：网间出口、7：国际出口)
    private int level;
    // 对应级别的id号（不同级别分别编号。1，2填城市编号；3，4省网级别时，填netid；5，6，7填ispid）
    private int levelId;
    // 运营商id
    private int ispId;
    // 节点名称
    private String nodeName;
    // 主机域名
    private String hostDomain;
    // 路由器端口信息
    private Set<RouterInterface> interfaces;

    public void setInterfaces(List<RouterInterface> interfaces) {
        interfaces.forEach(inf -> {
            // 重写了equals和hashCode
            if (this.interfaces.contains(inf)) {
                RouterInterface oldIf = getRouterInterface(inf);
                RouterInterface result = new RouterInterface();
                result.setAvgBps((oldIf.getAvgBps() + inf.getAvgBps()) / 2);
                result.setMaxBps(Math.max(oldIf.getMaxBps(), inf.getMaxBps()));
                this.interfaces.remove(oldIf);
                this.interfaces.add(result);
            } else {
                this.interfaces.add(inf);
            }
        });
    }

    private RouterInterface getRouterInterface(RouterInterface inf) {
        return this.interfaces.stream().filter(inter -> inter.equals(inf)).findFirst().get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouterDto routerDto = (RouterDto) o;
        return Objects.equals(ip, routerDto.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}
