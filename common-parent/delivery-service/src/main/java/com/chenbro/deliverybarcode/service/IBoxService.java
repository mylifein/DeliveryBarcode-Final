package com.chenbro.deliverybarcode.service;

import com.chenbro.deliverybarcode.model.*;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.response.MonitorInfo;
import com.chenbro.deliverybarcode.model.response.ProductsInfoResult;
import com.chenbro.deliverybarcode.service.base.IBaseService;

import java.util.List;

public interface IBoxService extends IBaseService<Box> {



    Result receive(String cartonNo);


    /**
    * @Description //TODO 根據裝箱單進行入庫處理
    * @Date 2020/4/8 9:24
    * @return com.chenbro.deliverybarcode.model.base.Result
    **/
    Result receive(Box box);

    Result shipping(String id);

    /**
    * @Description //TODO  根據裝箱單進行出貨處理
    * @Date 2020/4/8 9:23
    * @return com.chenbro.deliverybarcode.model.base.Result
    **/
    Result shipping(Box box);


    /**
    * @Description //TODO 查詢所有 倉庫收貨狀態的 裝箱單
    * @Date 2020/2/19 20:42
    * @return java.util.List<com.chenbro.deliverybarcode.model.Box>
    **/
    List<Box> findAllReceive();

    /**
    * @Description //TODO 查詢所有出貨狀態的 裝箱單
    * @Date 2020/2/19 20:42
    * @return java.util.List<com.chenbro.deliverybarcode.model.Box>
    **/
    List<Box> findAllShipping();

    Result findReiveById(String id);

    /**
    * @Description //TODO  查询所有装箱单
    * @Date 2020/3/12 18:13
    * @return java.util.List<com.chenbro.deliverybarcode.model.Box>
    **/
    List<Box> findAll();

    List<Box> findAll(int pageNum,int pageSize);

    /**
    * @Description //TODO  根据装箱单状态查询数量
    * @Date 2020/3/12 23:32
    * @return java.lang.Integer
    **/
    Integer findQtysByStatus(String status);


    /**
    * @Description //TODO  查询原浪潮出货信息
    * @Date 2020/3/17 14:44
    * @return java.util.List<com.chenbro.deliverybarcode.model.InspurPallet>
    **/
    List<InspurPallet> findAllInspurPallet(int pageNum,int pageSize);

    Box findDetailById(String uuid);

    /**
    * @Description //TODO  根據箱號查詢裝箱單號
    * @Date 2020/4/8 8:37
    * @return com.chenbro.deliverybarcode.model.Box
    **/
    Box findById(String id);

    /**
    * @Description //TODO 根据条件查询装箱单报表
    * @Date 2020/5/4 9:07
    * @return java.util.List<com.chenbro.deliverybarcode.model.Box>
    **/
    List<Box> queryReportByCond(DeliveryQueryCond deliveryQueryCond);


    /**
    * @Description //TODO 查询昨天已扫码出货的数据
    * @Date 2020/7/29 14:27
    * @return java.util.List<com.chenbro.deliverybarcode.model.Box>
    **/
    List<ProductsInfoResult> yesterdayShippedBoxes();

    /**
    * @Description //TODO 未出货数据
    * @Date 2020/11/13 8:26
    * @return
    **/
    List<ProductsInfoResult>  delayShippedBoxes();

    /**
    * @Description //TODO  查询当前线别实时生产数据
    * @Date 2020/8/7 13:17
    * @return java.util.List<com.chenbro.deliverybarcode.model.response.MonitorInfo>
    **/
    List<MonitorInfo> queryMonitorInfos(String prodLineId);

    List<MonitorInfo> queryUncompletedInfos(String prodLineId);

    /**
    * @Description //TODO 根據條件查詢浪潮接口信息
    * @Date 2020/9/24 14:11
    * @return com.chenbro.deliverybarcode.model.base.Result
    **/
    Result findInspurInfo(InspurQueryCond inspurQueryCond);

    Result currentPallet();


    Result disconnectBox(String barcode, String username);





}
