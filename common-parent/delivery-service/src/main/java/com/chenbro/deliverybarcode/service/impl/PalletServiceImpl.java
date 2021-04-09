package com.chenbro.deliverybarcode.service.impl;

import com.chenbro.deliverybarcode.model.BarcodeHistory;
import com.chenbro.deliverybarcode.model.Box;
import com.chenbro.deliverybarcode.model.Pallet;
import com.chenbro.deliverybarcode.model.PalletQueryCond;
import com.chenbro.deliverybarcode.model.base.BarcodeType;
import com.chenbro.deliverybarcode.model.base.BoxStatus;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.model.response.PalletInfoResult;
import com.chenbro.deliverybarcode.service.IPalletService;
import com.chenbro.deliverybarcode.service.base.BaseServiceImpl;
import com.chenbro.deliverybarcode.utils.BarcodeHistoryUtils;
import com.chenbro.deliverybarcode.utils.DateUtils;
import com.chenbro.deliverybarcode.utils.UuidUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName PalletServiceImpl
 * @Description TODO
 * @Author c8777
 * @Date 2020/2/19 11:54
 * @Version 1.0
 **/
@Service
@Transactional
public class PalletServiceImpl extends BaseServiceImpl<Pallet> implements IPalletService {

    @Override
    public Pallet findById(String id) {
        Pallet pallet = palletMapper.findById(id);
        if (pallet != null) {
            pallet.setBoxes(boxMapper.findBoxsByPalletNo(id));
        }
        return pallet;
    }

    @Override
    public List<Pallet> findAll(Integer pageNum, Integer pageSize, String palletNo) {
        PageHelper.startPage(pageNum, pageSize);
        return palletMapper.findAll(palletNo);
    }

    @Override
    public Pallet findByUUid(String uuid) {
        return null;
    }

    @Override
    public void deleteById(String uuid) {

    }

    @Override
    public void update(Pallet pallet) {
        pallet.setUpdateDate(DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        palletMapper.update(pallet);
    }

    @Override
    public void insert(Pallet pallet) {

    }



    @Override
    public Pallet findDetailById(String uuid) {
        return palletMapper.findDetailById(uuid);
    }

    @Override
    public List<Pallet> queryPalletReportByCond(PalletQueryCond palletQueryCond) {
        return palletMapper.queryPalletReportByCond(palletQueryCond);
    }

    @Override
    public Result queryPallets() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        return new Result(ResultCode.SUCCESS,palletMapper.queryCurrentPallets(format));
    }

    @Override
    public Result ngBarcode(String barcode, String opUser) {
        //1.判断当前barcode 是否为栈板标签 ,若栈板标签为空，则判断栈板标签无效.
        Pallet pallet = palletMapper.findById(barcode);
        if(pallet == null){
            return new Result(ResultCode.INVALIDID);
        }else{
            if(BoxStatus.NG.getValue().equals(pallet.getStatus())){
                return new Result(ResultCode.STATUSREPEAT);
            }
            List<Box> boxes = boxMapper.findBoxsByPalletNo(barcode);
            for(Box boxItem : boxes){
                boxItem.setCartonStatus(BoxStatus.NG.getValue());
                boxItem.setUpdateBy(opUser);
                boxItem.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                boxMapper.updateStatus(boxItem);            //更新裝箱單裝箱單
//                增加一條barcode 歷史記錄
                BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(boxItem.getCartonNo(),BoxStatus.NG.getValue(),opUser,BarcodeType.BOX);
                barcodeHistoryMapper.insert(barcodeHistory);
            }
            pallet.setStatus(BoxStatus.NG.getValue());
            pallet.setUpdateBy(opUser);
            pallet.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
            palletMapper.update(pallet);
            BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(pallet.getPalletNo(),BoxStatus.NG.getValue(),opUser, BarcodeType.PALLET);
            barcodeHistoryMapper.insert(barcodeHistory);
            return new Result(ResultCode.SUCCESS);
        }
    }

    @Override
    public Result disconnectPallet(String palletNo, String opUser) {
        //1.判断当前barcode 是否为栈板标签 ,若栈板标签为空，则判断栈板标签无效.
        Pallet pallet = palletMapper.findById(palletNo);
        if(pallet == null){
            return new Result(ResultCode.INVALIDID);
        }else{
            if(BoxStatus.DETROYED.getValue().equals(pallet.getStatus())){
                return new Result(ResultCode.STATUSREPEAT);
            }
            List<Box> boxes = boxMapper.findBoxsByPalletNo(palletNo);
            for(Box boxItem : boxes){
                boxItem.setCartonStatus(BoxStatus.PACK.getValue());
                boxItem.setUpdateBy(opUser);
                boxItem.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                boxMapper.updateStatus(boxItem);            //更新裝箱單裝箱單
//                增加一條barcode 歷史記錄
                BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(boxItem.getCartonNo(),BoxStatus.PACK.getValue(),opUser,BarcodeType.BOX);
                barcodeHistoryMapper.insert(barcodeHistory);
            }
            pallet.setStatus(BoxStatus.DETROYED.getValue());
            pallet.setUpdateBy(opUser);
            pallet.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
            pallet.setDelFlag("1");
            palletMapper.deletePallet(pallet);        //删除栈板信息
            palletMapper.deletePalletRelCartons(pallet);        //删除栈板关联的箱号信息
            BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(pallet.getPalletNo(),BoxStatus.DETROYED.getValue(),opUser, BarcodeType.PALLET);
            barcodeHistoryMapper.insert(barcodeHistory);
            return new Result(ResultCode.SUCCESS);
        }
    }

    @Override
    public Result passBarcode(String barcode, String username) {
        //1.判断当前barcode 是否为栈板标签 ,若栈板标签为空，则判断栈板标签无效.
        Pallet pallet = palletMapper.findById(barcode);
        if(pallet == null){
            return new Result(ResultCode.INVALIDID);
        }else{
            if(BoxStatus.NG.getValue().equals(pallet.getStatus())){
                return new Result(ResultCode.STATUSREPEAT);
            }
            List<Box> boxes = boxMapper.findBoxsByPalletNo(barcode);
            for(Box boxItem : boxes){
                boxItem.setCartonStatus(BoxStatus.NG.getValue());
                boxItem.setUpdateBy(username);
                boxItem.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                boxMapper.updateStatus(boxItem);            //更新裝箱單裝箱單
//                增加一條barcode 歷史記錄
                BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(boxItem.getCartonNo(),BoxStatus.NG.getValue(),username,BarcodeType.BOX);
                barcodeHistoryMapper.insert(barcodeHistory);
            }
            pallet.setStatus(BoxStatus.PASS.getValue());
            pallet.setUpdateBy(username);
            pallet.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
            palletMapper.update(pallet);
            BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(pallet.getPalletNo(),BoxStatus.PASS.getValue(),username, BarcodeType.PALLET);
            barcodeHistoryMapper.insert(barcodeHistory);
            return new Result(ResultCode.SUCCESS);
        }
    }

    @Override
    public Result shipping(String barcode, String vehicleNo, String username) {
        //1.判断当前barcode 是否为栈板标签 ,若栈板标签为空，则判断栈板标签无效.
        Pallet pallet = palletMapper.findById(barcode);
        if(pallet == null){
            Box box = boxMapper.findById(barcode);
            if(box != null){
                if(BoxStatus.PACK.getValue().equals(box.getCartonStatus())||BoxStatus.PALLET.getValue().equals(box.getCartonStatus()) || BoxStatus.RECEIVE.getValue().equals(box.getCartonStatus())){
                    box.setCartonStatus(BoxStatus.SHIPPING.getValue());
                    box.setVehicleNo(vehicleNo);
                    box.setUpdateBy(username);
                    box.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    boxMapper.updateStatus(box);            //更新裝箱單裝箱單
                    BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(box.getCartonNo(), BoxStatus.SHIPPING.getValue(), username, BarcodeType.BOX);
                    barcodeHistoryMapper.insert(barcodeHistory);
                    return new Result(ResultCode.SUCCESS);
                }else {
                    return new Result(ResultCode.INCONFORMITY);
                }
            }else{
                return new Result(ResultCode.INVALIDID);
            }
        }else{
            if(BoxStatus.NG.getValue().equals(pallet.getStatus())){
                return new Result(ResultCode.STATUSNG);
            }else if(BoxStatus.SHIPPING.getValue().equals(pallet.getStatus())){
                return new Result(ResultCode.STATUSSHIPPED);
            }else {
                List<Box> boxes = boxMapper.findBoxsByPalletNo(barcode);
                for(Box boxItem : boxes){
                    boxItem.setCartonStatus(BoxStatus.SHIPPING.getValue());
                    boxItem.setVehicleNo(vehicleNo);
                    boxItem.setUpdateBy(username);
                    boxItem.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    boxMapper.updateStatus(boxItem);            //更新裝箱單裝箱單
                    BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(boxItem.getCartonNo(),BoxStatus.SHIPPING.getValue(),username,BarcodeType.BOX);
                    barcodeHistoryMapper.insert(barcodeHistory);
                }
                pallet.setStatus(BoxStatus.SHIPPING.getValue());
                pallet.setVehicleNo(vehicleNo);
                pallet.setUpdateBy(username);
                pallet.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                palletMapper.update(pallet);
                BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(pallet.getPalletNo(),BoxStatus.SHIPPING.getValue(),username, BarcodeType.PALLET);
                barcodeHistoryMapper.insert(barcodeHistory);
                return new Result(ResultCode.SUCCESS);
            }
        }
    }

    @Override
    public List<PalletInfoResult> yesterdayPalletUpdate() {
        return palletMapper.yesterdayPalletUpdate();
    }

    @Override
    public Result replyUpdate(String barcode, String statusCode) {
        //barcode 統一為棧板號.  1.判斷當前棧板號是否有效
        Pallet pallet = palletMapper.findById(barcode);
        if (pallet == null) {
            return new Result(ResultCode.INVALIDID);
        } else {
            if (ObjectUtils.isEmpty(statusCode)) {
                return new Result(ResultCode.INVALIDIDSTATUS);
            }
            if (statusCode.equals(pallet.getStatus())) {
                return new Result(ResultCode.STATUSREPEAT);
            }
            List<Box> boxes = boxMapper.findBoxsByPalletNo(barcode);
            for (Box boxItem : boxes) {
                boxItem.setCartonStatus(statusCode);
                boxItem.setUpdateBy("VMI-User");
                boxItem.setUpdateDate(DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
                boxMapper.updateStatus(boxItem);            //更新裝箱單裝箱單

                //增加一條barcode 歷史記錄
//                BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(boxItem.getCartonNo(),statusCode,"VMI-User",BarcodeType.BOX);
//                barcodeHistoryMapper.insert(barcodeHistory);
            }
            pallet.setStatus(statusCode);
            pallet.setUpdateBy("VMI-User");
            pallet.setUpdateDate(DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
            palletMapper.update(pallet);
            BarcodeHistory barcodeHistory = BarcodeHistoryUtils.buildBarcodeHistory(pallet.getPalletNo(), statusCode, "VMI-User", BarcodeType.PALLET);
            barcodeHistoryMapper.insert(barcodeHistory);
            return new Result(ResultCode.SUCCESS);
        }



    }
}
