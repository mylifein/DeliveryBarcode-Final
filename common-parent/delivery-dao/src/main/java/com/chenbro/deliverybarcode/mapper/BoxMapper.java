package com.chenbro.deliverybarcode.mapper;

import com.chenbro.deliverybarcode.mapper.base.BaseMapper;
import com.chenbro.deliverybarcode.model.*;
import com.chenbro.deliverybarcode.model.response.InspurCT;
import com.chenbro.deliverybarcode.model.response.MonitorInfo;
import com.chenbro.deliverybarcode.model.response.ProductsInfoResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BoxMapper
 * @Description TODO
 * @Author c8777
 * @Date 2020/2/14 10:34
 * @Version 1.0
 **/
public interface BoxMapper extends BaseMapper<Box> {

    void updateStatus(Box box);

    List<Box> findBoxsByPalletNo(String palletNo);

    List<Box> findBoxsByStatus(String status);

    Box findById(String id);

    Integer findQtysByStatus(String status);

    List<InspurPallet> findAllInspurPallet();

    Box findDetailById(@Param("uuid") String uuid);

    List<Box> queryReportByCond(DeliveryQueryCond deliveryQueryCond);

    List<ProductsInfoResult> yesterdayShippedBoxes();



    List<MonitorInfo> queryMonitorInfos(@Param("prodLineId") String prodLineId);

    List<MonitorInfo> queryUncompletedInfos(@Param("prodLineId")String prodLineId);

    List<InspurCT> findInspurInfo(InspurQueryCond inspurQueryCond);


    List<ProductsInfoResult> currentPallet();

    /**
     * @Description //TODO  删除装箱单和栈板号的关联
     * @Date 2020/11/6 11:28
     * @return void
     **/
    void deleteBoxRelCts(Box box);

    /**
     * @Description //TODO   删除箱号
     * @Date 2020/11/6 11:35
     * @return void
     **/
    void deleteBox(Box box);

    List<ProductsInfoResult> delayShippedBoxes();
}
