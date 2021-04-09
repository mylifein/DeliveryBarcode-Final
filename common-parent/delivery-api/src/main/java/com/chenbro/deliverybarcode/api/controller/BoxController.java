package com.chenbro.deliverybarcode.api.controller;

import com.chenbro.deliverybarcode.model.Box;
import com.chenbro.deliverybarcode.model.HubUser;
import com.chenbro.deliverybarcode.model.InspurQueryCond;
import com.chenbro.deliverybarcode.model.User;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.service.IBoxService;
import com.chenbro.deliverybarcode.service.IPalletService;
import com.chenbro.deliverybarcode.service.IWoBatchService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BoxController
 * @Description TODO  裝箱單處理Controller
 * @Author c8777
 * @Date 2020/2/12 10:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("box")
public class BoxController {

    @Autowired
    private IBoxService boxService;

    @Autowired
    private IPalletService palletService;

    @Autowired
    private IWoBatchService woBatchService;

    /**
     * @return java.lang.String
     * @Description //TODO  @RequestMapping 指定restful方式的url,参数需要使用{}包起来  @PathVariable将url{}参数和形参进行绑定
     * @Date 2020/2/12 11:00
     **/
    @RequestMapping(value = "receive/{id:.+}", method = RequestMethod.GET)
    public Result receive(@PathVariable("id") String id) {
        return boxService.findReiveById(id);
    }
    
    /**
    * @Description //TODO  查詢所有收貨的裝箱單信息
    * @Date 2020/2/19 15:49
    * @return com.chenbro.deliverybarcode.model.base.Result
    **/
    @RequestMapping(value = "receive", method = RequestMethod.GET)
    public Result findAll() {
        List<Box> boxes = boxService.findAllReceive();
        return new Result(ResultCode.SUCCESS,boxes);
    }

    /**
    * @Description //TODO  新增裝箱單， 當前方法不啓用
    * @Date 2020/2/19 15:52
    * @return com.chenbro.deliverybarcode.model.base.Result
    **/
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result update(@RequestBody(required = false) Box box) {
        return new Result(ResultCode.FAIL,box);
    }

    /**
    * @Description //TODO 根據裝箱單號進行刪除
    * @Date 2020/2/19 15:54
    * @return com.chenbro.deliverybarcode.model.base.Result
    **/
    @RequestMapping(value = "receive/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id) {
        Box box = boxService.findByUUid(id);
        return new Result(ResultCode.SUCCESS,box);
    }

    /**
    * @Description //TODO  根據裝箱單號/棧板號  收貨處理 將boxStatus 修改爲2
    * @Date 2020/2/19 8:32
    * @return com.chenbro.deliverybarcode.model.base.Result
    **/
    @RequestMapping(value = "receive/{id:.+}", method = RequestMethod.PUT)
    public Result update(@PathVariable("id") String id) {
        return boxService.receive(id);
    }

    /**
    * @Description //TODO 根據裝箱單號/棧板號  出貨處理  將boxStatus 修改爲3
    * @Date 2020/2/19 20:10
    * @return com.chenbro.deliverybarcode.model.base.Result
    **/
    @RequestMapping(value = "shipping/{id:.+}", method = RequestMethod.PUT)
    public Result shipping(@PathVariable("id") String id) {
        return boxService.shipping(id);
    }

    @RequestMapping(value = "shipping/{id:.+}", method = RequestMethod.GET)
    public Result shippingQuery(@PathVariable("id") String id) {
        return new Result(ResultCode.SUCCESS,palletService.findById(id));
    }


    @RequestMapping(value = "reply/{barcode:.+}",method = RequestMethod.PUT)
    public Result replyUpdate(@PathVariable("barcode") String barcode,@RequestBody Map<String,String> map){
        return palletService.replyUpdate(barcode,map.get("statusCode"));
    }


    @RequestMapping(value = "distributeBatchNo",method = {RequestMethod.POST})
    @ResponseBody
    public Result distributeBatchNo(@RequestBody Map<String,String> map){
        // @RequestParam("uniqueNo") String uniqueNo, @RequestParam("productType") String productType
        return woBatchService.distributeBatchNo(map.get("uniqueNo"), map.get("productType"), "API");
    }


    @RequestMapping(value = "ct",method = {RequestMethod.POST,RequestMethod.GET})
    public Result queryCTcodeInfo(@RequestBody Map<String,Object> map){
        InspurQueryCond inspurQueryCond = new InspurQueryCond();
        List<String> cusNos = new ArrayList<>();
        cusNos.add("39264");
        cusNos.add("59866");
        inspurQueryCond.setCusNos(cusNos);
        inspurQueryCond.setDelMatnos((List<String>)map.get("delMatnos"));
        inspurQueryCond.setStatus((String)map.get("status"));
        inspurQueryCond.setFromTime((String)map.get("from"));
        inspurQueryCond.setToTime((String)map.get("to"));
        return boxService.findInspurInfo(inspurQueryCond);
    }



}
