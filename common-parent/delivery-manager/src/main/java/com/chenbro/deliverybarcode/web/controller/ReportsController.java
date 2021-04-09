package com.chenbro.deliverybarcode.web.controller;

import com.chenbro.deliverybarcode.model.*;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.model.response.CheckReportResult;
import com.chenbro.deliverybarcode.service.IBoxService;
import com.chenbro.deliverybarcode.service.ICheckWorkOrderService;
import com.chenbro.deliverybarcode.service.IPalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName ReportsController
 * @Description TODO
 * @Author c8777
 * @Date 2020/4/28 9:24
 * @Version 1.0
 **/
@Controller
@RequestMapping("report")
public class ReportsController {

    @Autowired
    private IBoxService boxService;

    @Autowired
    private ICheckWorkOrderService checkWorkOrderService;

    @Autowired
    private IPalletService palletService;

    @RequestMapping("delivery")
    public String deliveryReport(){
        return "reports/deliveryReport";
    }


    @RequestMapping("check")
    public String checkReport(){
        return "reports/checkReport";
    }

    @RequestMapping("pallet")
    public String palletReport(){
        return "reports/palletReport";
    }

    @RequestMapping("queryDelivery")
    @ResponseBody
    public Result queryDeliveryReport(DeliveryQueryCond deliveryQueryCond){
        List<Box> boxes = boxService.queryReportByCond(deliveryQueryCond);
        return new Result(ResultCode.SUCCESS,boxes);
    }



    @RequestMapping("queryCheck")
    @ResponseBody
    public Result queryCheckReport(CheckQueryCond checkQueryCond){
        List<CheckReportResult> checkWorkOrders = checkWorkOrderService.queryReportByCond(checkQueryCond);
        return new Result(ResultCode.SUCCESS,checkWorkOrders);
    }


    @RequestMapping("queryPallet")
    @ResponseBody
    public Result queryPalletReport(PalletQueryCond palletQueryCond){
        List<Pallet> pallets = palletService.queryPalletReportByCond(palletQueryCond);
        return new Result(ResultCode.SUCCESS,pallets);
    }



}
