package com.qunar.fresh2017.mybatis.model;

import java.io.Serializable;

/**
 * Created by shuai.lv on 2017/3/22.
 */
public class EmployeeModel implements Serializable{

    private Integer id;
    private Integer staffId;
    private String name;
    private String mobile;
    private String area;
    private Integer gender;
    private Integer isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", staffId=" + staffId +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", area='" + area + '\'' +
                ", gender=" + gender +
                ", isValid=" + isValid +
                '}';
    }
}
