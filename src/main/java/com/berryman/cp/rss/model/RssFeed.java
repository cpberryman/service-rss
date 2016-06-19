package com.berryman.cp.rss.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Domain PoJo for an RSS feed.
 *
 * @author cpberryman.
 */
@Component
public class RssFeed {

    @Id
    private Integer id;
    private String name;
    private String httpUrl;
    private String rssUrl;
    private List<RssEntry> rssEntries;

    public Integer getId() {
        return id;
    }

    public RssFeed setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RssFeed setName(String name) {
        this.name = name;
        return this;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public RssFeed setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
        return this;
    }

    public String getRssUrl() {
        return rssUrl;
    }

    public RssFeed setRssUrl(String rssUrl) {
        this.rssUrl = rssUrl;
        return this;
    }

    public List<RssEntry> getRssEntries() {
        return rssEntries;
    }

    public RssFeed setRssEntries(List<RssEntry> rssEntries) {
        this.rssEntries = rssEntries;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RssFeed rssFeed = (RssFeed) o;

        return new EqualsBuilder()
                .append(id, rssFeed.id)
                .append(name, rssFeed.name)
                .append(httpUrl, rssFeed.httpUrl)
                .append(rssUrl, rssFeed.rssUrl)
                .append(rssEntries, rssFeed.rssEntries)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(httpUrl)
                .append(rssUrl)
                .append(rssEntries)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("httpUrl", httpUrl)
                .append("rssUrl", rssUrl)
                .append("rssEntries", rssEntries)
                .toString();
    }

}
