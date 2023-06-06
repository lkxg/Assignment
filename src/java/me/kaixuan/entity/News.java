package me.kaixuan.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class News {
    private Integer nId;
    private String title;
    private String content;
    private String cover;
    private String abstracts;
    private String reprint;
    private Integer authorId;
    private Timestamp publishTime;
    private Integer statusId;
    private Integer categoryId;
    private List<Comment> comments;
    private User user;

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getnId() {
        return nId;
    }

    public void setnId(Integer nId) {
        this.nId = nId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getReprint() {
        return reprint;
    }

    public void setReprint(String reprint) {
        this.reprint = reprint;
    }

    @Override
    public String toString() {
        return "News{" +
                "nId=" + nId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", cover='" + cover + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", reprint='" + reprint + '\'' +
                ", authorId=" + authorId +
                ", publishTime=" + publishTime +
                ", statusId=" + statusId +
                ", categoryId=" + categoryId +
                ", comments=" + comments +
                ", user=" + user +
                '}';
    }
}
