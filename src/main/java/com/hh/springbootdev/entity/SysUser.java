package com.hh.springbootdev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = this.getRoleList();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return auths;
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

}
