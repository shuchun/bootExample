package com.example.bootbatch.domain;

import javax.validation.constraints.Size;

/**
 * Created by IBM on 2016/8/7.
 * 实体
 */
public class Person {

    private int id;
    @Size(max=2,min=1)
    private String name;

    private int age;
    private String nation;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
