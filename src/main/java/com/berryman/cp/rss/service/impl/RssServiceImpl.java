package com.berryman.cp.rss.service.impl;

import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssFeed;
import com.berryman.cp.rss.model.RssFeedBuilder;
import com.berryman.cp.rss.model.RssUrl;
import com.berryman.cp.rss.repository.RssUrlRepository;
import com.berryman.cp.rss.service.RssService;
import com.mongodb.MongoException;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link RssService} interface
 *
 * @author cpberryman.
 */
@Component
public class RssServiceImpl implements RssService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RssServiceImpl.class);

    @Autowired
    private Cache cache;

    @Autowired
    private RssUrlRepository rssRepository;

    @Autowired
    private RssFeedBuilder rssFeedBuilder;

    @Override
    public RssUrl addRssUrl(RssUrl rssUrl) {
        if (rssRepository.findById(rssUrl.getId()) != null) {
            throw new MongoException("url exits : " + rssUrl.getId());
        }
        return rssRepository.insert(rssUrl);
    }

    @Override
    public List<RssUrl> retrieveAllRssUrls() {
        return rssRepository.findAll();
    }

    @Override
    public List<RssFeed> retrieveEntriesForAllFeedsByNumber(Integer number) {
        List<RssFeed> feeds = new ArrayList<>();

        if (number > 0) {
            for (RssFeed feed : listAllFeeds()) {
                feeds.add(asTemporaryFeed(feed, number));
            }
        }

        return feeds;
    }

    @Override
    public RssFeed retrieveEntriesForFeedByNumber(String rssFeedName, Integer number) {
        RssFeed feed = (RssFeed) cache.get(rssFeedName).getObjectValue();

        return asTemporaryFeed(feed, number);
    }

    private RssFeed asTemporaryFeed(RssFeed feed, Integer number) {
        RssFeed temp = rssFeedBuilder.title(feed.getTitle()).rssUrl(feed.getRssUrl()).build();
        temp.setRssEntries(getEntriesByNumber(feed, number));

        return temp;
    }

    private List<RssFeed> listAllFeeds() {
        List<RssFeed> feeds = new ArrayList<>();
        Map<Object, Element> rssFeeds = cache.getAll(cache.getKeys());
        for (Map.Entry<Object, Element> entry : rssFeeds.entrySet()) {
            feeds.add((RssFeed) entry.getValue().getObjectValue());
        }

        return feeds;
    }

//    public void deleteFeed(String url) {
//        rssRepository.deleteRssFeedByUrl(url);
//    }

    private List<RssEntry> getEntriesByNumber(RssFeed rssFeed, Integer number) {
        List<RssEntry> rssEntries = sorted(rssFeed.getRssEntries());
        List<RssEntry> rssEntriesByNumber = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            if (i < rssEntries.size())
                rssEntriesByNumber.add(rssEntries.get(i));
        }
        return rssEntriesByNumber;
    }

    public static List<RssEntry> sorted(List rssEntries) {
        Collections.sort(rssEntries);
        Collections.reverse(rssEntries);
        return rssEntries;
    }


}
