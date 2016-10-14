package com.berryman.cp.rss.model;

import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.Date;

/**
 * Builder class for RSS entries
 *
 * @author cpberryman.
 */
@Component
@Singleton
public class RssEntryBuilder {

    private String title;
    private String url;
    private Date date;

    public RssEntryBuilder title(String title) {
        this.title = title;
        return this;
    }

    public RssEntryBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RssEntryBuilder date(Date date) {
        this.date = date;
        return this;
    }

    public RssEntry build(){
        return new RssEntry(this);
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
}
