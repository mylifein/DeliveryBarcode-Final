package com.chenbro.deliverybarcode.service.impl;

import com.chenbro.deliverybarcode.model.*;
import com.chenbro.deliverybarcode.model.base.BarcodeType;
import com.chenbro.deliverybarcode.model.base.BoxStatus;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.model.response.InspurCT;
import com.chenbro.deliverybarcode.model.response.MonitorInfo;
import com.chenbro.deliverybarcode.model.response.ProductsInfoResult;
import com.chenbro.deliverybarcode.service.IBoxService;
import com.chenbro.deliverybarcode.service.base.BaseServiceImpl;
import com.chenbro.deliverybarcode.utils.BarcodeHistoryUtils;
import com.chenbro.deliverybarcode.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BoxServiceImpl
 * @Description TODO
 * @Author c8777
 * @Date 2020/2/14 11:21
 * @Version 1.0
 **/
@Service
@Transactional
public class BoxServiceImpl extends BaseServiceImpl<Box> implements IBoxService {



    @Override
    public Box findByUUid(String uuid) {
        return boxMapper.findByUUid(uuid);
    }

    @Override
    public void deleteById(String uuid) {
         boxMapper.deleteById(uuid);
    }

    @Override
    public void update(Box box) {
        boxMapper.update(box);
    }

    @Override
    public void insert(Box box) {

        boxMapper.insert(box);
    }


    @Override
    public Result receive(String cartonNo) {
        //查询当前carton 是否Carton No/ Pallet No
        Box box = boxMapper.findById(cartonNo);
        if(box == null){
            List<Box> boxes = boxMapper.findBoxsByPalletNo(cartonNo);
            if(boxes == null || boxes.size() < 1){
                return new Result(ResultCode.INVALIDID);
            }
            boolean handle = false;
            for(Box boxItem : boxes){
                if(boxItem.getCartonStatus().equals("1") || boxItem.getCartonStatus().equals("0")){
                    boxItem.setCartonStatus("2");
                    boxItem.setUpdateBy("C8777");
                    boxItem.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    boxMapper.updateStatus(boxItem);
                }
            }
            if(handle){
                return new Result(ResultCode.SUCCESS,boxes);
            }else{
                return new Result(ResultCode.INCONFORMITY,boxes);
            }

        }
        if(box.getCartonStatus().equals("1") || box.getCartonStatus().equals("0")){
            box.setCartonStatus("2");
            box.setUpdateBy("C8777");
            box.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
            boxMapper.updateStatus(box);
            return new Result(ResultCode.SUCCESS,box);
        }
        return new Result(ResultCode.INCONFORMITY,box);
    }


    @Override
    public Result receive(Box box) {
        //查询当前carton 是否是Carton No/ Pallet No
        Box judgeBox = boxMapper.findById(box.getCartonNo());
        List<String> unhandleBox = new ArrayList<>();
        if(judgeBox == null){
            List<Box> boxes = boxMapper.findBoxsByPalletNo(box.getCartonNo());
            if(boxes == null || boxes.size() < 1){
                return new Result(ResultCode.INVALIDID);
            }
            boolean handle = false;
            for(Box boxItem : boxes){
                if(boxItem.getCartonStatus().equals("1") || boxItem.getCartonStatus().equals("0")){
                    handle = true;
                    boxItem.setCartonStatus("2");
                    boxItem.setUpdateBy(box.getUpdateBy());
                    boxItem.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    boxMapper.updateStatus(boxItem);
                }else {
                    switch (boxItem.getCartonStatus()){
                        case "2":
                            unhandleBox.add(boxItem.getCartonNo() + "已入庫;");
                            break;
                        case "3":
                            unhandleBox.add(boxItem.getCartonNo() + "已出庫;");
                            break;
                    }
                }
            }
            if(handle){
                Pallet pallet = new Pallet();
                pallet.setPalletNo(box.getCartonNo());
                pallet.setVehicleNo(box.getVehicleNo());
                pallet.setUpdateBy(box.getUpdateBy());
                pallet.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                palletMapper.update(pallet);
                return new Result(ResultCode.SUCCESS,boxes);
            }else{
                return new Result(10006,unhandleBox.toString(),false);
            }

        }else{
            if(judgeBox.getCartonStatus().equals("1") || judgeBox.getCartonStatus().equals("0")){
                judgeBox.setCartonStatus("2");
                judgeBox.setUpdateBy(box.getUpdateBy());
                judgeBox.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                boxMapper.update(judgeBox);
                return new Result(ResultCode.SUCCESS,box);
            }else{
                switch (judgeBox.getCartonStatus()){
                    case "2":
                        unhandleBox.add(judgeBox.getCartonNo() + "已入庫;");
                        break;
                    case "3":
                        unhandleBox.add(judgeBox.getCartonNo() + "已出庫;");
                        break;
                }
                return new Result(10006,unhandleBox.toString(),false);
            }

        }
    }

    @Override
    public Result shipping(String id) {
        //查询当前carton 是否Carton No/ Pallet No
        Box box = boxMapper.findById(id);
        if(box == null){
            List<Box> boxes = boxMapper.findBoxsByPalletNo(id);
            if(boxes == null || boxes.size() < 1){
                return new Result(ResultCode.INVALIDID);
            }
            boolean handle = false;
            for(Box boxItem : boxes){
                if(boxItem.getCartonStatus().equals("2")){
                    handle = true;
                    boxItem.setCartonStatus("3");
                    boxItem.setUpdateBy("C8777");
                    boxItem.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    boxMapper.updateStatus(boxItem);
                }
            }
            if(handle){
                return new Result(ResultCode.SUCCESS,boxes);
            }else{
                return new Result(ResultCode.INCONFORMITY,boxes);
            }

        }
        if(box.getCartonStatus().equals("2")){
            box.setCartonStatus("3");
            box.setUpdateBy("C8777");
            box.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
            boxMapper.updateStatus(box);
            return new Result(ResultCode.SUCCESS,box);
        }
        return new Result(ResultCode.INCONFORMITY,box);
    }

    @Override
    public Result shipping(Box box) {
        //查询当前carton 是否是Carton No/ Pallet No
        Box judgeBox = boxMapper.findById(box.getCartonNo());
        List<String> unhandleBox = new ArrayList<>();
        if(judgeBox == null){
            List<Box> boxes = boxMapper.findBoxsByPalletNo(box.getCartonNo());
            if(boxes == null || boxes.size() < 1){
                return new Result(ResultCode.INVALIDID);
            }else{
                boolean handle = false;
                for(Box boxItem : boxes){
                    if(boxItem.getCartonStatus().equals("2")||boxItem.getCartonStatus().equals("1")){
                        handle = true;
                        boxItem.setCartonStatus("3");
                        boxItem.setVehicleNo(box.getVehicleNo());                   //設置車牌號
                        boxItem.setUpdateBy(box.getUpdateBy());
                        boxItem.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                        boxMapper.updateStatus(boxItem);
                    }else{
                        switch (boxItem.getCartonStatus()){
                            case "0":
                                unhandleBox.add(boxItem.getCartonNo() + "已裝箱，不能直接出貨;");
                                break;
                            case "3":
                                unhandleBox.add(boxItem.getCartonNo() + "已出庫；");
                                break;
                        }
                    }
                }
                if(handle){
                    Pallet pallet = new Pallet();
                    pallet.setPalletNo(box.getCartonNo());
                    pallet.setVehicleNo(box.getVehicleNo());
                    pallet.setUpdateBy(box.getUpdateBy());
                    pallet.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    palletMapper.update(pallet);
                    return new Result(ResultCode.SUCCESS,boxes);
                }else{
                    return new Result(10006,unhandleBox.toString(),false);
                }
            }

        }else{
            if(judgeBox.getCartonStatus().equals("2") || judgeBox.getCartonStatus().equals("1")){
                judgeBox.setCartonStatus("3");
                judgeBox.setVehicleNo(box.getVehicleNo());                      //設置車牌號
                judgeBox.setUpdateBy(box.getUpdateBy());
                judgeBox.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                boxMapper.update(judgeBox);
                return new Result(ResultCode.SUCCESS,box);
            }else {
                switch (judgeBox.getCartonStatus()){
                    case "0":
                        unhandleBox.add(judgeBox.getCartonNo() + "已裝箱，不能直接出貨;");
                        break;
                    case "3":
                        unhandleBox.add(judgeBox.getCartonNo() + "已出庫；");
                        break;
                }
                return new Result(10006,unhandleBox.toString(),false);
            }
        }
    }



    @Override
    public List<Box> findAllReceive() {
        List<Box> boxes = boxMapper.findBoxsByStatus(BoxStatus.RECEIVE.getValue());
        return boxes;
    }

    @Override
    public List<Box> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Box> boxes = boxMapper.findBoxsByStatus(null);
        return boxes;
    }

    @Override
    public List<Box> findAllShipping() {
        List<Box> boxes = boxMapper.findBoxsByStatus(BoxStatus.SHIPPING.getValue());
        return boxes;
    }

    @Override
    public Result findReiveById(String id) {
        List<Box> boxes = new ArrayList<Box>();
        Box box = boxMapper.findById(id);
        if(box == null){
            boxes =boxMapper.findBoxsByPalletNo(id);
            return new Result(ResultCode.SUCCESS,boxes);
        }
        boxes.add(box);
        return new Result(ResultCode.SUCCESS,boxes);
    }

    public List<Box> findAll() {
        List<Box> boxes = boxMapper.findBoxsByStatus(null);
        return boxes;
    }

    @Override
    public Integer findQtysByStatus(String status) {
        return boxMapper.findQtysByStatus(status);
    }

    @Override
    public List<InspurPallet> findAllInspurPallet(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return boxMapper.findAllInspurPallet();
    }

    @Override
    public Box findDetailById(String uuid) {
        return boxMapper.findDetailById(uuid);
    }


    @Override
    public Box findById(String id) {
        return boxMapper.findById(id);
    }

    @Override
    public List<Box> queryReportByCond(DeliveryQueryCond deliveryQueryCond) {
        return boxMapper.queryReportByCond(deliveryQueryCond);
    }

    @Override
    public List<ProductsInfoResult> yesterdayShippedBoxes() {
        return boxMapper.yesterdayShippedBoxes();
    }


    @Override
    public List<ProductsInfoResult> delayShippedBoxes() {
        return boxMapper.delayShippedBoxes();
    }

    @Override
    public List<MonitorInfo> queryMonitorInfos(String prodLineId) {
        return boxMapper.queryMonitorInfos(prodLineId);
    }

    @Override
    public List<MonitorInfo> queryUncompletedInfos(String prodLineId) {
        return boxMapper.queryUncompletedInfos(prodLineId);
    }

    @Override
    public Result findInspurInfo(InspurQueryCond inspurQueryCond) {
        List<InspurCT> inspurInfo = boxMapper.findInspurInfo(inspurQueryCond);
        return new Result(ResultCode.SUCCESS,inspurInfo);
    }


    @Override
    public Result currentPallet() {
        return new Result(ResultCode.SUCCESS,boxMapper.currentPallet());
    }

    @Override
    public Result disconnectBox(String barcode, String opUser) {
        //1.判断当前barcode 是否为箱号标签 ,若箱号标签为空，则判断箱号标签无效.
        Box box = boxMapper.findById(barcode);
        if(box == null){
            return new Result(ResultCode.INVALIDID);
        }else{
            if(BoxStatus.DETROYED.getValue().equals(box.getCartonStatus())){
                return new Result(ResultCode.STATUSREPEAT);
            }
            box.setCartonStatus(BoxStatus.DETROYED.getValue());
            box.setUpdateBy(opUser);
            box.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
            box.setDelFlag("1");
            boxMapper.deleteBox(box);                           //删除箱号信息
            boxMapper.deleteBoxRelCts(box);                     //删除箱号关联的底座信息
            BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(box.getCartonNo(),BoxStatus.DETROYED.getValue(),opUser, BarcodeType.BOX);
            barcodeHistoryMapper.insert(barcodeHistory);
            return new Result(ResultCode.SUCCESS);
        }
    }
}
