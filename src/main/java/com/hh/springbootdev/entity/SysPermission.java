package com.hh.springbootdev.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Desc: 系统权限实体类
 * User: jiangningning
 * Date: 2018/4/20
 * Time: 16:50
 */
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
    // 资源类型， 取值为 enum 类型； 【"menu"| "button"】
    private String resourceType;
    // 资源路径
    private String url;
    /*
    * 权限字符串
    *   menu例子：role:*
    *   button例子：role:create,role:update,role:delete,role:view
    * */
    private String permission;
    // 父编号
    private Long parentId;
    // 父编号列表
    private String parentsIds;

    private Boolean available = Boolean.FALSE;

    private List<SysRole> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentsIds() {
        return parentsIds;
    }

    public void setParentsIds(String parentsIds) {
        this.parentsIds = parentsIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", url='" + url + '\'' +
                ", permission='" + permission + '\'' +
                ", parentId=" + parentId +
                ", parentsIds='" + parentsIds + '\'' +
                ", available=" + available +
                ", roleList=" + roleList +
                '}';
    }
}
