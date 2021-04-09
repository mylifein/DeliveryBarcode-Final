package com.chenbro.deliverybarcode.service;

import com.chenbro.deliverybarcode.model.WoBatch;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.service.base.IBaseService;

import java.util.Calendar;
import java.util.List;

public interface IWoBatchService extends IBaseService<WoBatch> {

    List<WoBatch> findAll(int pageNum, int pageSize,String woNo);


    Result updateBatchNo(WoBatch woBatch);

    /**
    * @Description //TODO  根据唯一号码(工单号/PO） 和 产品类别，占用一个批次号
    * @Date 2020/12/10 16:27
    * @return com.chenbro.deliverybarcode.model.base.Result
    **/
    Result distributeBatchNo(String uniqueNo, String productType, String opUser);

    String getBatchByDateCode(Calendar dateCode,String productType);
}
