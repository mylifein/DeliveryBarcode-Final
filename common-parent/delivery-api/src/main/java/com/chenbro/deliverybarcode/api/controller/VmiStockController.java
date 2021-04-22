package com.chenbro.deliverybarcode.api.controller;

import com.chenbro.deliverybarcode.model.VMIEntity;
import com.chenbro.deliverybarcode.model.VMIStock;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.service.IVMIStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName VmiStockController
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/4 9:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("vmi")
public class VmiStockController {

    @Autowired
    private IVMIStockService stockService;


    @RequestMapping(value = "stocks", method = RequestMethod.POST)
    public Result saveVmiStocks (@RequestBody VMIEntity entity) {
        return stockService.saveStocks(entity);
    }
}
