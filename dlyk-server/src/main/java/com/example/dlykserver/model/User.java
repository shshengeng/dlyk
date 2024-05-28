package com.example.dlykserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

/**
 * 用户表
 * @TableName t_user
 */
@Data
public class User implements UserDetails, Serializable {
    /**
     * 主键，自动增长，用户ID
     */
    private Integer id;

    /**
     * 登录账号
     */
    private String loginAct;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户手机
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 账户是否没有过期，0已过期 1正常
     */
    private Integer accountNoExpired;

    /**
     * 密码是否没有过期，0已过期 1正常
     */
    private Integer credentialsNoExpired;

    /**
     * 账号是否没有锁定，0已锁定 1正常
     */
    private Integer accountNoLocked;

    /**
     * 账号是否启用，0禁用 1启用
     */
    private Integer accountEnabled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 编辑人
     */
    private Integer editBy;

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

    /**
     *角色List
     */
    private List<String> roleList;

    /**
     * 权限标识符List
     */
    private List<String> permissionList;

    private static final long serialVersionUID = 1L;

    //实现UserDetails接口的方法
    //-----------------------------------------------------
    //@JsonIgnore在 Java 对象序列化为 JSON 数据时，如果某些字段或方法不需要包含在输出的 JSON 中，可以使用 @JsonIgnore 注解来标记这些字段或方法，以便在序列化时忽略它们。

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        //role
        if(!ObjectUtils.isEmpty(roleList)) {
            this.roleList.forEach(role ->
                    authorities.add(new SimpleGrantedAuthority(role))
            );
        }

        //permission
        if(!ObjectUtils.isEmpty(permissionList)) {
            this.permissionList.forEach(permission ->
                    authorities.add(new SimpleGrantedAuthority(permission))
            );
        }

        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.loginPwd;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.loginAct;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNoExpired == 1;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNoLocked == 1;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNoExpired == 1;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.accountEnabled == 1;
    }
}