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

    List<RssEntry> listEntriesByFeed(RssFeed rssFeed);

    List<RssEntry> listEntriesByNumber(RssFeed rssFeed, Integer number);


}

