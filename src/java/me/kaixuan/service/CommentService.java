package me.kaixuan.service;

import me.kaixuan.entity.Comment;

import java.sql.Timestamp;
import java.util.List;

public interface CommentService {
    void addComment(Comment comment);

    List<Comment> selectCommentByNewsId(Integer newsId);

    void deleteComment(Integer commentId);
}
