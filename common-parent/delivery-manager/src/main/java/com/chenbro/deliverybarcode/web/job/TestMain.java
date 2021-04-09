package com.chenbro.deliverybarcode.web.job;

import com.chenbro.deliverybarcode.model.Box;
import com.chenbro.deliverybarcode.service.IBoxService;
import com.chenbro.deliverybarcode.utils.MailUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author z8777
 * @Date 2020/7/23 23:09
 * @Version 1.0
 **/
public class TestMain {


//    public static void main(String[] args) throws Exception {
//        // 1.创建任务调度器
////        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
////
////        // 2.创建任务实例
////        JobDetail jobDetail = JobBuilder.newJob(InformJob.class)
////                .withIdentity("job1", "group1")
////                .usingJobData("message","打印日志")        //传递参数，名称message
////                .build();//参数1 任务的名称(唯一实例）  参数2： 任务组的名称
////        // 3.触发器
////        Trigger trigger = TriggerBuilder.newTrigger()
////                .withIdentity("trigger1", "group1")
////                .startNow()             //马上启动触发器
////                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(2))
////                .usingJobData("message","simple触发器")        //传递参数，名称message
////                .build();
////        //让调度器关联任务和触发器，保证按照触发器按照定义的条件执行任务
////        scheduler.scheduleJob(jobDetail,trigger);
////        //启动
////        scheduler.start();
////        MailUtils.sendSimpleEmail();
//////        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //1.获取报表数据
////        List<Box> boxes = boxService.yesterdayShippedBoxes();
//        //2.构建workbook 并构造sheet
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet();
//        //创建行
//        //标题
//        String[] titles = "箱号,装箱数量,生产线别,工单号,客户编号,客户名称,客户PO,PO数量,客户料号,出货料号,工单数量,销货单号,车牌号,生产时间,出货时间".split(",");
//        //处理标题
//        Row row = sheet.createRow(0);
//
//        int titleIndex = 0;
//        for(String title : titles){
//            Cell cell = row.createCell(titleIndex++);
//            cell.setCellValue(title);
//        }
//        int rowIndex = 1;
//        Cell cell = null;
////        for(Box box : boxes){
////            row = sheet.createRow(rowIndex++);
////            //箱号
////            cell = row.createCell(0);
////            cell.setCellValue(box.getCartonNo());
////            //数量
////            cell = row.createCell(1);
////            cell.setCellValue(box.getCartonQty());
////            //生产线别
////            cell = row.createCell(2);
////            cell.setCellValue(box.getProdLineDesc());
////            //工单号
////            cell = row.createCell(3);
////            cell.setCellValue(box.getWorkNo());
////            //客户编号
////            cell = row.createCell(4);
////            cell.setCellValue(box.getCusNo());
////            //客户名称
////            cell = row.createCell(5);
////            cell.setCellValue(box.getCusName());
////            //客户PO
////            cell = row.createCell(6);
////            cell.setCellValue(box.getCusPo());
////            //PO数量
////            cell = row.createCell(7);
////            cell.setCellValue(box.getPoQty());
////            //客户料号
////            cell = row.createCell(8);
////            cell.setCellValue(box.getCusMatno());
////            //出货料号
////            cell = row.createCell(9);
////            cell.setCellValue(box.getDelMatno());
////            //工单数量
////            cell = row.createCell(10);
////            cell.setCellValue(box.getWoQuantity());
////            //车牌号
////            cell = row.createCell(11);
////            cell.setCellValue(box.getVehicleNo());
////            //生产时间
////            cell = row.createCell(12);
////            cell.setCellValue(box.getCreateDate());
////            //出货时间
////            cell = row.createCell(13);
////            cell.setCellValue(box.getUpdateDate());
////        }
//        for (int i = 0; i < titles.length; i++) {
//            sheet.autoSizeColumn(i);
//        }
//        //完成下载
//        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
//        try {
//            workbook.write(baos);
//            workbook.close();
//            DataSource ds = new ByteArrayDataSource(baos.toByteArray(),"application/excel");
//            String[] mailsTo = {"stonechen@chenbro.com","Bravemo@chenbro.com"};
//            String[] mailsCC = {"lavinetseng@chenbro.com","kervinluo@chenbro.com"};
//            MailUtils.sendAttachmentMail(mailsTo,mailsCC,"出货通知单","已出货清单，请查看附件！",ds,"shippingList.xlsx");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if(baos != null){
//                try {
//                    baos.close();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


    public static Map<Integer, String> map = new HashMap();


    public static Map<Integer, String> mapNum = new HashMap();

    //    public static void main(String[] args) throws IOException {
//        map.put(0,"零");
//        map.put(1,"壹");
//        map.put(2,"贰");
//        map.put(3,"叁");
//        map.put(4,"肆");
//        map.put(5,"伍");
//        map.put(6,"陆");
//        map.put(7,"柒");
//        map.put(8,"捌");
//        map.put(9,"玖");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String input = "1.20";
//        while ((input = br.readLine()) != null){
//            StringBuilder sb = new StringBuilder();
//            boolean isZero = false;
//            sb.append("人民币");
//            String[] strArr = input.split("\\.");
//            int first = strArr[0].length();
//            int two = strArr[1].length();
//            for(int i =0,j = first; i < first; i++,j--){
//                int num = Integer.parseInt(String.valueOf(strArr[0].charAt(i)));
//                if(num == 0){
//                    isZero =true;
//                    if(first != 1){
//                        sb.append(unit(j));
//                    }
//                    continue;
//                }
//                if(num != 0 && (j == 9 || j == 5 || j ==1)){
//                    if(isZero){
//                        sb.append("零");
//                        isZero = false;
//                    }
//                }
//                if(j >= 9){
//                    sb.append(transfer(num,(j - 9)));
//                    if(j == 9){
//                        sb.append(unit(j));
//                    }
//                    continue;
//                }
//                if(j >= 5){
//                    sb.append(transfer(num,(j - 5)));
//                    if(j == 5){
//                        sb.append(unit(j));
//                    }
//                    continue;
//                }
//                sb.append(transfer(num,j -1));
//                if(j == 1){
//                    sb.append(unit(j));
//                }
//                continue;
//            }
//            boolean twoIsZero = false;
//            for(int i =0,j = two; i < two; i++,j--){
//                int num = Integer.parseInt(String.valueOf(strArr[1].charAt(i)));
//                if(num == 0){
//                    continue;
//                }else{
//                    sb.append(map.get(num));
//                    if(j == 2){
//                        sb.append("角");
//                    }
//                    if(j == 1){
//                        sb.append("分");
//                    }
//                }
//
//            }
//            System.out.println(sb.toString());
//        }
//
//
//
//
//    }
//public static void main(String[] args) throws IOException{
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    String input;
//    List<Character> charList = new ArrayList<>();
//    charList.add('0');
//    charList.add('1');
//    charList.add('2');
//    charList.add('3');
//    charList.add('4');
//    charList.add('5');
//    charList.add('6');
//    charList.add('7');
//    charList.add('8');
//    charList.add('9');
//    DecimalFormat df = new DecimalFormat("0.0");
//    while((input = br.readLine()) != null){
//        StringBuilder sb = new StringBuilder();
//        boolean isNum = false;
//        for(int i = 0; i < input.length(); i++){
//            char currentChar = input.charAt(i);
//            if(charList.contains(currentChar)){
//                if(!isNum){
//                    sb.append("*");
//                    isNum = true;
//                }
//                sb.append(currentChar);
//                continue;
//            }
//            if(isNum){
//                sb.append("*");
//                isNum = false;
//            }
//            sb.append(currentChar);
//
//        }
//        System.out.println(sb.toString());
//    }
//
//}

//    public static void main(String[] args) throws IOException {
//        String input = "yybb200dp744x1k7mnj5w1120bzx0bn769g06oo3z7l3527l6jy1z3e0cf8f7z63u04zk7h7gh504c695330s8ny1wqxct16s01c7x4w7dnpy9m3o1w68j2r45u3ica4cq8y0qfnwoi0c4y0x98ln1ih6mq0rnj9n589z8d056h379xoo35ed06lr4vmx7q3yo26p5o04dc2c5a5i35fomnj708fdj045b1wpl86azp8y90loij7dnz5xwj56mbfwg9t4tf7155tzd189gp6w2537ou3hk5t5p086794696pb1ra7w46oylp9k66v2e8dw885wvbc2p5na7kh3b85m5i0fd9r620u4sji18a4rb5424z922lc02sekk09gj7i26x7c198y9s643nypwpuyem735j2m7g12kd113vk1puwfjva2obx4i4tx7u81n4vjc000xx7j0e2e3mg7zd45yk7dh027jfj94aoqkj9t1t81b66sy02p7d12dbbeq911jicn261iu2u2b2c7zgh7lho8134pk683zcyi5940yberoj08703mc4";
//        Map<Character, Integer> mapChar = new HashMap<>();
//
//        for (int i = 0; i < input.length(); i++) {
//            char keyVal = input.charAt(i);
//            if (mapChar.containsKey(keyVal)) {
//                mapChar.put(keyVal, (mapChar.get(keyVal) + 1));
//            } else {
//                mapChar.put(keyVal, 1);
//            }
//
//        }
//        char[] resultArr = new char[mapChar.size()];
//        int[] times = new int[mapChar.size()];
//        int j = 0;
//        for (Map.Entry<Character, Integer> entry : mapChar.entrySet()) {
//            resultArr[j] = entry.getKey();
//            times[j] = entry.getValue();
//            j++;
//        }
//        for (int i = 0; i < (resultArr.length - 1); i++) {
//            int mediumNum = 0;
//            char mediumChar = ' ';
//            for (int k = 1; k < resultArr.length; k++) {
//                if (times[k - 1] <= times[k]) {
//                    if (times[k - 1] == times[k]) {
//                        if (resultArr[k - 1] > resultArr[k]) {
//                            mediumChar = resultArr[k - 1];
//                            mediumNum = times[k - 1];
//                            resultArr[k - 1] = resultArr[k];
//                            times[k - 1] = times[k];
//                            resultArr[k] = mediumChar;
//                            times[k] = mediumNum;
//                            continue;
//                        }
//                    } else {
//                        mediumChar = resultArr[k - 1];
//                        mediumNum = times[k - 1];
//                        resultArr[k - 1] = resultArr[k];
//                        times[k - 1] = times[k];
//                        resultArr[k] = mediumChar;
//                        times[k] = mediumNum;
//                    }
//                }
//            }
//
//        }
//        StringBuilder sb = new StringBuilder();
//        for(char item : resultArr){
//            sb.append(item);
//        }
//        System.out.println(sb.toString());
//
//
//    }
//public static void main(String[] args) {
//    List<String> list = new ArrayList<>();
////    int readNum = Integer.parseInt(input);
//    String[] inputStrs = {"8hs1kb6tdk7y2395r0kpg54","8wilq1o25o1t53q4so7jy8"};
//
//    for(String itemStr : inputStrs){
//        //计算可遍历的次数
//        int divided = itemStr.length()/8;
//        //计算是否可整除8
//        int mod = itemStr.length()%8;
//        for(int j = 0; j < divided; j++){
//            list.add(itemStr.substring(8*j,8*j +8));
//        }
//        if(mod != 0){
//            int fillZeroNum = 8 - mod;
//            String initialStr = itemStr.substring((divided * 8),itemStr.length());
//            for(int j = 0 ; j < fillZeroNum; j++){
//                initialStr += 0;
//            }
//            list.add(initialStr);
//        }
//    }
//    for(String item : list){
//        System.out.println(item);
//    }
//
//
//}

    public static void main(String[] args) {
        System.out.println(compute(81));
        TrafficLight color = TrafficLight.RED;
        System.out.println(color);

    }

    public static int char2int(char factorial) {
        int num = 0;
        switch (factorial) {
            case '0':
                num = 0;
                break;
            case '1':
                num = 1;
                break;
            case '2':
                num = 2;
                break;
            case '3':
                num = 3;
                break;
            case '4':
                num = 4;
                break;
            case '5':
                num = 5;
                break;
            case '6':
                num = 6;
                break;
            case '7':
                num = 7;
                break;
            case '8':
                num = 8;
                break;
            case '9':
                num = 9;
                break;
            case 'A':
                num = 10;
                break;
            case 'B':
                num = 11;
                break;
            case 'C':
                num = 12;
                break;
            case 'D':
                num = 13;
                break;
            case 'E':
                num = 14;
                break;
            case 'F':
                num = 15;
                break;
        }
        return num;
    }



    public static int compute(int num){
        if(num <= 2){
            if(num == 2){
                return 1;
            }
            return 0;
        }else{
            int x = num/3;
            return x + compute(x + num%3);
        }
    }

    public static int[] alignArr(int[] numArr, int alignmark) {
        int medium = 0;
        if (alignmark == 0) {
            for (int i = 0; i < (numArr.length - 1); i++) {
                for (int j = 1; j < numArr.length; j++) {
                    if (numArr[j - 1] > numArr[j]) {
                        medium = numArr[j - 1];
                        numArr[j - 1] = numArr[j];
                        numArr[j] = medium;
                    }
                }
            }
        } else {
            for (int i = 0; i < (numArr.length - 1); i++) {
                for (int j = 1; j < numArr.length; j++) {
                    if (numArr[j - 1] < numArr[j]) {
                        medium = numArr[j - 1];
                        numArr[j - 1] = numArr[j];
                        numArr[j] = medium;
                    }
                }
            }
        }
        return numArr;

    }

    public static String transfer(int num, int length) {
        String handleStr = "";
        switch (length) {
            case 0:
                handleStr = map.get(num);
                break;
            case 1:
                if (num == 1) {
                    handleStr = "拾";
                } else {
                    handleStr = map.get(num) + "拾";
                }
                break;
            case 2:
                handleStr = map.get(num) + "佰";
                break;
            case 3:
                handleStr = map.get(num) + "仟";
                break;
        }
        return handleStr;
    }

    public static String unit(int length) {
        String handleStr = "";
        switch (length) {
            case 1:
                handleStr = "元";
                break;
            case 5:
                handleStr = "万";
                break;
            case 9:
                handleStr = "亿";
                break;
        }
        return handleStr;
    }


    public static int convertHex(String hexStr) {

        int sum = 0;
        char[] hexChar = hexStr.toCharArray();
        for (int i = 2; i < hexChar.length; i++) {
            int factorial = 0;
            switch (hexChar[i]) {
                case 'A':
                    factorial = 10;
                    break;
                case 'B':
                    factorial = 11;
                    break;
                case 'C':
                    factorial = 12;
                    break;
                case 'D':
                    factorial = 13;
                    break;
                case 'E':
                    factorial = 14;
                    break;
                case 'F':
                    factorial = 15;
                    break;
                default:
                    factorial = Integer.parseInt(String.valueOf(hexChar[i]));
            }
            int mex = 1;
            if (i != (hexChar.length - 1)) {
                for (int j = 2; j <= hexChar.length - i; j++) {
                    mex *= 16;
                }
            }
            sum += mex * factorial;
        }
        return sum;

    }


    public static int modInt(int modNum) {
        if (modNum == 2) {
            return 1;
        }
        if (modNum < 2) {
            return 0;
        }
        if (modNum % 3 == 0) {
            int i = modNum / 3;
            return i + modInt(i);
        } else {
            int i = modNum / 3;
            int surplus = modNum % 3 + modNum / 3;
            return i + modInt(surplus);
        }

    }

    public static StringBuilder loopNum(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append("ABCD");
        }
        return sb;
    }
}
