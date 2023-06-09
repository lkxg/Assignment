package me.kaixuan.controller;

import me.kaixuan.entity.Comment;
import me.kaixuan.entity.News;
import me.kaixuan.entity.User;
import me.kaixuan.service.CommentService;
import me.kaixuan.service.NewsService;
import me.kaixuan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NewsController {
    @Resource
    private NewsService newsService;
    @Resource
    private UserService userService;
    @Resource
    private CommentService commentService;
    /**
     * 发布新闻
     * @param request 用于获取参数
     * @param session 用于获取当前用户
     * @return 返回发布新闻页面
     */
    @RequestMapping("/releasenews")
    public String releaseNews(News news, HttpSession session, HttpServletRequest request,@RequestParam("file") MultipartFile file) {
        // 获取当前用户
        User user = userService.selectUserByUsername((String) session.getAttribute("username"), (String) session.getAttribute("password"));
        // 设置作者id
        news.setAuthorId(user.getId());
        // 获取当前时间
        Date now = new Date();
        // 创建SimpleDateFormat对象，指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式化日期
        String nowStr = sdf.format(now);
        // 将字符串转换为Timestamp类型
        Timestamp nowTime = Timestamp.valueOf(nowStr);
        // 设置发布时间
        news.setPublishTime(nowTime);
        // 设置状态为待审核
        news.setStatusId(1);
        String path = null;
        // 获取新闻来源
        String origin = request.getParameter("origin");
        // 获取新闻类型
        String newsType = request.getParameter("newsType");
        if (!file.isEmpty()) {
            // 获取文件名
            try {
                // 文件保存路径
                byte[] bytes = file.getBytes();
                // 获取项目路径
                Path paths = Paths.get("E:\\WorkPlace\\web_workplace\\Assignment\\web\\image\\" + file.getOriginalFilename());
                // 写入文件
                Files.write(paths, bytes);
                // 设置图片路径
                path = "/image/" + file.getOriginalFilename();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 设置封面
        news.setCover(path);
        // 设置新闻来源
        if (origin.equals("reprint")) {
            news.setReprint(request.getParameter("reprint"));
        }
        // 设置新闻类型
        if (newsType.equals("yule")){
            news.setCategoryId(1);
        }
        if (newsType.equals("tiyu")){
            news.setCategoryId(2);
        }
        if (newsType.equals("keji")){
            news.setCategoryId(3);
        }
        if (newsType.equals("dianying")){
            news.setCategoryId(4);
        }
        if (newsType.equals("youxi")){
            news.setCategoryId(5);
        }
        if (newsType.equals("ruanjian")){
            news.setCategoryId(6);
        }
        if (newsType.equals("qita")){
            news.setCategoryId(7);
        }
        // 调用service层方法插入新闻
        newsService.insertNews(news);
        // 设置提示信息
        request.setAttribute("msg","发布成功");
        // 返回发布新闻页面
        return "forward:release";
    }
    @RequestMapping("/check")
    public String check(HttpServletRequest request,HttpSession session){
        List<News> news = newsService.selectAllNews();
        request.setAttribute("checkNews",news);
        return "check";
    }
    @RequestMapping("/checknewsapprove")
    public String checkNewsApprove(HttpServletRequest request,HttpSession session){
        String approve = request.getParameter("approve");
        Integer id = Integer.parseInt(approve);
        newsService.updateNewsStatus(id);
        return "redirect:check";
    }
    @RequestMapping("/checknewsreject")
    public String checkNewsReject(HttpServletRequest request,HttpSession session){
        String reject = request.getParameter("reject");
        Integer id = Integer.parseInt(reject);
        newsService.updateNewsStatusReject(id);
        return "redirect:check";
    }
    @RequestMapping("/deletenews")
    public String deleteNews(HttpServletRequest request,Integer newsId){
        if (newsId == null) {
            String delete = request.getParameter("delete");
            Integer id = Integer.parseInt(delete);
            newsService.deleteNews(id);
            return "redirect:check";
        } else {
            newsService.deleteNews(newsId);
            return "redirect:mynews";
        }

    }
    @RequestMapping("/mynews")
    public String showMyNews(HttpServletRequest request,HttpSession session){
        User user = userService.selectUserByUsername((String) session.getAttribute("username"), (String) session.getAttribute("password"));
        List<News> news = newsService.selectNewsByAuthorId(user.getId());
        request.setAttribute("myNews",news);
        return "mynews";
    }
    @RequestMapping("/shownews")
    public String showNews(HttpServletRequest request,Integer newsId,HttpSession session){

        if (request.getSession().getAttribute("newsId") != null){
            request.getSession().removeAttribute("newsId");
        }
        News news = newsService.selectNewsById(newsId);
        System.out.println(news);
        User user = userService.selectUserByUsername((String) session.getAttribute("username"), (String) session.getAttribute("password"));
        if (user != null){
            request.setAttribute("user",user);
        }
        request.setAttribute("showNews",news);
        request.getSession().setAttribute("newsId",newsId);
        List<Comment> comments = commentService.selectCommentByNewsId(newsId);
        request.setAttribute("comments",comments);
        return "news";
    }
    @RequestMapping("/news")
    public String news(Integer newsType,HttpServletRequest request){
      if (newsType == 1) {
          List<News> news = newsService.selectNewsByCategoryId(1);
          for (News n : news) {
              System.out.println(n);
          }
          request.setAttribute("newsType", "娱乐");
          request.setAttribute("news", news);
      }
        if (newsType == 2) {
            List<News> news = newsService.selectNewsByCategoryId(2);
            request.setAttribute("newsType", "体育");
            request.setAttribute("news", news);
        }
        if (newsType == 3) {
            List<News> news = newsService.selectNewsByCategoryId(3);
            request.setAttribute("newsType", "科技");
            request.setAttribute("news", news);
        }
        if (newsType == 4) {
            List<News> news = newsService.selectNewsByCategoryId(4);
            request.setAttribute("newsType", "电影");
            request.setAttribute("news", news);
        }
        if (newsType == 5) {
            List<News> news = newsService.selectNewsByCategoryId(5);
            request.setAttribute("newsType", "游戏");
            request.setAttribute("news", news);
        }
        if (newsType == 6) {
            List<News> news = newsService.selectNewsByCategoryId(6);
            request.setAttribute("newsType", "软件");
            request.setAttribute("news", news);
        }
        if (newsType == 7) {
            List<News> news = newsService.selectNewsByCategoryId(7);
            request.setAttribute("newsType", "其他");
            request.setAttribute("news", news);
        }
        return "newstype";
    }
    @RequestMapping("/search")
    public String search(String searchTitle,HttpServletRequest request){
        List<News> searchNews = newsService.selectNews(searchTitle);
        request.setAttribute("searchNews",searchNews);
        return "searchnews";
    }
}
