package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IBM on 2016/7/24.
 * 数据库表实体pojo类
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;//用户id

    private String name;//用户姓名

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

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }
}
