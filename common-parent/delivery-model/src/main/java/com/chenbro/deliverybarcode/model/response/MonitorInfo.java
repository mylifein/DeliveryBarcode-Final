package com.chenbro.deliverybarcode.model.response;

import java.io.Serializable;

/**
 * @ClassName MonitorInfo
 * @Description TODO
 * @Author z8777
 * @Date 2020/8/7 11:44
 * @Version 1.0
 **/
public class MonitorInfo implements Serializable {

    private static final long serialVersionUID = -2896459566156688048L;

    private String workNo;
    private String delMatno;
    private String woQuantity;
    private String totayTotalQty;
    private String totalQty;
    private String prodlineDesc;
    private String createTime;


    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getDelMatno() {
        return delMatno;
    }

    public void setDelMatno(String delMatno) {
        this.delMatno = delMatno;
    }

    public String getWoQuantity() {
        return woQuantity;
    }

    public void setWoQuantity(String woQuantity) {
        this.woQuantity = woQuantity;
    }

    public String getTotayTotalQty() {
        return totayTotalQty;
    }

    public void setTotayTotalQty(String totayTotalQty) {
        this.totayTotalQty = totayTotalQty;
    }

    public String getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
    }

    public String getProdlineDesc() {
        return prodlineDesc;
    }

    public void setProdlineDesc(String prodlineDesc) {
        this.prodlineDesc = prodlineDesc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
