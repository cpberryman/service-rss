package com.berryman.cp.rss.service.impl;

import com.berryman.cp.rss.config.ApplicationConfig;
import com.berryman.cp.rss.model.RssFeed;
import com.berryman.cp.rss.model.RssFeedBuilder;
import com.berryman.cp.rss.model.RssUrl;
import com.berryman.cp.rss.repository.RssUrlRepository;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.berryman.cp.rss.util.TestUtil.getTestRssFeeds;
import static com.berryman.cp.rss.util.TestUtil.getTestRssUrls;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link RssServiceImpl}
 *
 * @author cpberryman.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class RssServiceImplTest {

    RssServiceImpl service;

    RssUrlRepository rssRepositoryMock;

    RssFeedBuilder rssFeedBuilder;

    @Autowired
    Cache cache;

    @Before
    public void setUp() {
        service = new RssServiceImpl();
        rssRepositoryMock = mock(RssUrlRepository.class);
        ReflectionTestUtils.setField(service, "rssRepository", rssRepositoryMock);
        ReflectionTestUtils.setField(service, "cache", cache);
        rssFeedBuilder = new RssFeedBuilder();
        ReflectionTestUtils.setField(service, "rssFeedBuilder", rssFeedBuilder);
    }


    @Test
    public void addRssUrlCorrectly() {

        //given...
        RssUrl rssUrl = getTestRssUrls().get(0);
        when(rssRepositoryMock.insert(any(RssUrl.class))).thenReturn(rssUrl);

        //when...
        RssUrl rssUrlActual = service.addRssUrl(rssUrl);

        //then...
        assertEquals(rssUrlActual, rssUrl);
        verify(rssRepositoryMock, times(1)).insert(any(RssUrl.class));
        verify(rssRepositoryMock, times(1)).findById(anyString());
        verifyNoMoreInteractions(rssRepositoryMock);

    }

    @Test
    public void retrieveAllRssUrlsCorrectly() {

        //given..
        when(rssRepositoryMock.findAll()).thenReturn(getTestRssUrls());

        //when...
        List<RssUrl> rssUrlsActual = service.retrieveAllRssUrls();

        //then...
        assertEquals(rssUrlsActual, getTestRssUrls());
        verify(rssRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(rssRepositoryMock);

    }

    @Test
    public void retrieve_one_entry_forAllFeedsByNumberCorrectly() {

        //given..
        cache.put(new Element(getTestRssFeeds().get(0).getTitle(), getTestRssFeeds().get(0)));
        cache.put(new Element(getTestRssFeeds().get(1).getTitle(), getTestRssFeeds().get(1)));

        //when...
        List<RssFeed> actual = service.retrieveEntriesForAllFeedsByNumber(1);


        //then...
        assertEquals(actual.get(0).getRssEntries().size(), 1);
        assertEquals(actual.get(1).getRssEntries().size(), 1);


    }

    @Test
    public void retrieve_more_than_one_entry_forAllFeedsByNumberCorrectly() {

        //given..
        cache.put(new Element(getTestRssFeeds().get(0).getTitle(), getTestRssFeeds().get(0)));
        cache.put(new Element(getTestRssFeeds().get(1).getTitle(), getTestRssFeeds().get(1)));

        //when...
        List<RssFeed> actual2 = service.retrieveEntriesForAllFeedsByNumber(2);

        //then...
        assertEquals(actual2.get(0).getRssEntries().size(), 2);
        assertEquals(actual2.get(1).getRssEntries().size(), 2);
    }

    @Test
    public void retrieve_zero_entries_forAllFeedsByNumberCorrectly() {

        //given..
        cache.put(new Element(getTestRssFeeds().get(0).getTitle(), getTestRssFeeds().get(0)));
        cache.put(new Element(getTestRssFeeds().get(1).getTitle(), getTestRssFeeds().get(1)));

        //when...
        List<RssFeed> actual0 = service.retrieveEntriesForAllFeedsByNumber(0);

        //then...
        assertEquals(actual0.size(), 0);

    }

    @Test
    public void retrieveEntriesForFeedByNumberCorrectlyWithStringArg() {

        //given..
        cache.put(new Element(getTestRssFeeds().get(0).getTitle(), getTestRssFeeds().get(0)));
        cache.put(new Element(getTestRssFeeds().get(1).getTitle(), getTestRssFeeds().get(1)));

        //when...
        RssFeed rssFeed = service.retrieveEntriesForFeedByNumber(getTestRssFeeds().get(0).getTitle(), 1);

        //then...
        assertEquals(rssFeed.getRssEntries().size(), 1);

    }

}
