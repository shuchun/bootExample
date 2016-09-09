package com.example.resource.entity;

import com.example.role.entity.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IBM on 2016/9/9.
 */
@Entity
public class AppMenu {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinTable(name = "role_resource",joinColumns = {
            @JoinColumn(name = "menu_id",referencedColumnName = "id")
    },inverseJoinColumns = {
            @JoinColumn(name="role_id",referencedColumnName = "id")
    })
    private Set<Role> roles=new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
