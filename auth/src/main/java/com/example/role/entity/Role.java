package com.example.role.entity;

import com.example.resource.entity.Resource;
import com.example.user.entity.User;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.*;

/**
 * Created by IBM on 2016/9/7.
 * 角色
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    //@Column(name = "role_id")
    private Long id;//id
    private String roleName;//角色名称
    private String roleDesc;//角色描述
    private String roleStatus;//角色状态
    private String roleShowable;//角色是否前台可见
    private String createUser;//创建人
    private Date createTime;//创建时间
    private String updateUser;//最后修改人
    private Date updateTime;//最近修改时间
    @OneToMany(mappedBy = "role",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.EAGER)
    private Set<User> user= new HashSet<>();//用户
    /*@ManyToMany(mappedBy = "roles",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)*/
    //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,org.hibernate.annotations.CascadeType.PERSIST,org.hibernate.annotations.CascadeType.MERGE})
    @ManyToMany
    @JoinTable(name = "role_resource",joinColumns = {
            @JoinColumn(name = "role_id",referencedColumnName = "id")
    },inverseJoinColumns = {
            @JoinColumn(name = "resource_id",referencedColumnName = "id")
    })
    private Set<Resource> resources=new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getRoleShowable() {
        return roleShowable;
    }

    public void setRoleShowable(String roleShowable) {
        this.roleShowable = roleShowable;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
}
