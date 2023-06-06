import me.kaixuan.entity.News;
import me.kaixuan.entity.User;
import me.kaixuan.mapper.NewsMapper;
import me.kaixuan.mapper.UserMapper;
import me.kaixuan.service.NewsService;
import me.kaixuan.service.UserService;
import me.kaixuan.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;

public class UserTest {
    @Test
    public void electAllUsers() {
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("applicationContext.xml");
        NewsService newsService = (NewsService) applicationContext.getBean("newsServiceImpl");
        List<News> news = newsService.selectAllNews();
        for (News news1 : news) {
            System.out.println(news1);
        }

    }
    @Test
    public void test() {
        File folder = new File("web/image");
        String absolutePath = folder.getAbsolutePath();
        System.out.println(absolutePath);
    }

}
