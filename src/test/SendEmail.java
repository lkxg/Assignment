
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class SendEmail {
    public static void main(String[] args) throws MessagingException, GeneralSecurityException {
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

        //开启debug模式
        //session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect("mail0.serv00.com","liu@kaixuan.me","Lkx19836093883");

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress("liu@kaixuan.me"));

        //邮件接收人

        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress("3193888648@qq.com"));

        //邮件标题
        mimeMessage.setSubject("这是一个测试邮件");

        //邮件内容
        mimeMessage.setContent("测试成功","text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
    }
}
