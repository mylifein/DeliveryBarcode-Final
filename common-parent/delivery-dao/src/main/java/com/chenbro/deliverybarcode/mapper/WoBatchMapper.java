package com.chenbro.deliverybarcode.mapper;

import com.chenbro.deliverybarcode.mapper.base.BaseMapper;
import com.chenbro.deliverybarcode.model.FieldType;
import com.chenbro.deliverybarcode.model.WoBatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BoxMapper
 * @Description TODO
 * @Author c8777
 * @Date 2020/2/14 10:34
 * @Version 1.0
 **/
public interface WoBatchMapper extends BaseMapper<WoBatch> {


    List<WoBatch> findAll(@Param("woNo") String woNo);

    WoBatch findByBatchNo(@Param("batchNo") String batchNo);

    /**
    * @Description //TODO  根据工单号 查询 批次号信息
    * @Date 2020/12/10 16:31
    * @return com.chenbro.deliverybarcode.model.WoBatch
    **/
    WoBatch findByWorkNo(@Param("workNo") String workNo);

    /**
    * @Description //TODO  根据浪潮日期编码和厂商代码 查询已生成的批次
    * @Date 2020/12/11 8:23
    * @return java.util.List<java.lang.String>
    **/
    List<String> findCurrentBatchNos(@Param("dateCode") String dateCode);

    void addWoBatch(WoBatch woBatch);
}
