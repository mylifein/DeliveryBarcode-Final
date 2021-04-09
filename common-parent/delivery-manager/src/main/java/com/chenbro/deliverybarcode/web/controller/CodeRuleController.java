package com.chenbro.deliverybarcode.web.controller;

import com.chenbro.deliverybarcode.model.CodeRule;
import com.chenbro.deliverybarcode.model.HubUser;
import com.chenbro.deliverybarcode.model.MandFieldCite;
import com.chenbro.deliverybarcode.model.RuleCite;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.service.ICodeRuleService;
import com.chenbro.deliverybarcode.service.IRuleCiteService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName CodeRuleController
 * @Description TODO
 * @Author c8777
 * @Date 2020/4/5 13:23
 * @Version 1.0
 **/
@Controller
@RequestMapping("rule")
public class CodeRuleController {

    @Autowired
    private ICodeRuleService codeRuleService;

    @Autowired
    private IRuleCiteService ruleCiteService;


    @RequestMapping("queryCodeRule")
    public String queryCodeRule(Model model, @RequestParam(name = "page",required =true,defaultValue = "1")Integer pageNum, @RequestParam(name ="size",required = true,defaultValue = "10") Integer pageSize){
        List<CodeRule> codeRules = codeRuleService.findAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(codeRules);
        model.addAttribute("pageInfo",pageInfo);
        return "print/queryCodeRule";
    }


    @RequestMapping("queryCodeRuleDetail")
    @ResponseBody
    public Result queryCodeRuleDetail(String uuid){
        CodeRule codeRuleDetail = codeRuleService.findDetailByUuid(uuid);
        return  new Result(ResultCode.SUCCESS,codeRuleDetail);
    }

    @RequestMapping("updateCodeRule")
    @ResponseBody
    public Result updateCodeRule(@RequestBody CodeRule codeRule){
        HubUser opUser = (HubUser) SecurityUtils.getSubject().getPrincipal();
        codeRule.setUpdateBy(opUser.getUsername());
        codeRuleService.update(codeRule);
        return new Result(ResultCode.SUCCESS);
    }


    @RequestMapping("queryRuleDetailByNo")
    @ResponseBody
    public Result queryCodeRuleDetailByNo(String ruleNo){
        CodeRule codeRuleDetail = codeRuleService.findDetailByRuleNo(ruleNo);
        return  new Result(ResultCode.SUCCESS,codeRuleDetail);
    }


    @RequestMapping("queryRuleCite")
    public String queryRuleCite(Model model, @RequestParam(name = "page",required =true,defaultValue = "1")Integer pageNum, @RequestParam(name ="size",required = true,defaultValue = "10") Integer pageSize,@RequestParam(name = "delMatno",required = false)String delMatno){
        List<RuleCite> ruleCites = ruleCiteService.findAll(pageNum, pageSize,delMatno);
        PageInfo pageInfo = new PageInfo(ruleCites);
        model.addAttribute("pageInfo",pageInfo);
        return "rule/queryCite";
    }


    @RequestMapping("queryCiteById")
    @ResponseBody
    public Result queryCiteById(String uuid){
        RuleCite ruleCite = ruleCiteService.findByUUid(uuid);
        return new Result(ResultCode.SUCCESS,ruleCite);
    }


    @RequestMapping("updateRuleCite")
    @ResponseBody
    public Result updateRuleCite(RuleCite ruleCite){
        ruleCiteService.update(ruleCite);
        return new Result(ResultCode.SUCCESS);
    }
}
