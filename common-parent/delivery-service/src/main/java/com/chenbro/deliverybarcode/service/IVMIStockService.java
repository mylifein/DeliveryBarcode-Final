package com.chenbro.deliverybarcode.service;

import com.chenbro.deliverybarcode.model.VMIStock;
import com.chenbro.deliverybarcode.service.base.IBaseService;

import java.util.List;

public interface IVMIStockService extends IBaseService<VMIStock> {


    void saveStocks(List<VMIStock> stocks);
}
