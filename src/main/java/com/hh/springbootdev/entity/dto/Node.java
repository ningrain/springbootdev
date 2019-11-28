/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: Node.java</p>
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
 * <p>Title: Node</p>
 * <p>Description: 节点信息类（用于溯源地图）</p>
 *
 * @author jiangningning
 */
@Data
public class Node extends PSInfo{

    // 节点id(格式：level-levelId-ispId， eg: 5-1-1)
    // 节点类型为target时，id取ip
    private String id;
    // 节点名称
    private String name;
    // 节点类型（取值范围：router、target，目前会包含路由器、攻击目标）
    private NodeType type;
    // 节点级别
    private int level;
    // 节点级别
    private int levelId;
    // 运营商id
    private int ispId;
    // 路由器信息
    private Set<RouterDto> routers;

    public void setId() {
        this.id = this.level + "-" + this.levelId + "-" + this.ispId;
    }

    public void setRouters(List<RouterDto> routers) {
        routers.forEach(router -> {
            // 重写了equals和hashCode
            if (this.routers.contains(router)) {
                RouterDto oldRouterDto = getRouter(router);
                RouterDto result = new RouterDto();
                result.setAvgBps((oldRouterDto.getAvgBps() + router.getAvgBps()) / 2);
                result.setMaxBps(Math.max(oldRouterDto.getMaxBps(), router.getMaxBps()));
                this.routers.remove(oldRouterDto);
                this.routers.add(result);
            } else {
                this.routers.add(router);
            }
        });
    }

    private RouterDto getRouter(RouterDto router) {
        return this.routers.stream().filter(inter -> inter.equals(router)).findFirst().get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id.equals(node.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
