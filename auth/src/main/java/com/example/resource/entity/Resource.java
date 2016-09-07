package com.example.resource.entity;

import com.example.role.entity.Role;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IBM on 2016/9/7.
 * 资源
 */
@Entity
public class Resource {
    @Id
    @GeneratedValue
    private Long id;//id
    private String resourceName;//资源名称
    private String resourceUrl;//资源路径
    private String resourceType;//资源类型
    private String resourceId;//资源可见id
    private Long order;//排序号
    private String resourceStatus;//资源状态
    @ManyToOne
    private Resource parentId;//父资源id
    @ManyToMany
    private List<Role> roles;//角色
    private Long level;//资源层级
    private String needAuth;//是否需要认证

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public String getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(String resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public Resource getParentId() {
        return parentId;
    }

    public void setParentId(Resource parentId) {
        this.parentId = parentId;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(String needAuth) {
        this.needAuth = needAuth;
    }
}
