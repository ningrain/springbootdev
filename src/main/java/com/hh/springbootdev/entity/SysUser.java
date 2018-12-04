package com.hh.springbootdev.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/20
 * Time: 16:36
 */
public class SysUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private long userId;

    private String username;

    private String realname;

    private String password;

    /*
     * 用户状态:
     *   0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户
     *   1:正常状态
     *   2:用户被锁定.
     * */
    private boolean userState;

    private List<SysRole> roleList;

    public SysUser(long userId, String username, String realname, String password, boolean userState, List<SysRole> roleList) {
        this.userId = userId;
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.userState = userState;
        this.roleList = roleList;
    }

    public SysUser() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = this.getRoleList();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return auths;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getUserState() {
        return userState;
    }

    public void setUserState(boolean userState) {
        this.userState = userState;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                ", userState=" + userState +
                ", roleList=" + roleList +
                '}';
    }
}
