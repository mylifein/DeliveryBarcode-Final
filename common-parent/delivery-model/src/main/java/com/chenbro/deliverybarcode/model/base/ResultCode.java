package com.chenbro.deliverybarcode.model.base;

/**
* @Description //TODO  公共返回码   成功：10000  失败：10001  未登录：10002 未授权：10003 抛出异常：99999
* @Date 2020/2/13 23:16
* @return 
**/
public enum ResultCode {

    SUCCESS(true,10000,"操作成功"),
    // ---系统错误返回码   参数错误：10001-19999   业务错误：30001-39999   数据错误：50001-599999   接口错误：60001-69999---
    FAIL(false,10001,"操作失败,数据不符合操作条件！"),
    ADDUSERFAIL(false,20002,"该用户名已存在，添加失败！"),
    FAILDISTRIBUTE(false,20003,"批次领取失败，领取编号已占用批次！"),
    UNAUTHENTICATED(false,10002,"您还未登陆"),
    UNAUTHORISE(false,10003,"权限不足"),
    INVALIDID(false,10004,"当前Barcode无效"),
    SCANED(false,10005,"裝箱單都已經掃碼處理完成"),
    INCONFORMITY(false,10006,"裝箱單都不符合當前掃碼處理條件！"),
    INVALIDIDSTATUS(false,10007,"当前傳入的StatusCode為空值,請檢查！"),
    STATUSREPEAT(false,10008,"当前傳入的StatusCode值與Barcode狀態值一致,請檢查！"),
    STATUSSHIPPED(false,10009,"当前傳入條碼狀態值爲已出貨,請檢查！"),
    STATUSNOTPACK(false,10010,"当前傳入的barcode值為非裝箱狀態,不可進行其他狀態變更操作！"),
    STATUSNG(false,10010,"当前傳入的barcode值為NG狀態,不可進行其他狀態變更操作！"),
    USERNAMEORPASSWORDERROR(false,20001,"用户名或密码错误！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");

    //操作是否成功
    Boolean success;
    //操作码
    Integer code;
    //提示信息
    String message;

    ResultCode(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean success(){
        return success;
    }

    public Integer code(){
        return code;
    }

    public String message(){
        return message;
    }

}
