package com.berryman.cp.rss.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * POJO to store rss url information
 *
 * @author cpberryman.
 */
public class RssUrl implements Serializable {

    @Id
    private String id;
    private String name;
    private String url;

    public RssUrl(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RssUrl rssUrl = (RssUrl) o;

        return new EqualsBuilder()
                .append(id, rssUrl.id)
                .append(name, rssUrl.name)
                .append(url, rssUrl.url)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(url)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "RssUrl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
