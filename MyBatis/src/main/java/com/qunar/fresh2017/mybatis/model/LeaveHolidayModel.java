package com.qunar.fresh2017.mybatis.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shuai.lv on 2017/3/22.
 */
public class LeaveHolidayModel implements Serializable{

    private Integer id;
    private Integer staffId;
    private Date startDate;
    private Date endDate;
    private Integer dayNum;
    private Integer type;
    private String area;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "LeaveHoliday{" +
                "id=" + id +
                ", staffId=" + staffId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dayNum=" + dayNum +
                ", type=" + type +
                ", area='" + area + '\'' +
                '}';
    }
}
