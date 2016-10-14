package com.berryman.cp.rss.service.impl;

import com.berryman.cp.rss.model.*;
import com.berryman.cp.rss.repository.RssUrlRepository;
import com.berryman.cp.rss.service.RssService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Implementation of the {@link RssService} interface
 *
 * @author cpberryman.
 */
@Component
public class RssServiceImpl implements RssService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RssServiceImpl.class);

    @Autowired
    private Cache rssEntryCache;

    @Autowired
    private RssUrlRepository rssRepository;

    @Autowired
    private RssFeedBuilder rssFeedBuilder;

    @Override
    public RssUrl addRssUrl(RssUrl rssUrl) {
        rssRepository.insert(rssUrl);
        return rssUrl;
    }

    @Override
    public List<RssUrl> retrieveAllRssUrls() {
        return null;
    }

    @Override
    public List<RssFeed> retrieveEntriesForAllFeedsByNumber(Integer number) {
        return null;
    }

    @Override
    public RssFeed retrieveEntriesForFeedByNumber(String rssFeedName, Integer number) {
        return null;
    }

    @Override
    public RssFeed retrieveEntriesForFeedByNumber(RssFeed rssFeed, Integer number) {
        return null;
    }

//    @Override
//    public List<RssFeed> listFeeds() {
//        return rssRepository.findAll();
//    }
//
//    public List<RssEntry> listAllEntries() {
//        List<RssEntry> rssEntries = new ArrayList<>();
//        Map<Object, Element> rssFeeds = rssEntryCache.getAll(rssEntryCache.getKeys());
//        for (Map.Entry<Object, Element> entry : rssFeeds.entrySet()) {
//            Element value = entry.getValue();
//            for(RssEntry rssEntry : (RssEntry[]) value.getObjectValue()) {
//                rssEntries.add(rssEntry);
//            }
//        }
//        return rssEntries;
//    }
//
//    public List<RssEntry> listEntriesByNumber(Integer number) {
//        List<RssEntry> rssEntries = new ArrayList<>();
//        for(RssFeed rssFeed : rssRepository.findAll()) {
//            rssEntries.addAll(getEntriesByNumber(rssFeed, number));
//        }
//        return rssEntries;
//    }
//
//    public List<RssEntry> listEntriesByFeed(RssFeed rssFeed) {
//        List<RssEntry> rssEntries = new ArrayList<>();
//        RssFeed rssFeedTemp = (RssFeed) rssEntryCache.get(rssFeed.getId()).getObjectValue();
//        rssEntries.addAll(rssFeedTemp.getRssEntries());
//        return rssEntries;
//    }
//
//    public List<RssEntry> listEntriesForFeedByNumber(RssFeed rssFeed, Integer number) {
//        return getEntriesByNumber(rssFeed, number);
//    }
//
//
//
//
////    public void deleteFeed(String url) {
////        rssRepository.deleteRssFeedByUrl(url);
////    }
//
//    public List<RssFeed> retrieveFeedsByCategory(String category) {
//        List<RssFeed> rssFeeds = new ArrayList<>();
//        Map<Object, Element> rssFeedsAll = rssEntryCache.getAll(rssEntryCache.getKeys());
//        for (Map.Entry<Object, Element> entry : rssFeedsAll.entrySet()) {
//            Element value = entry.getValue();
//            RssFeed rssFeed = (RssFeed) value.getObjectValue();
//            List<String> categories = rssFeed.getCatagories();
//            for(String cat : categories) {
//                if(cat.equalsIgnoreCase(category))
//                rssFeeds.add(rssFeed);
//            }
//        }
//        return rssFeeds;
//    }
//
//
//
//    private List<RssEntry> getEntriesByNumber(RssFeed rssFeed, Integer number) {
//        List<RssEntry> rssEntries = sorted(listEntriesByFeed(rssFeed));
//        List<RssEntry> rssEntriesByNumber = new ArrayList<>();
//        int counter = 0;
//        while(counter < number) {
//            rssEntriesByNumber.add(rssEntries.get(counter));
//            counter++;
//        }
//        return rssEntriesByNumber;
//    }
//
//    public static List<RssEntry> sorted(List rssEntries) {
//        Collections.sort(rssEntries);
//        Collections.reverse(rssEntries);
//        return rssEntries;
//    }


}
