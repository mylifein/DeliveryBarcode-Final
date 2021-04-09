package com.chenbro.deliverybarcode.service.impl;

import com.chenbro.deliverybarcode.model.CommonException;
import com.chenbro.deliverybarcode.model.VMIStock;
import com.chenbro.deliverybarcode.service.IVMIStockService;
import com.chenbro.deliverybarcode.service.base.BaseServiceImpl;
import com.chenbro.deliverybarcode.utils.DateUtils;
import com.chenbro.deliverybarcode.utils.UuidUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        vmiStock.setUuid(UuidUtils.getUUID());
        vmiStock.setCreateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
        vmiStockMapper.insert(vmiStock);
    }

    @Override
    public void saveStocks(List<VMIStock> stocks) {
        for (VMIStock stock : stocks) {
            this.insert(stock);
        }
    }
}
