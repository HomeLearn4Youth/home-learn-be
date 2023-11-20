package com.homelearn.back.news;

import com.homelearn.back.news.dto.NaverNews;
import com.homelearn.back.news.dto.NewsInputSpec;
import com.homelearn.back.news.dto.NewsOutputSpec;

import java.util.List;

public interface NewsService {
    List<NewsOutputSpec> searchNews (NewsInputSpec inputSpec);
}
