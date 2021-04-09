package com.chenbro.deliverybarcode.web.controller;

import com.chenbro.deliverybarcode.model.*;
import com.chenbro.deliverybarcode.model.base.Result;
import com.chenbro.deliverybarcode.model.base.ResultCode;
import com.chenbro.deliverybarcode.service.IHubDepartmentService;
import com.chenbro.deliverybarcode.service.IHubUserService;
import com.chenbro.deliverybarcode.utils.DownloadUtils;
import com.chenbro.deliverybarcode.utils.PoiUtils;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author c8777
 * @Date 2020/2/11 13:39
 * @Version 1.0
 **/
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IHubUserService userService;

    @Autowired
    private PoiUtils poiUtils;


    @Autowired
    private IHubDepartmentService hubDepartmentService;

    @Autowired
    private DownloadUtils downloadUtils;


    
    @RequestMapping("login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value = "remember", defaultValue = "0") String remember, Model model) throws CommonException {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (!currentUser.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                if (remember != null && remember.equals("1")) {
                    token.setRememberMe(true);
                }
                currentUser.login(token);
            }
        } catch (UnknownAccountException uce) {
            model.addAttribute("msg","用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg","密码不正确");
            return "login";
        } catch (LockedAccountException lae) {
            model.addAttribute("msg","当前用户未啓用");
            return "login";
        } catch (AuthenticationException e) {
            model.addAttribute("msg","用户名/密码错误");
            return "login";
        }
        HubUser user = userService.findByUsername(username);
        model.addAttribute("user",user);
        return "main";

    }

    @RequestMapping("find")
    public String find(String uuid) throws CommonException {

        HubUser user = userService.findByUUid(uuid);
        System.out.println("user:" + user);
        return null;
    }

    @RequestMapping("index")
    public String index() {
        return "login";
    }

    @RequestMapping("monitor")
    public String monitorC() {
        return "monitor";
    }

    @RequestMapping("monitorb")
    public String monitorB() {
        return "monitorB";
    }

    @RequestMapping("monitora")
    public String monitorA() {
        return "monitorA";
    }


    @RequestMapping("shipment")
    public String shipmentScan() {
        return "shipmentScan";
    }

    @RequestMapping("main")
    public String toMain() {
        return "main";
    }

    /**
    * @Description //TODO  登出
    * @Date 2020/3/9 23:51
    * @return java.lang.String
    **/
    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


    @RequestMapping("query")
    public String query(Model model,@RequestParam(name = "page",required =true,defaultValue = "1")Integer pageNum,@RequestParam(name ="size",required = true,defaultValue = "6") Integer pageSize){
        List<HubUser> userList = userService.findAll(pageNum,pageSize);
        //PageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(userList);
        model.addAttribute("pageInfo",pageInfo);
        return "user/query";
    }

    public String save(Model model, @RequestBody HubUser hubUser){

        return "user/query";
    }

    @RequestMapping("queryDetail")
    public String queryDetail(Model model,String id){
        HubUser hubUser = userService.findByUUid(id);
        model.addAttribute("user",hubUser);
        return "user/queryDetail";
    }

    @RequestMapping("expire")
    public String expire(){
        return "login";
    }

    /**
    * @Description //TODO  用户添加功能，默认密码为12345
    * @Date 2020/3/18 18:19
    * @return java.lang.String
    **/
    @RequestMapping("add")
    @ResponseBody
    public Result addUser(HubUser hubUser){
        HubUser byUsername = userService.findByUsername(hubUser.getUsername());
        if(byUsername != null){
            return new Result(ResultCode.ADDUSERFAIL);
        }
        if(hubUser.getEnableState() == null){
            hubUser.setEnableState("0");
        }
        Object credentials = "12345";
        String hashAlgorithmName = "MD5";
        String username = hubUser.getUsername();
        Object salt = ByteSource.Util.bytes(username);
        int hashIterations = 1902;
        HubUser opUser = (HubUser) SecurityUtils.getSubject().getPrincipal();
        hubUser.setCreateBy(opUser.getUsername());
        Object result = new SimpleHash(hashAlgorithmName,credentials,salt,hashIterations);
        hubUser.setPassword(result.toString());
        userService.insert(hubUser);
        return new Result(ResultCode.SUCCESS);
    }

    /**
    * @Description //TODO  excel 批量导入用户
    * @Date 2020/3/19 8:11
    * @return java.lang.String
    **/
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String uploadUsers(@RequestParam(name="file") MultipartFile file) throws IOException {
        //1.解析excel
        //1.1 根据excel文件创建工作薄
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        //2. 获取sheet，根据索引获取第一个sheet
        Sheet sheetAt = workbook.getSheetAt(0);

        //3.获取sheet中的每一行，和每一个单元格
        // 获取用户数据列表
        List<HubUser> users = new ArrayList<>();
        for(int rowNum = 0; rowNum < sheetAt.getLastRowNum(); rowNum++){
            Row row = sheetAt.getRow(rowNum);  //根据所以获取每一行
            Object[] values = new Object[row.getLastCellNum()];
            for(int cellNum = 0 ;cellNum <= row.getLastCellNum(); cellNum++){
                //根据所以获取每个单元格
                Cell cell = row.getCell(cellNum);
                //获取每一个单元格内容
                Object cellValue = poiUtils.getCellValue(cell);
                values[cellNum] = cellValue;
            }
            HubUser user = new HubUser(values);
            users.add(user);
        }

        //4.获取用户数据列表

        //5.批量保存用户

        return "redirect:user/query";
    }

    /**
    * @Description //TODO  导出excel报表
    * @Date 2020/3/19 20:30
    * @return java.lang.String
    **/
    public String export(){

        return "query";
    }


    @RequestMapping("uploadPhoto/{id}")
    @ResponseBody
    public Result handleBase64(@PathVariable("id") String id,@RequestParam("file")MultipartFile file) throws IOException {
        //1.调用service 保存图片，返回数据 (获取图片的访问地址(dataUrl | http地址))
        String imgUrl = userService.uploadImage(id,file);
        //2.返回数据
        return new Result(ResultCode.SUCCESS,imgUrl);
    }

    @RequestMapping("update")
    public String updateUser(HubUser hubUser,@RequestParam("stuffPhoto")MultipartFile file){
        if(("").equals(hubUser.getEnableState())|| hubUser.getEnableState() == null){
            hubUser.setEnableState("0");
        }
        userService.update(hubUser);
        return "redirect:query.do";
    }





}
