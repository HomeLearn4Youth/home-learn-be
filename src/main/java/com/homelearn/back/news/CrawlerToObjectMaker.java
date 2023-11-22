package com.homelearn.back.news;

import com.homelearn.back.house.ApartMapper;
import com.homelearn.back.house.dto.AddApartImgParam;
import com.homelearn.back.house.entity.HouseInfo;
import com.homelearn.back.house.entity.HouseJoinLike;
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
public class CrawlerToObjectMaker {
    private String naverClientId;
    private String naverClientSecret;
    private WebClient webClient;
    private ApartMapper apartMapper;
    private static final String DEFAULT_APART_IMG_LINK = "https://img.freepik.com/free-photo/3d-view-of-house-model_23-2150761178.jpg?w=1380&t=st=1700658708~exp=1700659308~hmac=003edb3bdfc42e0f43d0280f159c13a2f8e6a48d463a495166541e08bc9ff2c6";

    @Autowired
    public CrawlerToObjectMaker(
            @Value(value = "${dummy.naver-client-id}") String naverClientId,
            @Value(value = "${dummy.naver-client-secret}") String naverClientSecret,
            ApartMapper apartMapper) {
        this.naverClientId = naverClientId;
        this.naverClientSecret = naverClientSecret;
        this.apartMapper = apartMapper;
        this.webClient = WebClient.builder()
                            .baseUrl("https://openapi.naver.com")
                            .defaultHeader("X-Naver-Client-Id", naverClientId)
                            .defaultHeader("X-Naver-Client-Secret", naverClientSecret)
                            .build();
    }
    public NaverImgInputSpec getNaverImgs(HouseJoinLike house){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/image")
                        .queryParam("query", getSearchParam(house.getDong(), house.getApartmentName()))
                        .queryParam("start", 1)
                        .queryParam("display", 1)
                        .build())
                .retrieve()
                .bodyToMono(NaverImgInputSpec.class)
                .block();
    }
    public String getImg(HouseJoinLike house){
        String link = "";
        try {
            link = getNaverImgs(house).getItems().get(0).getLink();
        }catch (NullPointerException e){
            link = DEFAULT_APART_IMG_LINK;
        } catch (IndexOutOfBoundsException e) {
            link = DEFAULT_APART_IMG_LINK;
        }
        if (link==null) link = DEFAULT_APART_IMG_LINK;
        apartMapper.addAptImg(AddApartImgParam.builder()
                        .aptCode(house.getAptCode())
                        .aptImg(link)
                        .build());
        return link;
    }

    public NaverNewsInput getNaverNews(NewsInputSpec inputSpec, HouseInfo houseInfo){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/news")
                        .queryParam("query", getSearchParam(houseInfo.getDong(), houseInfo.getApartmentName()))
                        .queryParam("start", inputSpec.getStartIndex()+1)
                        .queryParam("display", inputSpec.getCount())
                        .build())
                .retrieve()
                .bodyToMono(NaverNewsInput.class)
                .block();
    }

    public List<NewsOutputSpec> getNews(NewsInputSpec inputSpec, HouseInfo houseInfo){

        NaverNewsInput input = getNaverNews(inputSpec, houseInfo);

        List<NaverNews> items = input.getItems();
        List<NewsOutputSpec> output = new ArrayList<NewsOutputSpec>();
        for (NaverNews item : items) {
            NewsSite siteData = NewsCrawler.getThumbnailAndSiteName(item.getOriginallink());
            output.add(NewsOutputSpec.builder()
                    .title(item.getTitle().replaceAll("&quot;", "\"").replaceAll("<[^>]*>", ""))
                    .link(item.getLink())
                    .description(item.getDescription().replaceAll("<[^>]*>",""))
                    .pubDate(item.getPubDate())
                    .mediaCompany(siteData.getSiteName())
                    .thumbnailLink(siteData.getThumnail())
                    .build());
        }
        return output;
    }

    private String getSearchParam(
                             String dong,
                             String apartName
                             ){
        if (dong==null || apartName==null) return "부동산";
        return dong+" "+apartName+" 부동산";
    }
}
