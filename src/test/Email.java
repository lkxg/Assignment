import org.junit.Test;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Email {
    @Test
    public  void sendEmail( String email, String subject,String text) throws AddressException, MessagingException{
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("www.kaixuan.me@qq.com"));
        // 设置收件人邮箱地址
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(email)});
        //message.setRecipient(Message.RecipientType.TO, new InternetAddress("xxx@qq.com"));//一个收件人
        // 设置邮件标题
        message.setSubject("汽车商城预约试驾提醒:");
        // 设置邮件内容
        message.setText("尊敬的用户您好,您的预约试驾请求已同意，请在自邮件发送日起三日内前往本辖区内官方体验店，详情请咨询12345678");//这是我们的邮件要发送的信息内容
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("www.kaixuan.me@qq.com", "irthugnqtstnddhf");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码,输入自己的即可
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }



}

