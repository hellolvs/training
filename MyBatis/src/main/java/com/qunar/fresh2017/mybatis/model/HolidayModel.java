package com.qunar.fresh2017.mybatis.model;

import java.io.Serializable;

/**
 * Created by shuai.lv on 2017/3/22.
 */
public class HolidayModel implements Serializable{

    private Integer id;
    private Integer staffId;
    private Integer sickNum;
    private Integer annualNum;

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

    public Integer getSickNum() {
        return sickNum;
    }

    public void setSickNum(Integer sickNum) {
        this.sickNum = sickNum;
    }

    public Integer getAnnualNum() {
        return annualNum;
    }

    public void setAnnualNum(Integer annualNum) {
        this.annualNum = annualNum;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "id=" + id +
                ", staffId=" + staffId +
                ", sickNum=" + sickNum +
                ", annualNum=" + annualNum +
                '}';
    }
}
