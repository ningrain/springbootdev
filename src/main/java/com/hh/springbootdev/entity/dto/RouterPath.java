/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: RouterPath.java</p>
 *
 * @author jiangningning
 * @date 2019/11/28
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/11/28 Create
 */
package com.hh.springbootdev.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Title: RouterPath</p>
 * <p>Description: 路由器路径连线，用于获取节点路径的过渡路径</p>
 *
 * @author jiangningning
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouterPath {

    // 起点路由器
    private RouterDto from;
    // 终点路由器
    private RouterDto to;

}
