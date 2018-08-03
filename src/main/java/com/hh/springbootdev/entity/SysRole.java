package com.hh.springbootdev.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Desc: 系统角色实体
 * User: jiangningning
 * Date: 2018/4/20
 * Time: 16:43
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    // 角色标识程序中判断使用,如"admin",这个是唯一的.
    private String role;
    // 角色描述
    private String description;
    // 是否可用， 不可用时将不会添加给用户
    private Boolean available = Boolean.FALSE;
    // 角色--权限 多对多关系
    private List<SysPermission> permissionList;
    // 角色--用户 多对多关系
    private List<SysUser> sysUserList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<SysUser> getSysUserList() {
        return sysUserList;
    }

    public void setSysUserList(List<SysUser> sysUserList) {
        this.sysUserList = sysUserList;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", permissionList=" + permissionList +
                ", sysUserList=" + sysUserList +
                '}';
    }
}
