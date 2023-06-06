package me.kaixuan.mapper;

import me.kaixuan.entity.News;
import me.kaixuan.entity.User;

import java.util.List;

public interface NewsMapper {
    void insertNews(News news);

    List<News> selectAllNews();

    void updateNewsStatus(Integer id);

    void updateNewsStatusReject(Integer id);
}
