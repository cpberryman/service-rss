package com.berryman.cp.rss.model;

import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.List;

/**
 * Builder class for RSS feeds
 *
 * @author cpberryman.
 */
@Component
@Singleton
public class RssFeedBuilder {

    private String title;
    private String rssUrl;
    private List<RssEntry> rssEntries;

    public RssFeedBuilder title(String title) {
        this.title = title;
        return this;
    }

    public RssFeedBuilder rssUrl(String rssUrl) {
        this.rssUrl = rssUrl;
        return this;
    }

    public RssFeedBuilder rssEntries(List<RssEntry> rssEntries) {
        this.rssEntries = rssEntries;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RssFeed build() {
        return new RssFeed(this);
    }

    public String getRssUrl() {
        return rssUrl;
    }

    public List<RssEntry> getRssEntries() {
        return rssEntries;
    }
}

