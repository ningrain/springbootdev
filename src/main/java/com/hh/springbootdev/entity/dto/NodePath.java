/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: Path.java</p>
 *
 * @author jiangningning
 * @date 2019/11/27
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/11/27 Create
 */
package com.hh.springbootdev.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Title: Path</p>
 * <p>Description: 用于连接不同节点之间的连线信息（溯源地图使用）</p>
 *
 * @author jiangningning
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodePath {

    // 该连线信息起点
    private Node from;
    // 该连线信息终点
    private Node to;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodePath nodePath = (NodePath) o;
        return Objects.equals(from, nodePath.from) &&
                Objects.equals(to, nodePath.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
