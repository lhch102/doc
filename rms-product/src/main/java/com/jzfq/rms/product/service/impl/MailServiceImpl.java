package com.jzfq.rms.product.service.impl;


import com.jzfq.rms.product.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailServiceImpl implements IMailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
    /**
     * smtp服务器
     */
    @Value("${product.email.host}")
    private String host;

    /**
     * 用户名
     */
    @Value("${product.email.user}")
    private String user;

    /**
     * 企业邮授权码
     */
    @Value("${product.email.auth}")
    private String auth;

    /**
     * 发送邮件
     * @param from 发件人
     * @param to 收件人
     * @param subject 邮件标题
     * @param content 邮件内容
     */
    @Override
    public void send(String from, String to, String subject, String content) {
        LOGGER.info("MailServiceImpl.send() param=[from:"+from+"to:"+to+"subject:"+subject+"content:"+content+"]");

        Properties props = new Properties();

        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", host);
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");

        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);

        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        session.setDebug(true);

        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 加载发件人地址
            message.setFrom(new InternetAddress(from));
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 加载标题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            multipart.addBodyPart(contentPart);

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(host, user, auth);
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            LOGGER.error("MailServiceImpl.send() error", e.getMessage());
            e.printStackTrace();
        }
        LOGGER.info("MailServiceImpl.send() end");
    }
}