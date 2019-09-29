package com.hh.springbootdev.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Desc: 系统角色实体
 * User: jiangningning
 * Date: 2018/4/20
 * Time: 16:43
 */
@Data
@ToString
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private long roleId;
    // 角色标识程序中判断使用,如"admin",这个是唯一的.
    private String roleName;
    // 角色描述
    private String roleDesc;
    // 是否可用， 不可用时将不会添加给用户
    private boolean roleState;

}
