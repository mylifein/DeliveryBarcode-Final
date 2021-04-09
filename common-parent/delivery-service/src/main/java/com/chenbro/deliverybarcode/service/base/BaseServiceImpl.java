package com.chenbro.deliverybarcode.service.base;

import com.chenbro.deliverybarcode.mapper.*;
import com.chenbro.deliverybarcode.model.HubEmployee;
import com.chenbro.deliverybarcode.model.HubPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BaseServiceImpl
 * @Description TODO
 * @Author c8777
 * @Date 2020/2/11 14:16
 * @Version 1.0
 **/
public abstract class BaseServiceImpl<T> implements IBaseService<T>{

    //统一管理Dao

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected BoxMapper boxMapper;

    @Autowired
    protected PalletMapper palletMapper;

    @Autowired
    protected HubDepartmentMapper hubDepartmentMapper;

    @Autowired
    protected HubUserMapper hubUserMapper;

    @Autowired
    protected HubEmployeeMapper hubEmployeeMapper;

    @Autowired
    protected HubPermissionMapper hubPermissionMapper;

    @Autowired
    protected HubPermissionPointMapper hubPermissionPointMapper;

    @Autowired
    protected HubPermissionApiMapper hubPermissionApiMapper;

    @Autowired
    protected HubPermissionMenuMapper hubPermissionMenuMapper;

    @Autowired
    protected HubRoleMapper hubRoleMapper;

    @Autowired
    protected HubResourcesMapper hubResourcesMapper;

    @Autowired
    protected CtcodeMapper ctcodeMapper;

    @Autowired
    protected PrintModelMapper printModelMapper;

    @Autowired
    protected MandatoryFieldMapper mandatoryFieldMapper;

    @Autowired
    protected ModelCiteMapper modelCiteMapper;

    @Autowired
    protected FieldTypeMapper fieldTypeMapper;

    @Autowired
    protected CodeRuleMapper codeRuleMapper;

    @Autowired
    protected CapacityMapper capacityMapper;

    @Autowired
    protected CapacityCiteMapper capacityCiteMapper;

    @Autowired
    protected RuleCiteMapper ruleCiteMapper;

    @Autowired
    protected MandFieldCiteMapper mandFieldCiteMapper;

    @Autowired
    protected PrintDepartmentMapper printDepartmentMapper;

    @Autowired
    protected WoBatchMapper woBatchMapper;

    @Autowired
    protected CheckWorkOrderMapper checkWorkOrderMapper;

    @Autowired
    protected SysLogMapper sysLogMapper;

    @Autowired
    protected  BarcodeHistoryMapper barcodeHistoryMapper;

    @Autowired
    protected VMIStockMapper vmiStockMapper;

}
