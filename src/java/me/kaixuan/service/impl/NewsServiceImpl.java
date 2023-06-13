package me.kaixuan.service.impl;

import me.kaixuan.entity.News;
import me.kaixuan.entity.User;
import me.kaixuan.mapper.NewsMapper;
import me.kaixuan.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsMapper;
    @Override
    public void insertNews(News news) {
        newsMapper.insertNews(news);
    }

    @Override
    public List<News> selectAllNews() {
        return  newsMapper.selectAllNews();
    }

    @Override
    public void updateNewsStatus(Integer id) {
        newsMapper.updateNewsStatus(id);
    }

    @Override
    public void updateNewsStatusReject(Integer id) {
        newsMapper.updateNewsStatusReject(id);
    }

    @Override
    public void deleteNews(Integer id) {
        newsMapper.deleteNews(id);
    }

    @Override
    public List<News> selectNewsByAuthorId(Integer id) {
        return newsMapper.selectNewsByAuthorId(id);
    }

    @Override
    public News selectNewsById(Integer newsId) {
        return newsMapper.selectNewsById(newsId);
    }

    @Override
    public List<News> selectNewsByCategoryId(Integer i) {
        return newsMapper.selectNewsByCategoryId(i);
    }

    @Override
    public List<News> selectNews(String search) {
        return newsMapper.selectNews(search);
    }

}
