import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import me.kaixuan.entity.Comment;
import me.kaixuan.entity.News;
import me.kaixuan.entity.User;
import me.kaixuan.mapper.NewsMapper;
import me.kaixuan.mapper.UserMapper;
import me.kaixuan.service.CommentService;
import me.kaixuan.service.NewsService;
import me.kaixuan.service.UserService;
import me.kaixuan.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

public class UserTest {
    @Test
    public void electAllUsers() {
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService commentService = (CommentService) applicationContext.getBean("commentServiceImpl");
        List<Comment> comments = commentService.selectCommentByNewsId(18);
        for (Comment comment : comments) {
            System.out.println(comment);
        }

    }
    @Test
    public void test() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }
    @Test
    public void test1() {
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "pr_J7w3p_uqIRIozvNVGWU7UCb5zYr77K0v-GL42";
        String secretKey = "IqXrLjRDF9YsDNAKwrMVmBX1ziDtwdN6FC_-Z58Y";
        String bucket = "qnycloudspace";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "E:\\WorkPlace\\web_workplace\\Assignment\\web\\image\\wallhaven-ex9gwo_1500x750.png";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

}
