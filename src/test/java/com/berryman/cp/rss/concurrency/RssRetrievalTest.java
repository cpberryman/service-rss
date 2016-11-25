package com.berryman.cp.rss.concurrency;

import com.berryman.cp.rss.config.ApplicationConfig;
import com.berryman.cp.rss.dao.HttpRssDao;
import com.berryman.cp.rss.dao.impl.HttpRssDaoImpl;
import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssFeed;
import com.berryman.cp.rss.model.RssFeedBuilder;
import com.berryman.cp.rss.model.RssUrl;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static com.berryman.cp.rss.util.Constants.RSS_ENTRY_CACHE;
import static com.berryman.cp.rss.util.TestUtil.getTestRssEntries;
import static com.berryman.cp.rss.util.TestUtil.getTestRssUrls;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link RssRetrieval}
 *
 * @author cpberryman.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class RssRetrievalTest {

    Cache cache;

    @Autowired
    TaskExecutor taskExecutor;

    private RssFeedBuilder builder = new RssFeedBuilder();

    HttpRssDao httpRssDao;

    RssRetrieval rssRetrieval;

    @Before
    public void setUp() {
        rssRetrieval = new RssRetrieval();
        CacheManager cacheManager = CacheManager.getInstance();
        cache = cacheManager.getCache(RSS_ENTRY_CACHE);
        httpRssDao = mock(HttpRssDaoImpl.class);
        when(httpRssDao.retrieveFeedTitle()).thenReturn("foo");
        when(httpRssDao.retrieveRssEntries()).thenReturn(getTestRssEntries());
        ReflectionTestUtils.setField(rssRetrieval, "httpRssDao", httpRssDao);
        ReflectionTestUtils.setField(rssRetrieval, "rssEntryCache", cache);
        ReflectionTestUtils.setField(rssRetrieval, "taskExecutor", taskExecutor);
        ReflectionTestUtils.setField(rssRetrieval, "builder", builder);
    }

    @Test
    public void fireCorrectly() {

        //given...
        RssUrl rssUrl = getTestRssUrls().get(0);

        //when...
        rssRetrieval.fire(rssUrl);

        //then...
        verify(httpRssDao, times(1)).setRssUrl(any(RssUrl.class));
        verify(httpRssDao, times(1)).retrieveRssEntries();
        verify(httpRssDao, times(1)).retrieveFeedTitle();
        verifyNoMoreInteractions(httpRssDao);

    }

}
