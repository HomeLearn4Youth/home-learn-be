package com.homelearn.back.news;

import com.homelearn.back.house.ApartMapper;
import com.homelearn.back.house.exception.HouseErrorCode;
import com.homelearn.back.house.exception.HouseException;
import com.homelearn.back.news.dto.NaverNews;
import com.homelearn.back.news.dto.NewsInputSpec;
import com.homelearn.back.news.dto.NewsOutputSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.homelearn.back.house.exception.HouseErrorCode.*;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final CrawlerToObjectMaker newsMaker;
    private final ApartMapper apartMapper;
    @Override
    public List<NewsOutputSpec> searchNews(NewsInputSpec inputSpec) {
        return newsMaker.getNews(inputSpec, apartMapper.findApartByApartCode(inputSpec.getAptCode())
                .orElseThrow(()->new HouseException(NOT_EXISTS_HOUSE)));
    }
}
