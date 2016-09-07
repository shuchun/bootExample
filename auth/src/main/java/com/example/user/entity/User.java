package com.example.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by IBM on 2016/7/31.
 * 用户
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;                    //用户id
    private String name;            //用户名
    private String password;             //用户密码
    private int age;
    private String gender;           //性别
    private String status;              //状态：0-正常，1-停用，2-删除
    private Date   createDate;          //创建时间
    private Date   lastModityDate;      //最后修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public User() {
    }

    public User(String name, String password, int age, String gender) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }
}
