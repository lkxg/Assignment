package me.kaixuan.service.impl;

import me.kaixuan.entity.Comment;
import me.kaixuan.mapper.CommentMapper;
import me.kaixuan.service.CommentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        commentMapper.addComment(comment);
    }

    @Override
    public List<Comment> selectCommentByNewsId(Integer newsId) {
        return commentMapper.selectCommentByNewsId(newsId);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentMapper.deleteComment(commentId);
    }
}
