package com.berryman.cp.rss.dao.impl;

import com.berryman.cp.rss.dao.HttpRssDao;
import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssEntryBuilder;
import com.berryman.cp.rss.model.RssFeed;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link HttpRssDao}
 *
 * @author cpberryman.
 */
@Component
public class HttpRssDaoImpl implements HttpRssDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRssDaoImpl.class);

    @Autowired
    private RssEntryBuilder rssEntryBuilder;

    private RssFeed rssFeed;

    private SyndFeed syndFeed;

    @Override
    public HttpRssDao setFeed(RssFeed rssFeed) {
        this.rssFeed = rssFeed;
        this.syndFeed = this.getSyndFeed();
        return this;
    }

    @Override
    public RssFeed getRssFeed() {
        return this.rssFeed;
    }


    @Override
    public List<SyndCategory> retrieveFeedCategories() {
        return syndFeed.getCategories();
    }

    @Override
    public String retrieveFeedTitle(){
        return syndFeed.getTitle();
    }

    @Override
    public List<RssEntry> retrieveRssEntries() {

        List<RssEntry> rssEntries = new ArrayList<>();
        List<SyndEntry> syndEntries = syndFeed.getEntries();

        for (SyndEntry syndEntry : syndEntries) {
            rssEntries.add(rssEntryBuilder.buildRssEntry(syndEntry, rssFeed));
        }

        return rssEntries;

    }

    private SyndFeed getSyndFeed() {
        SyndFeed syndFeed = null;
        try {
            URL feedUrl = new URL(rssFeed.getRssUrl());
            SyndFeedInput syndFeedInput = new SyndFeedInput();
            syndFeed = rssFeed.getRssUrl().contains("https") ?
                    syndFeedInput.build(new XmlReader(asHttpRequest(feedUrl))) :
                    syndFeedInput.build(new XmlReader(feedUrl));
        } catch (MalformedURLException ex) {
            LOGGER.debug("Bad feed url: ", ex);
        } catch (IOException ex) {
            LOGGER.debug("IO error when reading in feed XML: ", ex);
        } catch (FeedException ex) {
            LOGGER.debug("Bad feed: ", ex);
        }
        return syndFeed;
    }

    private HttpURLConnection asHttpRequest(URL feedUrl) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) feedUrl.openConnection();
            String encoding = new BASE64Encoder().encode("username:password".getBytes());
            httpURLConnection.setRequestProperty("Authentication", "Basic " + encoding);
        } catch (IOException ex ){
            LOGGER.debug("Error with HTTP request: ", ex);
        }
        return httpURLConnection;
    }

}
