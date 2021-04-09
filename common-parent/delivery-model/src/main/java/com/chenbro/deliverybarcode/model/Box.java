package com.chenbro.deliverybarcode.model;

import com.chenbro.deliverybarcode.model.base.BaseEntity;

import java.util.List;

/**
 * @ClassName Box
 * @Description TODO
 * @Author c8777
 * @Date 2020/2/14 8:51
 * @Version 1.0
 **/
public class Box extends BaseEntity {

    private String cartonNo;
    private Integer cartonQty;
    private String prodlineId;
    private String prodLineDesc;
    private String cartonStatus;
    private String capacityNo;
    private String packType;
    private String ruleNo;
    private String workNo;
    private String cusNo;
    private String cusName;
    private String cusPo;
    private String poQty;
    private String cusMatno;
    private String delMatno;
    private String offiNo;
    private String verNo;
    private String woQuantity;
    private String modelNo;
    private String soOrder;
    private String vehicleNo;
    private List<Ctcode> ctcodes;

    public String getCartonNo() {
        return cartonNo;
    }

    public void setCartonNo(String cartonNo) {
        this.cartonNo = cartonNo;
    }

    public Integer getCartonQty() {
        return cartonQty;
    }

    public void setCartonQty(Integer cartonQty) {
        this.cartonQty = cartonQty;
    }

    public String getProdlineId() {
        return prodlineId;
    }

    public void setProdlineId(String prodlineId) {
        this.prodlineId = prodlineId;
    }

    public String getCartonStatus() {
        return cartonStatus;
    }

    public void setCartonStatus(String cartonStatus) {
        this.cartonStatus = cartonStatus;
    }

    public String getCapacityNo() {
        return capacityNo;
    }

    public void setCapacityNo(String capacityNo) {
        this.capacityNo = capacityNo;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
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

    public String getPoQty() {
        return poQty;
    }

    public void setPoQty(String poQty) {
        this.poQty = poQty;
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

    public String getOffiNo() {
        return offiNo;
    }

    public void setOffiNo(String offiNo) {
        this.offiNo = offiNo;
    }

    public String getVerNo() {
        return verNo;
    }

    public void setVerNo(String verNo) {
        this.verNo = verNo;
    }

    public String getWoQuantity() {
        return woQuantity;
    }

    public void setWoQuantity(String woQuantity) {
        this.woQuantity = woQuantity;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(String soOrder) {
        this.soOrder = soOrder;
    }

    public List<Ctcode> getCtcodes() {
        return ctcodes;
    }

    public void setCtcodes(List<Ctcode> ctcodes) {
        this.ctcodes = ctcodes;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getProdLineDesc() {
        return prodLineDesc;
    }

    public void setProdLineDesc(String prodLineDesc) {
        this.prodLineDesc = prodLineDesc;
    }
}
