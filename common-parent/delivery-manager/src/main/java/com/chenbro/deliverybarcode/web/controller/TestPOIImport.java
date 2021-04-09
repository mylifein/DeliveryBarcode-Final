package com.chenbro.deliverybarcode.web.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * @ClassName TestPOIImport
 * @Description TODO
 * @Author c8777
 * @Date 2020/3/18 23:49
 * @Version 1.0
 **/
public class TestPOIImport {

    public static void main(String[] args) throws IOException {
        //1.根据excel文件创建工作薄
        Workbook workbook = new XSSFWorkbook("D:\\ChenBroDocuments\\ChenBro\\poi\\test1.xlsx");
        //2.获取sheet
        Sheet sheetAt = workbook.getSheetAt(0);  //参数索引

        //3.获取sheet 中的每一行，和每一个单元格
        for(int rowNum = 0; rowNum < sheetAt.getLastRowNum(); rowNum++){
            Row row = sheetAt.getRow(rowNum);  //根据所以获取每一行
            for(int cellNum = 0 ;cellNum < row.getLastCellNum(); cellNum++){
                //根据所以获取每个单元格
                Cell cell = row.getCell(cellNum);
                //获取每一个单元格内容
                Object cellValue = getCellValue(cell);
            }

        }
    }


    public static Object getCellValue(Cell cell){
        //1.获取单元格的属性类型
        CellType cellType = cell.getCellType();
        //2.根据单元格数据类型获取数据
        Object value = null;
        switch (cellType){
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case NUMERIC:
                //日期格式判断
                if(DateUtil.isCellDateFormatted(cell)){
                    //日期格式
                    value = cell.getDateCellValue();
                }else {
                    //数字
                    value = cell.getNumericCellValue();
                }
            case FORMULA: //公式
                value = cell.getCellFormula();
                break;
            default:
                break;
        }
        return value;
    }
}
