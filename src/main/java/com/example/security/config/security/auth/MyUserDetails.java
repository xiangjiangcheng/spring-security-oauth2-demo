package com.example.security.config.security.auth;

import com.example.security.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Xiang JiangCheng
 */
public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 4761073170738191351L;
    private static final MyUserDetails ANONYMOUS = new MyUserDetails("anonymous", true);
    private String guid;
    private String name;
    private String username;
    private String password;
    private boolean admin;
    private boolean enabled;
    private boolean isAnonymousUser;

    public MyUserDetails() {
    }

    public MyUserDetails(String name, boolean isAnonymousUser) {
        this.name = name;
        this.isAnonymousUser = isAnonymousUser;
    }

    public MyUserDetails(String name, boolean admin, boolean enabled) {
        this.name = name;
        this.admin = admin;
        this.enabled = enabled;
    }

    public static UserDetails of(User user) {
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setGuid(user.getGuid());
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setAdmin(user.getAdmin());
        userDetails.setEnabled(user.getEnabled());
        userDetails.setName(user.getName());
        return userDetails;
    }

    public static MyUserDetails anonymousUser() {
        return null;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAnonymousUser() {
        return isAnonymousUser;
    }

    public void setAnonymousUser(boolean anonymousUser) {
        isAnonymousUser = anonymousUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
