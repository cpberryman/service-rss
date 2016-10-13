package com.berryman.cp.rss.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;

/**
 * POJO to store rss url information
 *
 * @author cpberryman.
 */
public class RssUrl {

    @Id
    private String id;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
