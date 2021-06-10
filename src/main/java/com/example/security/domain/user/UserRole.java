package com.example.security.domain.user;

import com.example.security.domain.DomainObject;

import javax.persistence.*;

/**
 * @author Xiang JiangCheng
 */
@Entity
@Table(name = "user_role")
public class UserRole extends DomainObject {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole() {
    }
}
