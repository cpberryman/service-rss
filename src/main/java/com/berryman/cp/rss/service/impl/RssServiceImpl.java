package com.berryman.cp.rss.service.impl;

import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssFeed;
import com.berryman.cp.rss.model.RssFeedBuilder;
import com.berryman.cp.rss.repository.RssRepository;
import com.berryman.cp.rss.service.RssService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Implementation of the {@link RssService} interface
 *
 * @author cpberryman.
 */
public class RssServiceImpl implements RssService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RssServiceImpl.class);

    @Autowired
    private Cache rssEntryCache;

    @Autowired
    private RssRepository rssRepository;

    @Autowired
    private RssFeedBuilder rssFeedBuilder;

    public List<RssEntry> listAllEntries() {
        List<RssEntry> rssEntries = new ArrayList<>();
        Map<Object, Element> rssFeeds = rssEntryCache.getAll(rssEntryCache.getKeys());
        for (Map.Entry<Object, Element> entry : rssFeeds.entrySet()) {
            Element value = entry.getValue();
            for(RssEntry rssEntry : (RssEntry[]) value.getObjectValue()) {
                rssEntries.add(rssEntry);
            }
        }
        return rssEntries;
    }

    public List<RssEntry> listEntriesByNumber(Integer number) {
        List<RssEntry> rssEntries = new ArrayList<>();
        for(RssFeed rssFeed : rssRepository.findAllRssFeeds()) {
            rssEntries.addAll(getEntriesByNumber(rssFeed, number));
        }
        return rssEntries;
    }

    public List<RssEntry> listEntriesByFeed(RssFeed rssFeed) {
        List<RssEntry> rssEntries = new ArrayList<>();
        RssFeed rssFeedTemp = (RssFeed) rssEntryCache.get(rssFeed.getId()).getObjectValue();
        rssEntries.addAll(rssFeedTemp.getRssEntries());
        return rssEntries;
    }

    public List<RssEntry> listEntriesForFeedByNumber(RssFeed rssFeed, Integer number) {
        return getEntriesByNumber(rssFeed, number);
    }

    public void addFeed(String url) {
        rssRepository.insertRssFeed(rssFeedBuilder.buildRssFeed(url));
    }

    public void deleteFeed(RssFeed rssFeed) {
        rssRepository.deleteRssFeed(rssFeed);
    }

    private List<RssEntry> getEntriesByNumber(RssFeed rssFeed, Integer number) {
        List<RssEntry> rssEntries = sorted(listEntriesByFeed(rssFeed));
        List<RssEntry> rssEntriesByNumber = new ArrayList<>();
        int counter = 0;
        while(counter < number) {
            rssEntriesByNumber.add(rssEntries.get(counter));
            counter++;
        }
        return rssEntriesByNumber;
    }

    public static List<RssEntry> sorted(List rssEntries) {
        Collections.sort(rssEntries);
        Collections.reverse(rssEntries);
        return rssEntries;
    }


}
