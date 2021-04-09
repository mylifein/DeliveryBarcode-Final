package com.chenbro.deliverybarcode.model.response;

import java.io.Serializable;

/**
 * @ClassName PalletInfoResult
 * @Description TODO
 * @Author z8777
 * @Date 2020/8/17 15:12
 * @Version 1.0
 **/
public class PalletInfoResult implements Serializable {

    private static final long serialVersionUID = -7243064348721241496L;

    private String palletNo;
    private Integer palletQty;
    private String delMatno;
    private String cusMatno;
    private String cusName;
    private String soOrder;
    private String status;
    private String statusDesc;

    public String getPalletNo() {
        return palletNo;
    }

    public void setPalletNo(String palletNo) {
        this.palletNo = palletNo;
    }

    public Integer getPalletQty() {
        return palletQty;
    }

    public void setPalletQty(Integer palletQty) {
        this.palletQty = palletQty;
    }

    public String getDelMatno() {
        return delMatno;
    }

    public void setDelMatno(String delMatno) {
        this.delMatno = delMatno;
    }

    public String getCusMatno() {
        return cusMatno;
    }

    public void setCusMatno(String cusMatno) {
        this.cusMatno = cusMatno;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(String soOrder) {
        this.soOrder = soOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
