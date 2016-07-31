package com.example.user.entity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by IBM on 2016/7/31.
 * 用户
 */
@Entity
public class User {
    private Long id;                    //用户id
    private String userName;            //用户名
    private String userPwd;             //用户密码
    private String userEmail;           //邮箱
    private String status;              //状态：0-正常，1-停用，2-删除
    private Date   createDate;          //创建时间
    private Date   lastModityDate;      //最后修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModityDate() {
        return lastModityDate;
    }

    public void setLastModityDate(Date lastModityDate) {
        this.lastModityDate = lastModityDate;
    }
}
