package com.chenbro.deliverybarcode.model;

import java.io.Serializable;

/**
 * @ClassName SysLog
 * @Description TODO
 * @Author c8777
 * @Date 2020/5/10 17:06
 * @Version 1.0
 **/
public class SysLog implements Serializable {

    private static final long serialVersionUID = -7769210362864984977L;

    private String uuid;
    private String visitTime;
    private String username;
    private String ip;
    private String url;
    private Long executionTime;
    private String method;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
