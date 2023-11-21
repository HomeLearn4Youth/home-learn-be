package com.homelearn.back.news;

import com.homelearn.back.news.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class NewsMaker {
    private String naverClientId;
    private String naverClientSecret;
    private WebClient webClient;

    @Autowired
    public NewsMaker(@Value(value = "${dummy.naver-client-id}") String naverClientId,
                     @Value(value = "${dummy.naver-client-secret}") String naverClientSecret) {
        this.naverClientId = naverClientId;
        this.naverClientSecret = naverClientSecret;
        this.webClient = WebClient.builder()
                            .baseUrl("https://openapi.naver.com")
                            .defaultHeader("X-Naver-Client-Id", naverClientId)
                            .defaultHeader("X-Naver-Client-Secret", naverClientSecret)
                            .build();
    }

    public NaverNewsInput getNaverNews(NewsInputSpec inputSpec){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/news")
                        .queryParam("query", inputSpec.getSearchText())
                        .queryParam("start", inputSpec.getStartIndex()+1)
                        .queryParam("display", inputSpec.getCount())
                        .build())
                .retrieve()
                .bodyToMono(NaverNewsInput.class)
                .block();
    }

    public List<NewsOutputSpec> getNews(NewsInputSpec inputSpec){

        NaverNewsInput input = getNaverNews(inputSpec);

        List<NaverNews> items = input.getItems();
        List<NewsOutputSpec> output = new ArrayList<NewsOutputSpec>();
        for (NaverNews item : items) {
            NewsSite siteData = NewsCrawler.getThumbnailAndSiteName(item.getOriginallink());
            output.add(NewsOutputSpec.builder()
                    .title(item.getTitle())
                    .link(item.getLink())
                    .description(item.getDescription().replaceAll("<[^>]*>",""))
                    .pubDate(item.getPubDate())
                    .mediaCompany(siteData.getSiteName())
                    .thumbnailLink(siteData.getThumnail())
                    .build());
        }
        return output;
    }
}
