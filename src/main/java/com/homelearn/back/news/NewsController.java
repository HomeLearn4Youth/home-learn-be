package com.homelearn.back.news;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.news.dto.NaverNews;
import com.homelearn.back.news.dto.NewsInputSpec;
import com.homelearn.back.news.dto.NewsOutputSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    @GetMapping
    public ResponseEntity<MessageUtil<List<NewsOutputSpec>>> getNewsList(
            @ModelAttribute NewsInputSpec inputSpec
    ){
        return ResponseEntity.ok().body(MessageUtil.success(newsService.searchNews(inputSpec)));
    }
}
