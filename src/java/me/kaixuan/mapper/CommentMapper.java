package me.kaixuan.mapper;

import me.kaixuan.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface CommentMapper {
    void addComment(Comment comment);

    List<Comment> selectCommentByNewsId(Integer newsId);

    void deleteComment(Integer commentId);
}
