package com.chenbro.deliverybarcode.model;

import java.util.List;

/**
 * @ClassName VMIEntity
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/8 9:46
 * @Version 1.0
 **/
public class VMIEntity {

    private Long replyId;
    private List<VMIStock> stocks;

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public List<VMIStock> getStocks() {
        return stocks;
    }

    public void setStocks(List<VMIStock> stocks) {
        this.stocks = stocks;
    }
}
