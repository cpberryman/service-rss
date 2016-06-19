package com.berryman.cp.rss.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Domain PoJo for an RSS entry
 *
 * @author cpberryman.
 */
@Component
public class RssEntry {

    private String title;
    private String url;
    private Integer feedId;
    private String feedUrl;
    private Date date;

    public String getTitle() {
        return title;
    }

    public RssEntry setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RssEntry setUrl(String url) {
        this.url = url;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public RssEntry setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public RssEntry setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
        return this;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public RssEntry setFeedId(Integer feedId) {
        this.feedId = feedId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RssEntry rssEntry = (RssEntry) o;

        return new EqualsBuilder()
                .append(title, rssEntry.title)
                .append(url, rssEntry.url)
                .append(feedId, rssEntry.feedId)
                .append(feedUrl, rssEntry.feedUrl)
                .append(date, rssEntry.date)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(url)
                .append(feedId)
                .append(feedUrl)
                .append(date)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("url", url)
                .append("feedId", feedId)
                .append("feedUrl", feedUrl)
                .append("date", date)
                .toString();
    }

}
