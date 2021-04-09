package com.chenbro.deliverybarcode.model;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName InspurQueryCond
 * @Description TODO
 * @Author z8777
 * @Date 2020/9/24 14:12
 * @Version 1.0
 **/
public class InspurQueryCond implements Serializable {

    private static final long serialVersionUID = -4190155958077453676L;

    private String status;
    private List<String> cusNos;
    private List<String> delMatnos;
    private String fromTime;            //查询起始时间
    private String toTime;              //查询终止时间


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getCusNos() {
        return cusNos;
    }

    public void setCusNos(List<String> cusNos) {
        this.cusNos = cusNos;
    }

    public List<String> getDelMatnos() {
        return delMatnos;
    }

    public void setDelMatnos(List<String> delMatnos) {
        this.delMatnos = delMatnos;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }
}
