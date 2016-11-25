package com.berryman.cp.rss.dao.impl;

import com.berryman.cp.rss.dao.HttpRssDao;
import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssEntryBuilder;
import com.berryman.cp.rss.model.RssUrl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
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

    private RssUrl rssUrl;

    private SyndFeed syndFeed;

    @Override
    public HttpRssDao setRssUrl(RssUrl rssUrl) {
        this.rssUrl = rssUrl;
        this.syndFeed = this.getSyndFeed();
        return this;
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
            rssEntries.add(
                    rssEntryBuilder
                    .title(syndEntry.getTitle())
                    .url(syndEntry.getUri())
                    .date(syndEntry.getPublishedDate())
                    .build()
            );
        }

        return rssEntries;

    }

    private SyndFeed getSyndFeed() {
        SyndFeed syndFeed = null;
        try {
            URL feedUrl = new URL(rssUrl.getUrl());
            SyndFeedInput syndFeedInput = new SyndFeedInput();
            syndFeed = rssUrl.getUrl().contains("https") ?
                    syndFeedInput.build(new XmlReader(asHttpsRequest(feedUrl))) :
                    syndFeedInput.build(new XmlReader(feedUrl));
        } catch (MalformedURLException ex) {
            LOGGER.info("Bad feed url: ", ex);
        } catch (IOException ex) {
            LOGGER.info("IO error when reading in feed XML: ", ex);
        } catch (FeedException ex) {
            LOGGER.info("Bad feed: ", ex);
        }
        return syndFeed;
    }

    private HttpURLConnection asHttpsRequest(URL feedUrl) {
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
