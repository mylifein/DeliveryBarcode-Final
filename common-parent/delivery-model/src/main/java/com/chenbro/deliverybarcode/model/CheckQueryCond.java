package com.chenbro.deliverybarcode.model;

import java.io.Serializable;

/**
 * @ClassName CheckQueryCond
 * @Description TODO
 * @Author c8777
 * @Date 2020/5/22 8:23
 * @Version 1.0
 **/
public class CheckQueryCond implements Serializable {


    private static final long serialVersionUID = 4407474683653709930L;

    private String qualityNo;
    private String woNo;
    private String delMatno;
    private String deptId;
    private String deptCode;
    private String modelNo;
    private String status;              //质检状态
    private Integer checkTimes;
    private String startCheckTime;
    private String endCheckTime;
    private String deliverMan;


    public String getQualityNo() {
        return qualityNo;
    }

    public void setQualityNo(String qualityNo) {
        this.qualityNo = qualityNo;
    }

    public String getWoNo() {
        return woNo;
    }

    public void setWoNo(String woNo) {
        this.woNo = woNo;
    }

    public String getDelMatno() {
        return delMatno;
    }

    public void setDelMatno(String delMatno) {
        this.delMatno = delMatno;
    }


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCheckTimes() {
        return checkTimes;
    }

    public void setCheckTimes(Integer checkTimes) {
        this.checkTimes = checkTimes;
    }

    public String getStartCheckTime() {
        return startCheckTime;
    }

    public void setStartCheckTime(String startCheckTime) {
        this.startCheckTime = startCheckTime;
    }

    public String getEndCheckTime() {
        return endCheckTime;
    }

    public void setEndCheckTime(String endCheckTime) {
        this.endCheckTime = endCheckTime;
    }

    public String getDeliverMan() {
        return deliverMan;
    }

    public void setDeliverMan(String deliverMan) {
        this.deliverMan = deliverMan;
    }


}
