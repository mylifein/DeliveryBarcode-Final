package com.chenbro.deliverybarcode.model.response;

import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @ClassName InspurCT
 * @Description TODO  浪潮接口实体信息
 * @Author z8777
 * @Date 2020/9/24 13:52
 * @Version 1.0
 **/
public class InspurCT implements Serializable {


    private static final long serialVersionUID = -6586292638646866594L;

    private String proCategory;
    private String workOrderID;
    private String inspurQN;
    private String inspurPN;
    private String delMatno;
    private String vendorCode;
    private String vendorName;
    private String operationCode;
    private String operationName;
    private String startTime;
    private String testResult;
    private String status;

    public InspurCT(){
        this.proCategory = "機箱";
        this.vendorCode = "100087";
        this.vendorName = "勤誠興業股份有限公司";
        this.testResult = "Pass";
    }

    public String getProCategory() {
        return proCategory;
    }

    public void setProCategory(String proCategory) {
        this.proCategory = proCategory;
    }

    public String getWorkOrderID() {
        return workOrderID;
    }

    public void setWorkOrderID(String workOrderID) {
        this.workOrderID = workOrderID;
    }

    public String getInspurQN() {
        return inspurQN;
    }

    public void setInspurQN(String inspurQN) {
        this.inspurQN = inspurQN;
    }

    public String getInspurPN() {
        return inspurPN;
    }

    public void setInspurPN(String inspurPN) {
//        if(!ObjectUtils.isEmpty(inspurPN)){
//            String[] split = inspurPN.split(" ");
//            inspurPN = split[0];
//        }
        this.inspurPN = inspurPN;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelMatno() {
        return delMatno;
    }

    public void setDelMatno(String delMatno) {
        this.delMatno = delMatno;
    }
}
