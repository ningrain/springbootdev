package com.hh.springbootdev.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/20
 * Time: 16:36
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private long uid;

    private String username;

    private String name;

    private String password;

    private String salt;

    /*
    * 用户状态:
    *   0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户
    *   1:正常状态
    *   2:用户被锁定.
    * */
    private byte state;

    private List<SysRole> roleList;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    // 密码盐
    public String getCredentialsSalt(){
        return this.username + this.salt;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", state=" + state +
                '}';
    }
}
