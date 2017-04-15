package com.qunar.fresh2017.exam3.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shuai.lv on 2017/3/23.
 */
public class DiffModel implements Serializable{

    private Integer id;
    private String ownerName;
    private Date diffTime;
    private String sourceFileContent;
    private String targetFileContent;
    private String diffContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(Date diffTime) {
        this.diffTime = diffTime;
    }

    public String getSourceFileContent() {
        return sourceFileContent;
    }

    public void setSourceFileContent(String sourceFileContent) {
        this.sourceFileContent = sourceFileContent;
    }

    public String getTargetFileContent() {
        return targetFileContent;
    }

    public void setTargetFileContent(String targetFileContent) {
        this.targetFileContent = targetFileContent;
    }

    public String getDiffContent() {
        return diffContent;
    }

    public void setDiffContent(String diffContent) {
        this.diffContent = diffContent;
    }

    @Override
    public String toString() {
        return "DiffModel{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", diffTime=" + diffTime +
                ", sourceFileContent='" + sourceFileContent + '\'' +
                ", targetFileContent='" + targetFileContent + '\'' +
                ", diffContent='" + diffContent + '\'' +
                '}';
    }
}
