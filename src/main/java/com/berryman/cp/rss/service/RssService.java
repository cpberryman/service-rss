package com.berryman.cp.rss.service;

import com.berryman.cp.rss.model.RssFeed;
import com.berryman.cp.rss.model.RssUrl;

import java.util.List;

/**
 * Interface for implementations of an RSS web service
 *
 * @author cpberryman.
 */
public interface RssService {

    RssUrl addRssUrl(RssUrl rssUrl);

    List<RssUrl> retrieveAllRssUrls();

    List<RssFeed> retrieveEntriesForAllFeedsByNumber(Integer number);

    RssFeed retrieveEntriesForFeedByNumber(String rssFeedName, Integer number);

}

