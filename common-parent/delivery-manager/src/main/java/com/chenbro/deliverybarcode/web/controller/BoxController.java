package com.chenbro.deliverybarcode.web.controller;

import com.chenbro.deliverybarcode.model.Box;
import com.chenbro.deliverybarcode.model.HubUser;
import com.chenbro.deliverybarcode.model.InspurPallet;
import com.chenbro.deliverybarcode.model.WoBatch;
import com.chenbro.deliverybarcode.model.base.BoxStatus;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.model.response.MonitorInfo;
import com.chenbro.deliverybarcode.service.IBoxService;
import com.chenbro.deliverybarcode.service.IWoBatchService;
import com.chenbro.deliverybarcode.utils.DownloadUtils;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * @ClassName BoxController
 * @Description TODO
 * @Author c8777
 * @Date 2020/3/14 12:40
 * @Version 1.0
 **/
@Controller
@RequestMapping("box")
public class BoxController {

    @Autowired
    private IBoxService boxService;

    @Autowired
    private DownloadUtils downloadUtils;

    @Autowired
    private IWoBatchService woBatchService;

    @RequestMapping("query")
    public String queryBox(Model model, @RequestParam(name = "page",required =true,defaultValue = "1")Integer pageNum, @RequestParam(name ="size",required = true,defaultValue = "10") Integer pageSize){
        List<Box> boxList = boxService.findAll(pageNum,pageSize);
        Integer pickedQty = boxService.findQtysByStatus(BoxStatus.PACK.getValue());
        Integer packedQty = boxService.findQtysByStatus(BoxStatus.PALLET.getValue());
        Integer receivedQty = boxService.findQtysByStatus(BoxStatus.RECEIVE.getValue());
        Integer shippedQty = boxService.findQtysByStatus(BoxStatus.SHIPPING.getValue());
        PageInfo pageInfo = new PageInfo(boxList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pickedQty",pickedQty);
        model.addAttribute("packedQty",packedQty);
        model.addAttribute("receivedQty",receivedQty);
        model.addAttribute("shippedQty",shippedQty);
        return "box/query";
    }


    /**
    * @Description //TODO  查询原浪潮出货栈板信息
    * @Date 2020/4/7 9:31
    * @return java.lang.String
    **/
    @RequestMapping("queryInspur")
    public String queryInspur(Model model, @RequestParam(name = "page",required =true,defaultValue = "1")Integer pageNum, @RequestParam(name ="size",required = true,defaultValue = "10") Integer pageSize){
        List<InspurPallet> inspurPallets = boxService.findAllInspurPallet(pageNum,pageSize);
        //PageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(inspurPallets);
        model.addAttribute("pageInfo",pageInfo);
        return "box/inspurQuery";
    }

    @RequestMapping("queryDetail")
    @ResponseBody
    public Result queryDetail(String uuid){
        Box detailById = boxService.findDetailById(uuid);
        return new Result(ResultCode.SUCCESS,detailById);
    }

    /**
     * @Description //TODO  导出装箱单数据
     * @Date 2020/3/19 20:30
     * @return java.lang.String
     **/
    public String export(HttpServletResponse response) throws IOException {
        //1.获取报表数据
        List<Box> boxes = boxService.findAll();
        //2.构建workbook 并构造sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //创建行
        //标题
        String[] titles = "箱号，装箱单".split(",");
        //处理标题
        Row row = sheet.createRow(0);

        int titleIndex = 0;
        for(String title : titles){
            Cell cell = row.createCell(titleIndex++);
            cell.setCellValue(title);
        }
        int rowIndex = 1;
        Cell cell = null;
        for(Box box : boxes){
            row = sheet.createRow(rowIndex++);
            //箱号
            cell = row.createCell(0);
            cell.setCellValue(box.getCartonNo());
            //数量
            cell = row.createCell(1);
            cell.setCellValue(box.getCartonQty());
        }
        //完成下载
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        downloadUtils.download(baos,response,"装箱单报表.xlsx");
        return "query";
    }


    @RequestMapping("store")
    public String forwardToStore(){
        return "box/storage";
    }


    @RequestMapping("outstore")
    public String forwardToOutstore(){
        return "box/outstore";
    }


    @RequestMapping("destroyBox")
    public String forwardDestroyBox(){
        return "box/destroyBox";
    }

    @RequestMapping("destroyPallet")
    public String forwardDestroyPallet(){
        return "box/destroyPallet";
    }

    @RequestMapping("qualityCheck")
    public String forwardToQualityCheck(){
        return "box/qualityCheck";
    }

    @RequestMapping("distributeBatch")
    public String distributeBatch(){
        return "box/distributeBatch";
    }
    /**
     * @Description //TODO  根據裝箱單號/棧板號  收貨處理 將boxStatus 修改爲2
     * @Date 2020/2/19 8:32
     * @return com.chenbro.deliverybarcode.model.base.Result
     **/
    @RequestMapping(value = "receive", method = RequestMethod.POST)
    @ResponseBody
    public Result receive(String id) {
        HubUser opUser = (HubUser) SecurityUtils.getSubject().getPrincipal();
        Box box = new Box();
        box.setCartonNo(id);
        box.setUpdateBy(opUser.getUsername());
        return boxService.receive(box);
    }

    /**
     * @Description //TODO 根據裝箱單號/棧板號  出貨處理  將boxStatus 修改爲3
     * @Date 2020/2/19 20:10
     * @return com.chenbro.deliverybarcode.model.base.Result
     **/
    @RequestMapping(value = "shipping", method = RequestMethod.POST)
    @ResponseBody
    public Result shipping(Box box) {
        HubUser opUser = (HubUser) SecurityUtils.getSubject().getPrincipal();
        box.setUpdateBy(opUser.getUsername());
        return boxService.shipping(box);
    }


    @RequestMapping("queryWoBatch")
    public String queryWoBatchs(Model model, @RequestParam(name = "page",required =true,defaultValue = "1")Integer pageNum, @RequestParam(name ="size",required = true,defaultValue = "10") Integer pageSize,@RequestParam(name = "woNo",required = false)String woNo){
        List<WoBatch> woBatches = woBatchService.findAll(pageNum, pageSize, woNo);
        PageInfo pageInfo = new PageInfo(woBatches);
        model.addAttribute("pageInfo",pageInfo);
        return "box/woBatch";
    }


    @RequestMapping("queryWoBatchById")
    @ResponseBody
    public Result queryWoBatchById(String uuid){
        WoBatch woBatch = woBatchService.findByUUid(uuid);
        return new Result(ResultCode.SUCCESS,woBatch);
    }

    @RequestMapping("distributeBatchNo")
    @ResponseBody
    public Result distributeBatchNo(String uniqueNo, String productType){
        HubUser opUser = (HubUser) SecurityUtils.getSubject().getPrincipal();
        return woBatchService.distributeBatchNo(uniqueNo, productType, opUser.getUsername());
    }

    @RequestMapping("updateWoBatch")
    @ResponseBody
    public Result updateWoBatch(WoBatch woBatch){
        synchronized (this){
            return woBatchService.updateBatchNo(woBatch);
        }
    }


    @RequestMapping("queryMonitor")
    @ResponseBody
    public Result queryMonitor(String prodLineId){
        List<MonitorInfo> monitorInfos = boxService.queryMonitorInfos(prodLineId);
        return new Result(ResultCode.SUCCESS,monitorInfos);
    }

    @RequestMapping("queryMonitorUncompleted")
    @ResponseBody
    public Result queryMonitorUncompleted(String prodLineId){
        List<MonitorInfo> monitorInfos = boxService.queryUncompletedInfos(prodLineId);
        return new Result(ResultCode.SUCCESS,monitorInfos);
    }


    @RequestMapping(value = "packingBoxes", method = RequestMethod.GET)
    @ResponseBody
    public Result packingBoxes() {
        return boxService.currentPallet();
    }


    @RequestMapping(value = "disconnect", method = RequestMethod.GET)
    @ResponseBody
    public Result disconnectPallet(String barcode) {
        HubUser opUser = (HubUser) SecurityUtils.getSubject().getPrincipal();
        return boxService.disconnectBox(barcode,opUser.getUsername());
    }
}
