package com.chenbro.deliverybarcode.utils;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

/**
 * @ClassName ToolUtils
 * @Description TODO
 * @Author c8777
 * @Date 2019/12/30 14:06
 * @Version 1.0
 **/
public class UuidUtils {

    /**
    * @Description //获得UUID
    * @Date 2019/12/30 14:08
    * @return java.lang.String
    **/
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


    // 根据传入得 时间 返回浪潮年月日
    public static String getInspurCode(Calendar now){
        // 根据当前的时间 获取年份 后两位 以及月份 并转化为 浪潮要求的年月日
        if(!ObjectUtils.isEmpty(now)){
            Integer year = Integer.parseInt(new SimpleDateFormat("yy", Locale.CHINESE).format(now.getTime()));
            Integer month = now.get(Calendar.MONTH) + 1;
            Integer currentDay = now.get(Calendar.DAY_OF_MONTH);
            String day = currentDay < 10 ? ("0" + currentDay): currentDay.toString();
            String queryBatch = (String) ConstantsUtil.INTTOCODEINSPUR.get(year) + (String) ConstantsUtil.INTTOCODEINSPUR.get(month) + day + "Q";
            return queryBatch;
        }else{
            return "";
        }
    }
}
