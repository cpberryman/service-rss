package com.berryman.cp.rss.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Domain PoJo for an RSS feed.
 *
 * @author cpberryman.
 */
@Component
public class RssFeed implements Serializable {

    @Id
    private Integer id;
    private String title;
    private String httpUrl;
    private String rssUrl;
    private List<String> categories;
    private List<RssEntry> rssEntries;

    public Integer getId() {
        return id;
    }

    public RssFeed setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RssFeed setTitle(String title) {
        this.title = title;
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

    public List<String> getCatagories() {
        return categories;
    }

    public RssFeed setCatagories(List<String> categories) {
        this.categories = categories;
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
                .append(title, rssFeed.title)
                .append(httpUrl, rssFeed.httpUrl)
                .append(rssUrl, rssFeed.rssUrl)
                .append(categories, rssFeed.categories)
                .append(rssEntries, rssFeed.rssEntries)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(title)
                .append(httpUrl)
                .append(rssUrl)
                .append(categories)
                .append(rssEntries)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("httpUrl", httpUrl)
                .append("rssUrl", rssUrl)
                .append("categories", categories)
                .append("rssEntries", rssEntries)
                .toString();
    }

}
