package com.chenbro.deliverybarcode.model.response;

import java.io.Serializable;

/**
 * @ClassName ProductsInfo
 * @Description TODO
 * @Author z8777
 * @Date 2020/7/31 10:49
 * @Version 1.0
 **/
public class ProductsInfoResult implements Serializable {


    private static final long serialVersionUID = 2330572020675654726L;

    private String deptName;
    private String prodlineDesc;
    private Integer totalQty;
    private Integer boxesQty;
    private String delMatno;


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProdlineDesc() {
        return prodlineDesc;
    }

    public void setProdlineDesc(String prodlineDesc) {
        this.prodlineDesc = prodlineDesc;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public Integer getBoxesQty() {
        return boxesQty;
    }

    public void setBoxesQty(Integer boxesQty) {
        this.boxesQty = boxesQty;
    }

    public String getDelMatno() {
        return delMatno;
    }

    public void setDelMatno(String delMatno) {
        this.delMatno = delMatno;
    }
}
