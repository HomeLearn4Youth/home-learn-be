package com.homelearn.back.news;

import com.homelearn.back.news.dto.NewsSite;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
@Slf4j
public class NewsCrawler {

    private static final String THUMB_TAG = "meta[property='og:image']";
    private static final String SITE_NAME_TAG = "meta[property='og:site_name']";
    private static final String ATTRIBUTE = "content";
    private static final String DEFAULT_NEWS_IMG_LINK = "https://blog.kakaocdn.net/dn/rw27p/btrasuelNNf/MwuludNcmEP2SmQFRywPl0/img.jpg";
    private static final String DEFAULT_SITE_NAME = "쌰피셜 10th";
    public static NewsSite getThumbnailAndSiteName(String url) {

        String thumnail = "";
        String siteName = "";
        try {
            Document document = Jsoup.connect(url).get();

            Element thumbEle = document.selectFirst(THUMB_TAG);
            Element siteNameEle = document.selectFirst(SITE_NAME_TAG);
            if (thumbEle==null&&siteNameEle!=null){
                siteName = siteNameEle.attr(ATTRIBUTE);
                return new NewsSite(DEFAULT_NEWS_IMG_LINK, siteName);
            } else if (thumbEle!=null&&siteNameEle==null){
                thumnail = thumbEle.attr(ATTRIBUTE);
                return new NewsSite(thumnail, DEFAULT_SITE_NAME);
            } else if (thumbEle==null&&siteNameEle==null) {
                return new NewsSite(DEFAULT_NEWS_IMG_LINK, DEFAULT_SITE_NAME);
            }
            thumnail = thumbEle.attr(ATTRIBUTE);
            siteName = siteNameEle.attr(ATTRIBUTE);
        } catch (Exception e) {
            log.error("크롤링 에러 !!!!",e);
            return new NewsSite(DEFAULT_NEWS_IMG_LINK, DEFAULT_SITE_NAME);
        }

        return new NewsSite(thumnail, siteName);
    }
}
