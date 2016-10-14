package com.berryman.cp.rss.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain PoJo for an RSS entry
 *
 * @author cpberryman.
 */
@Component
public class RssEntry implements Serializable {

    private String title;
    private String url;
    private Date date;

    public RssEntry(RssEntryBuilder rssEntryBuilder) {
        this.title = rssEntryBuilder.getTitle();
        this.url = rssEntryBuilder.getUrl();
        this.date = rssEntryBuilder.getDate();
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RssEntry rssEntry = (RssEntry) o;

        return new EqualsBuilder()
                .append(title, rssEntry.title)
                .append(url, rssEntry.url)
                .append(date, rssEntry.date)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(url)
                .append(date)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("url", url)
                .append("date", date)
                .toString();
    }

}
