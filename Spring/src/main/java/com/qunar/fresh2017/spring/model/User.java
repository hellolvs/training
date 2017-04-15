package com.qunar.fresh2017.spring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shuai.lv on 2017/3/15.
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private boolean gender;
    private int age;
    private String comment;
    private boolean validity;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd" )
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd" )
    private Date lastModifiedTime;

    public User() {
    }

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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", comment='" + comment + '\'' +
                ", validity=" + validity +
                ", createTime=" + createTime +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
