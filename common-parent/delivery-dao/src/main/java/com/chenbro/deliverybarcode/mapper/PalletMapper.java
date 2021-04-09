package com.chenbro.deliverybarcode.mapper;

import com.chenbro.deliverybarcode.mapper.base.BaseMapper;
import com.chenbro.deliverybarcode.model.Box;
import com.chenbro.deliverybarcode.model.Pallet;
import com.chenbro.deliverybarcode.model.PalletQueryCond;
import com.chenbro.deliverybarcode.model.response.PalletInfoResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BoxMapper
 * @Description TODO
 * @Author c8777
 * @Date 2020/2/14 10:34
 * @Version 1.0
 **/
public interface PalletMapper extends BaseMapper<Pallet> {


    public Pallet findById(String id);

    List<Pallet> findAll(@Param("palletNo") String palletNo);

    Pallet findDetailById(@Param("uuid")String uuid);

    List<Pallet> queryPalletReportByCond(PalletQueryCond palletQueryCond);

    List<PalletInfoResult> yesterdayPalletUpdate();


    List<Pallet> queryCurrentPallets(@Param("currentDate") String currentDate);

    /**
    * @Description //TODO  删除装箱单和栈板号的关联
    * @Date 2020/11/6 11:28
    * @return void
    **/
    void deletePalletRelCartons(Pallet pallet);

    /**
    * @Description //TODO   删除栈板号
    * @Date 2020/11/6 11:35
    * @return void
    **/
    void deletePallet(Pallet pallet);

}
