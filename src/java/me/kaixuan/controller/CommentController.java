package me.kaixuan.controller;

import me.kaixuan.entity.Comment;
import me.kaixuan.entity.User;
import me.kaixuan.service.CommentService;
import me.kaixuan.service.NewsService;
import me.kaixuan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;
    @Resource
    private NewsService newsService;
    @RequestMapping("/addcomment")
    public String addComment(Comment comment, HttpServletRequest request) {
        Integer newsId = (Integer) request.getSession().getAttribute("newsId");
       comment.setNewsId(newsId);
        // 获取当前时间
        Date now = new Date();
        // 创建SimpleDateFormat对象，指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式化日期
        String nowStr = sdf.format(now);
        // 将字符串转换为Timestamp类型
        Timestamp nowTime = Timestamp.valueOf(nowStr);
        comment.setCommentTime(nowTime);
        User user = userService.selectUserByUsername((String) request.getSession().getAttribute("username"), (String) request.getSession().getAttribute("password"));
        comment.setCommenterId(user.getId());
        commentService.addComment(comment);
        return "redirect:/shownews?newsId=" + newsId;
    }
    @RequestMapping("/deletecomment")
    public String deleteComment(Integer commentId, HttpServletRequest request) {
        Integer newsId = (Integer) request.getSession().getAttribute("newsId");
        commentService.deleteComment(commentId);
        return "redirect:/shownews?newsId=" + newsId;
    }
}
