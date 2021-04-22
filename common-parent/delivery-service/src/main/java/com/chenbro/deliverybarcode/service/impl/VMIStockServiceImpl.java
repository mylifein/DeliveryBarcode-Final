package com.chenbro.deliverybarcode.service.impl;

import com.chenbro.deliverybarcode.model.CommonException;
import com.chenbro.deliverybarcode.model.VMIEntity;
import com.chenbro.deliverybarcode.model.VMIStock;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.service.IVMIStockService;
import com.chenbro.deliverybarcode.service.base.BaseServiceImpl;
import com.chenbro.deliverybarcode.utils.DateUtils;
import com.chenbro.deliverybarcode.utils.UuidUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * @ClassName VMIStockServiceImpl
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/4 10:52
 * @Version 1.0
 **/
@Service
@Transactional
public class VMIStockServiceImpl extends BaseServiceImpl<VMIStock> implements IVMIStockService {


    @Override
    public VMIStock findByUUid(String uuid) {
        return null;
    }

    @Override
    public void deleteById(String uuid) throws CommonException {

    }

    @Override
    public void update(VMIStock vmiStock) {

    }

    @Override
    public void insert(VMIStock vmiStock) {
        //设置主键的值
        vmiStock.setCreateBy("VMI-User");
        vmiStock.setUuid(UuidUtils.getUUID());
        vmiStock.setCreateDate(DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        vmiStockMapper.insert(vmiStock);
    }

    @Override
    public Result saveStocks(VMIEntity vmiEntity) {
        if (ObjectUtils.isEmpty(vmiEntity.getReplyId())) {
            vmiEntity.setReplyId(System.currentTimeMillis());
            for (VMIStock stock : vmiEntity.getStocks()) {
                stock.setReplyId(vmiEntity.getReplyId());
                this.insert(stock);
            }
        } else {
            vmiStockMapper.deleteByReplyId(vmiEntity.getReplyId());
            for (VMIStock stock : vmiEntity.getStocks()) {
                stock.setReplyId(vmiEntity.getReplyId());
                this.insert(stock);
            }
        }
        return new Result(ResultCode.SUCCESS, vmiEntity.getReplyId());
    }
}
