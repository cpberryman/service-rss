package com.berryman.cp.rss.dao;

import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssFeed;
import com.rometools.rome.feed.synd.SyndCategory;

import java.util.List;

/**
 * Interface for implementations of a HTTP DAO which retrieves RSS entries
 *
 * @author cpberryman.
 */
public interface HttpRssDao {

    HttpRssDao setFeed(RssFeed rssFeed);

    RssFeed getRssFeed();

    List<SyndCategory> retrieveFeedCategories();

    String retrieveFeedTitle();

    List<RssEntry> retrieveRssEntries();



}
