package com.chenbro.deliverybarcode.web.controller;

import com.chenbro.deliverybarcode.model.Box;
import com.chenbro.deliverybarcode.model.HubUser;
import com.chenbro.deliverybarcode.model.Pallet;
import com.chenbro.deliverybarcode.model.base.BoxStatus;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.service.IPalletService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName PalletController
 * @Description TODO
 * @Author c8777
 * @Date 2020/6/10 10:14
 * @Version 1.0
 **/
@Controller
@RequestMapping("pallet")
public class PalletController {

    @Autowired
    private IPalletService palletService;



    @RequestMapping("query")
    public String queryBox(Model model, @RequestParam(name = "page",required =true,defaultValue = "1")Integer pageNum, @RequestParam(name ="size",required = true,defaultValue = "10") Integer pageSize,@RequestParam(name = "palletNo",required = false) String palletNo){
        List<Pallet> pallets = palletService.findAll(pageNum,pageSize,palletNo);
        PageInfo pageInfo = new PageInfo(pallets);
        model.addAttribute("pageInfo",pageInfo);
        return "box/palletQuery";
    }


    @RequestMapping("queryDetail")
    @ResponseBody
    public Result queryDetail(String uuid){
        Pallet pallet = palletService.findDetailById(uuid);
        return new Result(ResultCode.SUCCESS,pallet);
    }

    @RequestMapping(value = "ngBarcode", method = RequestMethod.POST)
    @ResponseBody
    public Result ngBarcode(String barcode) {
        HubUser opUser = (HubUser) SecurityUtils.getSubject().getPrincipal();
        return palletService.ngBarcode(barcode,opUser.getUsername());
    }

    @RequestMapping(value = "passBarcode", method = RequestMethod.POST)
    @ResponseBody
    public Result passBarcode(String barcode) {
        HubUser opUser = (HubUser) SecurityUtils.getSubject().getPrincipal();
        return palletService.passBarcode(barcode,opUser.getUsername());
    }

    @RequestMapping(value = "shipping", method = RequestMethod.POST)
    @ResponseBody
    public Result shipping(String barcode,String vehicleNo) {
        synchronized (this){
            HubUser opUser = (HubUser) SecurityUtils.getSubject().getPrincipal();
            return palletService.shipping(barcode,vehicleNo,opUser.getUsername());
        }
    }


    @RequestMapping(value = "todayShipment", method = RequestMethod.GET)
    @ResponseBody
    public Result todayShipment() {
        return palletService.queryPallets();
    }

    @RequestMapping(value = "disconnect", method = RequestMethod.GET)
    @ResponseBody
    public Result disconnectPallet(String barcode) {
        HubUser opUser = (HubUser) SecurityUtils.getSubject().getPrincipal();
        return palletService.disconnectPallet(barcode,opUser.getUsername());
    }


}
