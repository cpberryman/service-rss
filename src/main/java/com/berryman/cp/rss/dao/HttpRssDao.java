package com.berryman.cp.rss.dao;

import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssFeed;

import java.util.List;

/**
 * Interface for implementations of a HTTP DAO which retrieves RSS entries
 *
 * @author cpberryman.
 */
public interface HttpRssDao {

    List<RssEntry> retrieveEntries(RssFeed feed);

}
