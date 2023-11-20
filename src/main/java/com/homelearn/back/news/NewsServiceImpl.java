package com.homelearn.back.news;

import com.homelearn.back.news.dto.NaverNews;
import com.homelearn.back.news.dto.NewsInputSpec;
import com.homelearn.back.news.dto.NewsOutputSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsMaker newsMaker;
    @Override
    public List<NaverNews> searchNews(NewsInputSpec inputSpec) {
        List<NaverNews> items = newsMaker.getNaverNews(inputSpec).getItems();
        System.out.println(items);
        return items;
    }
}
