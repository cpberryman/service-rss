package com.berryman.cp.rss.dao;

import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssFeed;
import com.berryman.cp.rss.model.RssUrl;
import com.sun.syndication.feed.synd.SyndCategory;

import java.util.List;

/**
 * Interface for implementations of a HTTP DAO which retrieves RSS entries
 *
 * @author cpberryman.
 */
public interface HttpRssDao {

    HttpRssDao setRssUrl(RssUrl rssUrl);

    String retrieveFeedTitle();

    List<RssEntry> retrieveRssEntries();


}
