package com.chenbro.deliverybarcode.service;

import com.chenbro.deliverybarcode.model.VMIEntity;
import com.chenbro.deliverybarcode.model.VMIStock;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.service.base.IBaseService;

import java.util.List;

public interface IVMIStockService extends IBaseService<VMIStock> {


    Result saveStocks(VMIEntity vmiEntity);
}
