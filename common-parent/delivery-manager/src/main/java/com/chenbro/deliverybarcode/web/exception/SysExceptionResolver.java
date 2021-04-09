package com.chenbro.deliverybarcode.web.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SysExceptionResolver
 * @Description TODO  异常处理器
 * @Author z8777
 * @Date 2020/10/23 14:02
 * @Version 1.0
 **/
public class SysExceptionResolver implements HandlerExceptionResolver {


    /**
    * @Description //TODO 处理异常的业务逻辑
    * @Date 2020/10/23 14:03
    * @return org.springframework.web.servlet.ModelAndView
    **/
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //1.获取异常对象
        SysException sysException = null;
        if(sysException instanceof SysException){
            sysException = (SysException)e;
        }else{
            sysException = new SysException("系统正在维护...");
        }
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg",e.getMessage());
        mv.setViewName("errror");
        return mv;
    }
}
