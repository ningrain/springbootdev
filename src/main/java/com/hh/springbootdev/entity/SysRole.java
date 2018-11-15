package com.hh.springbootdev.entity;

import java.io.Serializable;

/**
 * Desc: 系统角色实体
 * User: jiangningning
 * Date: 2018/4/20
 * Time: 16:43
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private long roleId;
    // 角色标识程序中判断使用,如"admin",这个是唯一的.
    private String roleName;
    // 角色描述
    private String roleDesc;
    // 是否可用， 不可用时将不会添加给用户
    private boolean roleState;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public boolean getRoleState() {
        return roleState;
    }

    public void setRoleState(boolean roleState) {
        this.roleState = roleState;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", roleState=" + roleState +
                '}';
    }
}
