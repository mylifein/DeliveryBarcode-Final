package com.chenbro.deliverybarcode.mapper;

import com.chenbro.deliverybarcode.mapper.base.BaseMapper;
import com.chenbro.deliverybarcode.model.CodeRule;
import com.chenbro.deliverybarcode.model.RuleContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BoxMapper
 * @Description TODO
 * @Author c8777
 * @Date 2020/2/14 10:34
 * @Version 1.0
 **/
public interface CodeRuleMapper extends BaseMapper<CodeRule> {


    List<CodeRule> findAll();


    CodeRule findDetailByUuid(@Param("uuid") String uuid);

    CodeRule findDetailByRuleNo(@Param("ruleNo") String ruleNo);


    /**
    * @Description //TODO 删除 规则信息
    * @Date 2020/8/14 14:50
    * @return void
    **/
    void deleteRuleContent(RuleContent ruleContent);


    /**
    * @Description //TODO  更新 规则信息
    * @Date 2020/8/14 14:51
    * @return void
    **/
    void updateRuleContent(RuleContent ruleContent);

    /**
    * @Description //TODO 新增 规则信息
    * @Date 2020/8/14 15:36
    * @return void
    **/
    void insertRuleContent(RuleContent ruleContent);


}
