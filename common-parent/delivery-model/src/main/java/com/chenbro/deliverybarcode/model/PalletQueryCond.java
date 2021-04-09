package com.chenbro.deliverybarcode.model;

import java.io.Serializable;

/**
 * @ClassName PalletQueryCond
 * @Description TODO
 * @Author c8777
 * @Date 2020/6/17 14:47
 * @Version 1.0
 **/
public class PalletQueryCond implements Serializable {


    private static final long serialVersionUID = -6721965090725125637L;

    private String palletNo;
    private String workNo;
    private String batchNo;
    private String cusNo;
    private String cusName;
    private String cusPo;
    private String cusMatno;
    private String delMatno;
    private String soOrder;
    private String vehicleNo;
    private String status;
    private String createdStartTime;
    private String createdEndTime;
    private String updatedStartTime;
    private String updatedEndTime;


    public String getPalletNo() {
        return palletNo;
    }

    public void setPalletNo(String palletNo) {
        this.palletNo = palletNo;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPo() {
        return cusPo;
    }

    public void setCusPo(String cusPo) {
        this.cusPo = cusPo;
    }

    public String getCusMatno() {
        return cusMatno;
    }

    public void setCusMatno(String cusMatno) {
        this.cusMatno = cusMatno;
    }

    public String getDelMatno() {
        return delMatno;
    }

    public void setDelMatno(String delMatno) {
        this.delMatno = delMatno;
    }

    public String getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(String soOrder) {
        this.soOrder = soOrder;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getCreatedStartTime() {
        return createdStartTime;
    }

    public void setCreatedStartTime(String createdStartTime) {
        this.createdStartTime = createdStartTime;
    }

    public String getCreatedEndTime() {
        return createdEndTime;
    }

    public void setCreatedEndTime(String createdEndTime) {
        this.createdEndTime = createdEndTime;
    }

    public String getUpdatedStartTime() {
        return updatedStartTime;
    }

    public void setUpdatedStartTime(String updatedStartTime) {
        this.updatedStartTime = updatedStartTime;
    }

    public String getUpdatedEndTime() {
        return updatedEndTime;
    }

    public void setUpdatedEndTime(String updatedEndTime) {
        this.updatedEndTime = updatedEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
