package com.example.resource.entity;

import com.example.role.entity.Role;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IBM on 2016/9/9.
 */
@Entity
public class Resource {
    @Id
    @GeneratedValue
    private Long id;
    private String resourceName;//资源名称
    private String resourceUrl;//资源路径
    private String resourceType;//资源类型
    private String resourceId;//资源可见id
    private Integer resourceOrder;//排序号
    private String resourceStatus;//资源状态
    private Long parentId;//父资源id
    private Integer level;//资源层级
    private String needAuth;//是否需要认证
   /* @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "role_resource",joinColumns = {
            @JoinColumn(name = "resource_id",referencedColumnName = "id")
    },inverseJoinColumns = {
            @JoinColumn(name="role_id",referencedColumnName = "id")
    })*/
    //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,org.hibernate.annotations.CascadeType.PERSIST,org.hibernate.annotations.CascadeType.REFRESH})
    @ManyToMany(mappedBy = "resources",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
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

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
    public String getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(String resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getResourceOrder() {
        return resourceOrder;
    }

    public void setResourceOrder(Integer resourceOrder) {
        this.resourceOrder = resourceOrder;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(String needAuth) {
        this.needAuth = needAuth;
    }
}
