package com.chenbro.deliverybarcode.web.job;

import com.chenbro.deliverybarcode.model.Box;
import com.chenbro.deliverybarcode.model.response.PalletInfoResult;
import com.chenbro.deliverybarcode.model.response.ProductsInfoResult;
import com.chenbro.deliverybarcode.service.IBoxService;
import com.chenbro.deliverybarcode.service.IPalletService;
import com.chenbro.deliverybarcode.utils.MailUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Controller;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName InformJob
 * @Description TODO  定时任务通知
 * @Author z8777
 * @Date 2020/7/23 22:51
 * @Version 1.0
 **/
public class InformJob implements Job {

//    private String message;
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

    @Autowired
    private IBoxService boxService;

    @Autowired
    private IPalletService palletService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //1.获取报表数据
        List<ProductsInfoResult> productsInfoResults = boxService.yesterdayShippedBoxes();
        //2.构建workbook 并构造sheet
        Workbook workbook = new XSSFWorkbook();
        // 獲取出貨時間
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");                 //设置日期格式
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,-1);
        String deliveryDate = sdf.format(instance.getTime());
        // Sheet-1 出貨信息
        Sheet sheet = workbook.createSheet();
        workbook.setSheetName(0,"出貨清單-" + deliveryDate);
        //创建行
        //标题
        String[] titles = "部门,线别,出货料号,箱数,总数量".split(",");
        //处理标题
        Row row = sheet.createRow(0);

        int titleIndex = 0;
        for(String title : titles){
            Cell cell = row.createCell(titleIndex++);
            cell.setCellValue(title);
        }
        int rowIndex = 1;
        Cell cell = null;
        for(ProductsInfoResult productsInfoResult : productsInfoResults){
            row = sheet.createRow(rowIndex++);
            //箱号
            cell = row.createCell(0);
            cell.setCellValue(productsInfoResult.getDeptName());
            //数量
            cell = row.createCell(1);
            cell.setCellValue(productsInfoResult.getProdlineDesc());
            //生产线别
            cell = row.createCell(2);
            cell.setCellValue(productsInfoResult.getDelMatno());
            //工单号
            cell = row.createCell(3);
            cell.setCellValue(productsInfoResult.getBoxesQty());
            //客户编号
            cell = row.createCell(4);
            cell.setCellValue(productsInfoResult.getTotalQty());
        }
        for (int i = 0; i < titles.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Sheet-2  已更新的棧板信息
        //1. 獲取更新的棧板信息
        List<PalletInfoResult> palletInfoResults = palletService.yesterdayPalletUpdate();
        Sheet palletSheet = workbook.createSheet();
        workbook.setSheetName(1,"VMI更新棧板信息-" + deliveryDate);
        //创建行
        //标题
        String[] palletTitles = "棧板號,數量,客戶名稱,客戶料號,出貨料號,銷貨訂單號,狀態".split(",");
        //处理标题
        row = palletSheet.createRow(0);

        titleIndex = 0;
        for(String title : palletTitles){
            cell = row.createCell(titleIndex++);
            cell.setCellValue(title);
        }
        rowIndex = 1;
        cell = null;
        for(PalletInfoResult palletInfoResult : palletInfoResults){
            row = palletSheet.createRow(rowIndex++);
            //棧板號
            cell = row.createCell(0);
            cell.setCellValue(palletInfoResult.getPalletNo());
            //棧板數量
            cell = row.createCell(1);
            cell.setCellValue(palletInfoResult.getPalletQty());
            //客戶名稱
            cell = row.createCell(2);
            cell.setCellValue(palletInfoResult.getCusName());
            //客戶料號
            cell = row.createCell(3);
            cell.setCellValue(palletInfoResult.getCusMatno());
            //出貨料號
            cell = row.createCell(4);
            cell.setCellValue(palletInfoResult.getDelMatno());
            //銷貨訂單號
            cell = row.createCell(5);
            cell.setCellValue(palletInfoResult.getSoOrder());
            //狀態值
            cell = row.createCell(6);
            cell.setCellValue(palletInfoResult.getStatus());
        }
        for (int i = 0; i < titles.length; i++) {
            palletSheet.autoSizeColumn(i);
        }
        // Sheet-3  生产未出货明细
        //1. 獲取更新的棧板信息
/*        List<ProductsInfoResult> delayProfucts= boxService.delayShippedBoxes();
        // Sheet-2 出貨信息
        Sheet sheet3 = workbook.createSheet();
        workbook.setSheetName(2,"未出货产品汇总12-01——" + deliveryDate);
        //创建行
        //标题
        String[] delayTitles = "部门,线别,出货料号,箱数,总数量".split(",");
        //处理标题
        row = sheet3.createRow(0);

        titleIndex = 0;
        for(String title : delayTitles){
            cell = row.createCell(titleIndex++);
            cell.setCellValue(title);
        }
        rowIndex = 1;
        cell = null;
        for(ProductsInfoResult productsInfoResult : delayProfucts){
            row = sheet3.createRow(rowIndex++);
            //箱号
            cell = row.createCell(0);
            cell.setCellValue(productsInfoResult.getDeptName());
            //数量
            cell = row.createCell(1);
            cell.setCellValue(productsInfoResult.getProdlineDesc());
            //生产线别
            cell = row.createCell(2);
            cell.setCellValue(productsInfoResult.getDelMatno());
            //工单号
            cell = row.createCell(3);
            cell.setCellValue(productsInfoResult.getBoxesQty());
            //客户编号
            cell = row.createCell(4);
            cell.setCellValue(productsInfoResult.getTotalQty());
        }
        for (int i = 0; i < titles.length; i++) {
            sheet3.autoSizeColumn(i);
        }*/
        //完成下载
        ByteArrayOutputStream baos = new ByteArrayOutputStream(2000);
        try {
            workbook.write(baos);
            workbook.close();
            DataSource ds = new ByteArrayDataSource(baos.toByteArray(),"application/excel");
            String[] mailsTo = {"stonechen@chenbro.com","lindajiang@chenbro.com","adaai@chenbro.com","txpmc8@chenbro.com","t534008@chenbro.com.tx","t534003@chenbro.com.tx","Bravemo@chenbro.com","markliu@chenbro.com"};
            String[] mailsCC = {"lavinetseng@chenbro.com","kervinluo@chenbro.com"};
            MailUtils.sendAttachmentMail(mailsTo,mailsCC,"出货通知单——" +sdf.format(new Date()),deliveryDate + " :各线别出货信息汇总 和VMI回傳更新的棧板信息，请查看附件！",ds,"shippingList.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(baos != null){
                try {
                    baos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
