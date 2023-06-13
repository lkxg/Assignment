package me.kaixuan.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Comment {
    private Integer id;
    private String content;
    private Integer newsId;
    private Integer commenterId;
    private Timestamp commentTime;
    private User user;
    private News news;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(Integer commenterId) {
        this.commenterId = commenterId;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", newsId=" + newsId +
                ", commenterId=" + commenterId +
                ", commentTime=" + commentTime +
                ", user=" + user +
                ", news=" + news +
                '}';
    }
}
