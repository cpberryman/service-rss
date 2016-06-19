package com.berryman.cp.rss.dao.impl;

import com.berryman.cp.rss.dao.HttpRssDao;
import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssEntryBuilder;
import com.berryman.cp.rss.model.RssFeed;
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

    @Override
    public List<RssEntry> retrieveRssEntries(RssFeed rssFeed) {

        List<RssEntry> rssEntries = new ArrayList<>();
        List<SyndEntryImpl> syndEntries = retrieveSyndEntries(rssFeed.getRssUrl());

        for (SyndEntryImpl syndEntry : syndEntries) {
            rssEntries.add(rssEntryBuilder.buildRssEntry(syndEntry, rssFeed));
        }

        return rssEntries;

    }

    private List<SyndEntryImpl> retrieveSyndEntries(String url) {

        List<SyndEntryImpl> syndEntries = new ArrayList<>();

        try {
            URL feedUrl = new URL(url);
            SyndFeedInput syndFeedInput = new SyndFeedInput();
            SyndFeed syndFeed = url.contains("https") ? syndFeedInput.build(new XmlReader(asHttpRequest(feedUrl))) : syndFeedInput.build(new XmlReader(feedUrl));

        } catch (MalformedURLException ex) {
            LOGGER.debug("Bad feed url: ", ex);
        } catch (IOException ex) {
            LOGGER.debug("IO error when reading in feed XML: ", ex);
        } catch (FeedException ex) {
            OGGER.debug("Bad feed: ", ex);
        }


    }

    private HttpURLConnection asHttpRequest(URL feedUrl) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) feedUrl.openConnection();
            String encoding = new BASE64Encoder().encode("username:password".getBytes());
            httpURLConnection.setRequestProperty("Authentication", "Basic " + encoding);
        } catch (IOException ex ){
            LOGGER.debug("Error woth HTTP request: ", ex);
        }
        return httpURLConnection;
    }




}
