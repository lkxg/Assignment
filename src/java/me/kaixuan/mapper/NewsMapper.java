package me.kaixuan.mapper;

import me.kaixuan.entity.News;
import me.kaixuan.entity.User;

import java.util.List;

public interface NewsMapper {
    void insertNews(News news);

    List<News> selectAllNews();

    void updateNewsStatus(Integer id);

    void updateNewsStatusReject(Integer id);

    void deleteNews(Integer id);

    List<News> selectNewsByAuthorId(Integer id);

    News selectNewsById(Integer newsId);

    List<News> selectNewsByCategoryId(Integer i);

    List<News> selectNews(String search);
}
