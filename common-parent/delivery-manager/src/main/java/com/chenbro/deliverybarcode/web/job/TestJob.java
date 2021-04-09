package com.chenbro.deliverybarcode.web.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * @ClassName TestJob
 * @Description TODO
 * @Author z8777
 * @Date 2020/7/27 15:14
 * @Version 1.0
 **/
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行了Job" + new Date());
        List<Integer> arrInt = new ArrayList<Integer>();
    }
}
