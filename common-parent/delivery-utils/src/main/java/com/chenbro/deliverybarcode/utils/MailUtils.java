package com.chenbro.deliverybarcode.utils;


import org.apache.commons.mail.*;
import org.springframework.core.io.InputStreamSource;

import javax.activation.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName MailUtils
 * @Description TODO
 * @Author z8777
 * @Date 2020/7/28 10:16
 * @Version 1.0
 **/
public class MailUtils {

    //邮件发送服务器
    public final static String HOST_NAME = "tpmail1.chenbro.com.tw";           //tpmail1.chenbro.com.tw   smtp.qq.com
    //邮件发送服务器端口号:SSL->994/25
    public final static int SMTP_PORT = 465;
    //邮箱登录用户名，即邮箱地址
    public final static String AUTH_USERNAME = "barcode";              // qq邮箱： 350989039@qq.com
    //客户端授权码，注意不是邮箱的密码
    public final static String AUTH_PASSWORD = "2wsx1qaz";           //"dvaurzizxrvacafg"   1qaz2wsx;
    //设置发送email
    public final static String FROM_MAIL = "barcode@chenbro.com";

    /**
     * 发送附件邮件
     *
     * @throws Exception
     */
    public static void sendAttachmentMail(String[] mailsTo, String[] mailsCC, String subject, String content, DataSource file, String fileName) throws Exception
    {
        MultiPartEmail mail = new MultiPartEmail();
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮箱服务器信息
        mail.setHostName(HOST_NAME);
        // 设置密码
        mail.setAuthentication(AUTH_USERNAME,AUTH_PASSWORD);
        // 设置超时时间
        mail.setSocketTimeout(25000);
        // 设置邮件发送者
        mail.setFrom(FROM_MAIL,"出货条码系统");
        // 设置邮件接收者
        mail.addTo(mailsTo);
        // 设置邮件抄送者
        mail.addCc(mailsCC);
        // 设置邮件主题
        mail.setSubject(subject);
        // 设置邮件内容
        mail.setMsg(content);
        // 创建附件
//        EmailAttachment attachment = new EmailAttachment();
//        attachment.setPath("1_jianggujin.jpg");
//        attachment.setDisposition(EmailAttachment.ATTACHMENT);
//        attachment.setName("1_jianggujin.jpg");
        mail.attach(file,fileName,"已出货清单",EmailAttachment.ATTACHMENT);
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }


    public static void sendTextMail() throws Exception
    {
        SimpleEmail mail = new SimpleEmail();
        // 设置邮箱服务器信息
        mail.setSmtpPort(465);
        mail.setHostName("smtp.qq.com");
        mail.setAuthentication("350989039@qq.com","vrudtjsnilhjbjfe");
        // 设置密码验证器
//        mail.setAuthenticator(new DefaultAuthenticator("350989039@qq.com","vrudtjsnilhjbjfe"));
        // 设置邮件发送者
        mail.setFrom("350989039@qq.com","出货条码系统");
        // 设置邮件接收者
        mail.addTo("loukiakong@163.com");
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject("Test Email");
        // 设置邮件内容
        mail.setMsg("this is a test Text mail");
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }





    public static void main(String[] args) throws EmailException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleEmail email = new SimpleEmail();
        email.setHostName("tpmail1.chenbro.com.tw");
        email.setAuthentication("barcode", "1qaz2wsx");//邮件服务器验证：用户名/密码
        email.setCharset("UTF-8");// 必须放在前面，否则乱码
        email.addCc("kervinluo@chenbro.com");
        email.setFrom("barcode@chenbro.com", "overtime_compensate_system");
        email.setSubject("出货通知信息" + sdf.format(new Date()));
        StringBuilder msgInfo = new StringBuilder();
        msgInfo.append("莫工给力：").append("\r\n\t");
        msgInfo.append("1、待赔付数量：4").append("\r\n\t");
        msgInfo.append("2、赔付中数量：5").append("\r\n\t");
        msgInfo.append("3、赔付失败数量：6").append("\r\n\t");
        msgInfo.append("统计时间：").append(sdf.format(new Date()));

        email.setMsg(msgInfo.toString());
        email.send();
    }


//    public static void main(String[] args) throws Exception {
//        try {
//            Email email = new SimpleEmail();
//            email.setHostName("172.22.16.106");
////            email.setAuthenticator(new DefaultAuthenticator("barcode@chenbro.com", "1qaz2wsx"));
//            email.setAuthentication("barcode", "1qaz2wsx");
//            //设置编码格式，防止乱码
//            email.setCharset("UTF-8");
//            email.setFrom("barcode@chenbro.com");
//            email.setSubject("主题");
//            email.setMsg("发送邮件");
//            email.addTo("kervinluo@chenbro.com");
//            email.send();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("=====>发送完毕！");
//    }

}
