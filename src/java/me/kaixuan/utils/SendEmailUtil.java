package me.kaixuan.utils;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件工具类
 */

public class SendEmailUtil {

    /**
     * 发送邮件
     *
     * @param email   收件人邮箱地址
     * @param subject 邮件主题
     * @param text    邮件内容
     * @throws AddressException   邮件地址异常
     * @throws MessagingException 发送消息时异常
     * @throws IOException        properties读取异常
     */
    public void sendEmail(String email, String subject, String text) throws AddressException, MessagingException, IOException {
        Properties properties = new Properties();
        // 读取邮件配置文件
        properties.load(this.getClass().getClassLoader().getResourceAsStream("email.properties"));
        // 得到会话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress(properties.getProperty("mail.user")));
        // 设置收件人邮箱地址
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(email)});
        // 设置邮件标题
        message.setSubject(subject);
        // 设置邮件内容
        message.setText(text);
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户,密码为邮箱开通的smtp服务后得到的客户端授权码,或邮箱密码
        transport.connect(properties.getProperty("mail.user"), properties.getProperty("mail.password"));
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();

    }
}

