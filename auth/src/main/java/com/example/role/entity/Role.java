package com.example.role.entity;

import com.example.user.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by IBM on 2016/9/7.
 * 角色
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;//id
    @OneToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<User> users;//用户
    private String roleName;//角色名称
    private String roleDesc;//角色描述
    private String roleStatus;//角色状态
    private String roleShowable;//角色是否前台可见
    private String createUser;//创建人
    private Date createTime;//创建时间
    private String updateUser;//最后修改人
    private Date updateTime;//最近修改时间


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
}
