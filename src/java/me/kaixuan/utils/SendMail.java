package me.kaixuan.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class SendMail {
    public  MimeMessage getMessage() throws MessagingException, GeneralSecurityException {
        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host","mail0.serv00.com");

        properties.setProperty("mail.transport.protocol","smtp");

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("liu@kaixuan.me","Lkx19836093883");
            }
        });

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress("liu@kaixuan.me"));

        return mimeMessage;
    }

    public Transport getTransport() throws MessagingException {
        Properties properties = new Properties();

        properties.setProperty("mail.host","mail0.serv00.com");

        properties.setProperty("mail.transport.protocol","smtp");

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("liu@kaixuan.me","Lkx19836093883");
            }
        });

        //获取连接对象
        Transport transport = session.getTransport();
        transport.connect("mail0.serv00.com","liu@kaixuan.me","Lkx19836093883");
        return transport;
    }
}
