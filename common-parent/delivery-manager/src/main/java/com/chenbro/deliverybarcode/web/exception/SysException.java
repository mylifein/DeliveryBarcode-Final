package com.chenbro.deliverybarcode.web.exception;

/**
 * @ClassName SysException
 * @Description TODO  自定义异常类，用于提示异常信息
 * @Author z8777
 * @Date 2020/10/23 13:59
 * @Version 1.0
 **/
public class SysException extends Exception{

    //存储提示信息
    private String message;


    public SysException(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
