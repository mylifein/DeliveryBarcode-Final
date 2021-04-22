package com.chenbro.deliverybarcode.mapper;

import com.chenbro.deliverybarcode.mapper.base.BaseMapper;
import com.chenbro.deliverybarcode.model.VMIStock;

import java.util.List;

public interface VMIStockMapper extends BaseMapper<VMIStock> {

    List<VMIStock> findAll(VMIStock vmiStock);

    void deleteByReplyId(Long replyId);
}
