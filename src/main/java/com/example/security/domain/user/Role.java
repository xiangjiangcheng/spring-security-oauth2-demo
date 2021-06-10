package com.example.security.domain.user;

import com.example.security.domain.DomainObject;

import javax.persistence.*;
import java.util.List;

/**
 * @author Xiang JiangCheng
 */
@Entity
@Table(name = "role")
public class Role extends DomainObject {

    /**
     * 角色Key
     */
    @Column(name = "role")
    private String role;

    /**
     * 角色名称 例如：管理员、运营人员
     */
    @Column(name = "name")
    private String name;

    /**
     * 角色对应的权限
     * json as List<AccessRight>
     */
    @Column(name = "access_right")
    private String accessRight;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles;

    public Role() {
    }
}
