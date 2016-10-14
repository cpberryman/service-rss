package com.berryman.cp.rss.model;

import org.springframework.stereotype.Component;

import javax.inject.Singleton;

/**
 * Builder class for rss urls instances
 *
 * @author cpberryman.
 */
@Component
@Singleton
public class RssUrlBuilder {

    private String id;
    private String name;
    private String url;

    public RssUrlBuilder id(String id) {
        this.id = id;
        return this;
    }

    public RssUrlBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RssUrlBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RssUrl build() {
        return new RssUrl(id, name, url);
    }

}
