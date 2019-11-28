/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: PSInfo.java</p>
 *
 * @author jiangningning
 * @date 2019/11/28
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/11/28 Create
 */
package com.hh.springbootdev.entity.dto;

import lombok.Data;

/**
 * <p>Title: PSInfo</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Data
public class PSInfo {

    // 平均流速
    private double avgBps;
    // 最大流速
    private long maxBps;
    // 平均包速
    private double avgPps;
    // 最大包速
    private long maxPps;

    public void setAvgBps(double avgBps) {
        this.avgBps = (this.avgBps + avgBps) / 2;
    }

    public void setMaxBps(long maxBps) {
        this.maxBps = Math.max(this.maxBps, maxBps);
    }

    public void setAvgPps(double avgPps) {
        this.avgPps = (this.avgPps + avgPps) / 2;
    }

    public void setMaxPps(long maxPps) {
        this.maxPps = Math.max(this.maxPps, maxPps);
    }

}
