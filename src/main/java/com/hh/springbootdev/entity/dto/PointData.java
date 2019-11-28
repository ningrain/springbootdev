/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: PointData.java</p>
 *
 * @author jiangningning
 * @date 2019/11/28
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/11/28 Create
 */
package com.hh.springbootdev.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * <p>Title: PointData</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Data
@NoArgsConstructor
public class PointData<T> extends PSInfo{

    private String id;

    private String parentId;

    private T data;

    public PointData(String id, String patentId, T data) {
        this.id = id;
        this.parentId = patentId;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointData pointData = (PointData) o;
        return Objects.equals(id, pointData.id) &&
                Objects.equals(parentId, pointData.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId);
    }
}
