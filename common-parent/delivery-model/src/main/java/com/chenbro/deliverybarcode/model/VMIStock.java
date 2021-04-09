package com.chenbro.deliverybarcode.model;

import com.chenbro.deliverybarcode.model.base.BaseEntity;

import java.sql.Date;

/**
 * @ClassName VMIStock
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/4 8:43
 * @Version 1.0
 **/
public class VMIStock extends BaseEntity {

    private String cusMatno;
    private String delMatno;
    private String dn;
    private String prodName;
    private Integer prodQty;
    private String wmName;
    private Date storeDate;
    private Integer stockDays;
    private Date planDue;
    private String prodOwner;
    private String supplier;
    private String prodProp;
    private String remark;

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

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getProdQty() {
        return prodQty;
    }

    public void setProdQty(Integer prodQty) {
        this.prodQty = prodQty;
    }

    public String getWmName() {
        return wmName;
    }

    public void setWmName(String wmName) {
        this.wmName = wmName;
    }

    public Date getStoreDate() {
        return storeDate;
    }

    public void setStoreDate(Date storeDate) {
        this.storeDate = storeDate;
    }

    public Integer getStockDays() {
        return stockDays;
    }

    public void setStockDays(Integer stockDays) {
        this.stockDays = stockDays;
    }

    public Date getPlanDue() {
        return planDue;
    }

    public void setPlanDue(Date planDue) {
        this.planDue = planDue;
    }

    public String getProdOwner() {
        return prodOwner;
    }

    public void setProdOwner(String prodOwner) {
        this.prodOwner = prodOwner;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProdProp() {
        return prodProp;
    }

    public void setProdProp(String prodProp) {
        this.prodProp = prodProp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
