package com.hh.springbootdev.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Desc: 系统权限实体类
 * User: jiangningning
 * Date: 2018/4/20
 * Time: 16:50
 */
@Data
@ToString
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

}
