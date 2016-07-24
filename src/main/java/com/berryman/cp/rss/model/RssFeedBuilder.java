package com.berryman.cp.rss.model;

import com.berryman.cp.rss.dao.HttpRssDao;
import com.rometools.rome.feed.synd.SyndCategory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for RSS feeds
 *
 * @author cpberryman.
 */
@Singleton
public class RssFeedBuilder {

    private static Integer id = 0;

    @Autowired
    private HttpRssDao httpRssDao;

    public RssFeed buildRssFeed(String url) {
        id++;
        RssFeed rssFeed = new RssFeed();
        rssFeed.setId(id).setRssUrl(url);
        httpRssDao.setFeed(rssFeed);
        rssFeed.setTitle(httpRssDao.retrieveFeedTitle())
                .setCatagories(categoriesAsString(httpRssDao.retrieveFeedCategories()))
                .setRssEntries(httpRssDao.retrieveRssEntries());
        return rssFeed;
    }

    private List<String> categoriesAsString(List<SyndCategory> syndCategories) {
        List<String> categories = new ArrayList<>();
        for(SyndCategory syndCategory : syndCategories) {
            categories.add(syndCategory.getName());
        }
        return categories;
    }

}
