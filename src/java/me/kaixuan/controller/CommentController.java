package me.kaixuan.controller;

import me.kaixuan.entity.Comment;
import me.kaixuan.entity.User;
import me.kaixuan.service.CommentService;
import me.kaixuan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;
    /**
     * 添加评论
     *
     * @param comment 将前端输入封装为评论对象
     * @param request 用于获取当前用户
     * @return
     */
    @RequestMapping("/addcomment")
    public String addComment(Comment comment, HttpServletRequest request) {
        // 判断评论内容是否为空
        if (comment.getContent()==null||comment.getContent().equals("")){
            // 将错误信息存入session
            request.getSession().setAttribute("commentError", "评论内容不能为空");
            // 重定向到新闻详情页
            return "redirect:/shownews?newsId=" + (Integer) request.getSession().getAttribute("newsId");
        }
        // 从session获取当前新闻id
        Integer newsId = (Integer) request.getSession().getAttribute("newsId");
        // 将新闻id存入评论对象
       comment.setNewsId(newsId);
        // 获取当前时间
        Date now = new Date();
        // 创建SimpleDateFormat对象，指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式化日期
        String nowStr = sdf.format(now);
        // 将字符串转换为Timestamp类型
        Timestamp nowTime = Timestamp.valueOf(nowStr);
        // 将时间存入评论对象
        comment.setCommentTime(nowTime);
        // 从session获取当前用户
        User user = userService.selectUserByUsername((String) request.getSession().getAttribute("username"), (String) request.getSession().getAttribute("password"));
        // 将用户id存入评论对象
        comment.setCommenterId(user.getId());
        // 调用service层方法添加评论
        commentService.addComment(comment);
        // 重定向到新闻详情页
        return "redirect:/shownews?newsId=" + newsId;
    }
    /**
     * 删除评论
     *
     * @param commentId 评论id
     * @param request 用于获取当前新闻id
     * @return 重定向到新闻详情页
     */
    @RequestMapping("/deletecomment")
    public String deleteComment(Integer commentId, HttpServletRequest request) {
        // 从session获取当前新闻id
        Integer newsId = (Integer) request.getSession().getAttribute("newsId");
        // 调用service层方法删除评论
        commentService.deleteComment(commentId);
        // 重定向到新闻详情页
        return "redirect:/shownews?newsId=" + newsId;
    }
}
