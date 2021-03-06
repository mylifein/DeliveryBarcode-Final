package com.chenbro.deliverybarcode.service.impl;

import com.chenbro.deliverybarcode.model.CommonException;
import com.chenbro.deliverybarcode.model.WoBatch;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.service.IWoBatchService;
import com.chenbro.deliverybarcode.service.base.BaseServiceImpl;
import com.chenbro.deliverybarcode.utils.ConstantsUtil;
import com.chenbro.deliverybarcode.utils.DateUtils;
import com.chenbro.deliverybarcode.utils.UuidUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName WoBatchServiceImpl
 * @Description TODO
 * @Author c8777
 * @Date 2020/5/6 16:59
 * @Version 1.0
 **/
@Service
@Transactional
public class WoBatchServiceImpl extends BaseServiceImpl<WoBatch> implements IWoBatchService {


    @Override
    public List<WoBatch> findAll(int pageNum, int pageSize, String woNo) {
        PageHelper.startPage(pageNum, pageSize);
        return woBatchMapper.findAll(woNo);
    }

    @Override
    public WoBatch findByUUid(String uuid) {
        return woBatchMapper.findByUUid(uuid);
    }

    @Override
    public void deleteById(String uuid) throws CommonException {

    }

    @Override
    public void update(WoBatch woBatch) {
        WoBatch byBatchNo = woBatchMapper.findByBatchNo(woBatch.getBatchNo());
        if (ObjectUtils.isEmpty(woBatch)) {
            woBatch.setUpdateDate(DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
            woBatchMapper.update(woBatch);
        }
    }

    @Override
    public Result updateBatchNo(WoBatch woBatch) {
        WoBatch byBatchNo = woBatchMapper.findByBatchNo(woBatch.getBatchNo().trim());
        if (ObjectUtils.isEmpty(byBatchNo)) {                   // ???????????????????????????????????????
            woBatch.setUpdateDate(DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
            woBatchMapper.update(woBatch);
            return new Result(ResultCode.SUCCESS);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    public Result distributeBatchNo(String uniqueNo, String productType, String opUser) {
        // ????????????????????????????????????????????????
        WoBatch byBatchNo = woBatchMapper.findByWorkNo(uniqueNo);

        if (ObjectUtils.isEmpty(byBatchNo)) {                   // ???????????????????????????????????????
            // ???????????????????????????????????????
            String realBathNo = this.getBatchByDateCode(Calendar.getInstance(), productType);
            // ???????????????
            WoBatch newWoBatch = new WoBatch();
            newWoBatch.setUuid(UuidUtils.getUUID());
            newWoBatch.setWorkNo(uniqueNo);
            newWoBatch.setBatchNo(realBathNo);
            newWoBatch.setCreateBy(opUser);
            newWoBatch.setCreateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
            woBatchMapper.addWoBatch(newWoBatch);
            return new Result(ResultCode.SUCCESS, realBathNo);
        } else {
            return new Result(ResultCode.FAILDISTRIBUTE, byBatchNo.getBatchNo());             // ?????????????????????????????????
        }
    }

    @Override
    public void insert(WoBatch woBatch) {

    }

    @Override
    public String getBatchByDateCode(Calendar dateCode, String productType) {
        String inspurCode = UuidUtils.getInspurCode(dateCode);
        String realBathNo = "";
        // ???????????????????????????
        List<String> currentBatchNos = woBatchMapper.findCurrentBatchNos("%" + inspurCode + "%");
        if(ObjectUtils.isEmpty(currentBatchNos)){       // ????????????????????????????????? ??????1??????
            realBathNo = productType + inspurCode + "1";
        }else{
            // ????????????????????????  ????????????????????????
            String firstBatch = currentBatchNos.get(0);
            int maxNo = (int) ConstantsUtil.CODETOINTINSPUR.get(firstBatch.substring(firstBatch.length() -1, firstBatch.length()));
            int currentNo = 0;
            String currentBatch = "";
            for (int i = 1; i < currentBatchNos.size(); i++){
                currentBatch = currentBatchNos.get(i);
                currentNo = (int) ConstantsUtil.CODETOINTINSPUR.get(currentBatch.substring(firstBatch.length() - 1, currentBatch.length()));
                if(currentNo > maxNo){
                    maxNo = currentNo;
                }
            }
            // ???????????????????????????????????? ??????Z
            if(maxNo >= 33){
                dateCode.add(dateCode.DATE,1);
                return getBatchByDateCode(dateCode,productType);
            }
            realBathNo = productType + inspurCode + ConstantsUtil.INTTOCODEINSPUR.get(maxNo + 1);
        }
        return realBathNo;
    }
}
