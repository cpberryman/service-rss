package com.berryman.cp.rss.service;

import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssFeed;

import java.util.List;

/**
 * Interface for implementations of an RSS web service
 *
 * @author cpberryman.
 */
public interface RssService {

    List<RssEntry> listAllEntries();  //all entries listed

    List<RssEntry> listEntriesByNumber(Integer number);  //entries listed for all feeds by number

    List<RssEntry> listEntriesByFeed(RssFeed rssFeed);  //all entries listed for a feed

    List<RssEntry> listEntriesForFeedByNumber(RssFeed rssFeed, Integer number); //a given number of entries for a given feed

    void addFeed(String url);

    void deleteFeed(RssFeed rssFeed);

}

