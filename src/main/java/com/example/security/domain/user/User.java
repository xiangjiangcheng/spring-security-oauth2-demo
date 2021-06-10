package com.example.security.domain.user;

import com.example.security.domain.DomainObject;

import javax.persistence.*;
import java.util.List;

/**
 * @author Xiang JiangCheng
 */
@Entity
@Table(name = "user")
public class User extends DomainObject {

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    /**
     * 账号状态 1：启用 0：禁用
     */
    @Column(name = "enabled")
    private Boolean enabled;

    /**
     * 是否是管理员 1：是 0：否
     */
    @Column(name = "admin")
    private Boolean admin;

    /**
     * 角色
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles;

    public User() {
    }

    public User(String name, String username, String phoneNumber, String password, Boolean enabled, Boolean admin, List<UserRole> roles) {
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.enabled = enabled;
        this.admin = admin;
        this.roles = roles;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
