package com.berryman.cp.rss.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Domain PoJo for an RSS feed.
 *
 * @author cpberryman.
 */
@Component
@JsonRootName(value = "RssFeed")
public class RssFeed implements Serializable {

    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "rssUrl")
    private String rssUrl;
    @JsonProperty(value = "rssEntries")
    private List<RssEntry> rssEntries;

    public RssFeed(RssFeedBuilder rssFeedBuilder) {
        this.title = rssFeedBuilder.getTitle();
        this.rssUrl = rssFeedBuilder.getRssUrl();
        this.rssEntries = rssFeedBuilder.getRssEntries();
    }

    public String getTitle() {
        return title;
    }

    public String getRssUrl() {
        return rssUrl;
    }

    public List<RssEntry> getRssEntries() {
        return rssEntries;
    }

    public void setRssEntries(List<RssEntry> rssEntries) {
        this.rssEntries = rssEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RssFeed rssFeed = (RssFeed) o;

        return new EqualsBuilder()
                .append(title, rssFeed.title)
                .append(rssUrl, rssFeed.rssUrl)
                .append(rssEntries, rssFeed.rssEntries)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(rssUrl)
                .append(rssEntries)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("rssUrl", rssUrl)
                .append("rssEntries", rssEntries)
                .toString();
    }

}
