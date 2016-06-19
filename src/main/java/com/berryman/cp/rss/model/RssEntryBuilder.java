package com.berryman.cp.rss.model;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

/**
 * Builder class for RSS entries
 *
 * @author cpberryman.
 */
//@Component
@Singleton
public class RssEntryBuilder {

    public RssEntry buildRssEntry(SyndEntry syndEntry, RssFeed rssFeed) {
        return new RssEntry().setTitle(syndEntry.getTitle()).setUrl(syndEntry.getUri()).setFeedId(rssFeed.getId())
                .setFeedUrl(rssFeed.getHttpUrl()).setDate(syndEntry.getPublishedDate());

    }

}
