package com.homelearn.back.news;

import com.homelearn.back.news.dto.NewsSite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class NewsCrawler {

    private static final String THUMB_TAG = "meta[property='og:image']";
    private static final String SITE_NAME_TAG = "meta[property='og:site_name']";
    private static final String ATTRIBUTE = "content";

    public static NewsSite getThumbnailAndSiteName(String url) {

        String thumnail = "";
        String siteName = "";
        try {
            Document document = Jsoup.connect(url).get();

            Element thumbEle = document.selectFirst(THUMB_TAG);
            Element siteNameEle = document.selectFirst(SITE_NAME_TAG);

            thumnail = thumbEle.attr(ATTRIBUTE);
            siteName = siteNameEle.attr(ATTRIBUTE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new NewsSite(thumnail, siteName);
    }
}
