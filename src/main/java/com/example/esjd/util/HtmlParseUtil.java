package com.example.esjd.util;

import com.example.esjd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtil {
    public List<Content> parseJD(String keywords) throws IOException {
        String url = "https://search.jd.com/Search?keywords=" + keywords;
        Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36").timeout(30000).get();

        System.out.println(document.html());
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> goodsList = new ArrayList<>();

        for (Element el : elements) {

                String img = el.getElementsByTag("img").eq(0).attr("source-data-lazy-img");
                String price = el.getElementsByClass("p-price").eq(0).text();
                String title = el.getElementsByClass("p-name").eq(0).text();

                goodsList.add(new Content(img, title, price));


        }
        return goodsList;
    }
}
