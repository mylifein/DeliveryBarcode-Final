package com.chenbro.deliverybarcode.web.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName TestPOI
 * @Description TODO
 * @Author c8777
 * @Date 2020/3/18 23:08
 * @Version 1.0
 **/
public class TestPOI {

    public static void main(String[] args) throws IOException {
        //1.創建工作薄  HSSFWorkBook  2003  XSSFWorkbook 2007
        Workbook workbook = new XSSFWorkbook();
        //2.创建表单sheet
        Sheet test = workbook.createSheet("test");
        //创建行对象   参数：行索引 从0开始
        Row row = test.createRow(0);
        //创建单元格对象 参数：索引  从0开始
        Cell cell = row.createCell(0);
        //向单元格中写入内容
        cell.setCellValue("CHENBRO");
        //创建样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.DASHED);
        //创建字体对象
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        cellStyle.setFont(font);

        //3.文件流

        FileOutputStream fop = new FileOutputStream("D:\\ChenBroDocuments\\ChenBro\\poi\\test1.xlsx");


        //4.写入文件
        workbook.write(fop);
        fop.close();

    }
}
