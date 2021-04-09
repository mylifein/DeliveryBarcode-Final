package com.chenbro.deliverybarcode.service.impl;

import com.chenbro.deliverybarcode.model.CodeRule;
import com.chenbro.deliverybarcode.model.CommonException;
import com.chenbro.deliverybarcode.model.RuleContent;
import com.chenbro.deliverybarcode.service.ICodeRuleService;
import com.chenbro.deliverybarcode.service.base.BaseServiceImpl;
import com.chenbro.deliverybarcode.utils.DateUtils;
import com.chenbro.deliverybarcode.utils.UuidUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CodeRuleServiceImpl
 * @Description TODO
 * @Author c8777
 * @Date 2020/3/27 15:31
 * @Version 1.0
 **/
@Controller
@Transactional
public class CodeRuleServiceImpl extends BaseServiceImpl<CodeRule> implements ICodeRuleService {


    @Override
    public List<CodeRule> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return codeRuleMapper.findAll();
    }

    @Override
    public CodeRule findDetailByUuid(String uuid) {
        return codeRuleMapper.findDetailByUuid(uuid);
    }

    @Override
    public CodeRule findByUUid(String uuid) {
        return null;
    }

    @Override
    public void deleteById(String uuid) throws CommonException {

    }

    @Override
    public void update(CodeRule codeRule) {
        //1. 根据uuid 查询CodeRule 信息
        CodeRule detail = codeRuleMapper.findDetailByUuid(codeRule.getUuid());
        //2. 更新 RuleContent 信息
        List<String> existRules = new ArrayList<>();
        for (RuleContent ruleItem : codeRule.getRuleContents()) {
            // 判断uuid 是否为空， 若为空 新增规则条目信息
            if(ObjectUtils.isEmpty(ruleItem.getUuid())){
                ruleItem.setUuid(UuidUtils.getUUID());
                ruleItem.setRuleNo(codeRule.getRuleNo());
                ruleItem.setCreateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                ruleItem.setCreateBy(codeRule.getUpdateBy());
                codeRuleMapper.insertRuleContent(ruleItem);
            }else{
                //更新条目信息
                ruleItem.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                ruleItem.setUpdateBy(codeRule.getUpdateBy());
                codeRuleMapper.updateRuleContent(ruleItem);
                existRules.add(ruleItem.getUuid());
            }
        }
        for (RuleContent rulecontent:detail.getRuleContents()) {
            // 判断规则 条目是否需要删除
            if(!existRules.contains(rulecontent.getUuid())){
                rulecontent.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
                rulecontent.setUpdateBy(codeRule.getUpdateBy());
                rulecontent.setDelFlag("1");
                codeRuleMapper.deleteRuleContent(rulecontent);
            }
        }
        codeRule.setUpdateDate(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
        codeRuleMapper.update(codeRule);
    }

    @Override
    public void insert(CodeRule codeRule) {

    }

    @Override
    public CodeRule findDetailByRuleNo(String ruleNo) {
        return codeRuleMapper.findDetailByRuleNo(ruleNo);
    }
}
