package com.berryman.cp.rss.util;

import com.berryman.cp.rss.model.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Helper for unit tests
 *
 * @author cpberryman.
 */
public class TestUtil {

    public static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());

    static final RssUrlBuilder urlBuilder = new RssUrlBuilder();
    static final RssEntryBuilder entryBuilder = new RssEntryBuilder();
    static final RssFeedBuilder feedBuilder = new RssFeedBuilder();

    public static List<RssUrl> getTestRssUrls() {
        List<RssUrl> rssUrls = new ArrayList<>();

        RssUrl rssUrl = urlBuilder.id("test1")
                .name("foo")
                .url("foo.com")
                .build();

        rssUrls.add(rssUrl);

        RssUrl rssUrl1 = urlBuilder.id("test2")
                .name("bar")
                .url("bar.com")
                .build();

        rssUrls.add(rssUrl1);

        return rssUrls;
    }

    public static List<RssFeed> getTestRssFeeds() {
        List<RssFeed> rssFeeds = new ArrayList<>();

        RssFeed rssFeed = feedBuilder.title("foo title")
                .rssUrl("foo.com")
                .rssEntries(getTestRssEntries())
                .build();

        rssFeeds.add(rssFeed);

        RssFeed rssFeed1 = feedBuilder.title("bar title")
                .rssUrl("bar.com")
                .rssEntries(getTestRssEntries())
                .build();

        rssFeeds.add(rssFeed1);

        return rssFeeds;
    }

    public static List<RssEntry> getTestRssEntries() {
        List<RssEntry> rssEntries = new ArrayList<>();

        RssEntry entry = entryBuilder.title("foo entry")
                .date(new Date())
                .url("fooentry.com")
                .build();

        rssEntries.add(entry);

        RssEntry entry1 = entryBuilder.title("bar entry")
                .url("barentry.com")
                .date(new Date())
                .build();

        rssEntries.add(entry1);

        return  rssEntries;
    }

}
