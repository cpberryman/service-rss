package com.berryman.cp.rss.concurrency;

import com.berryman.cp.rss.dao.HttpRssDao;
import com.berryman.cp.rss.model.RssFeed;
import com.berryman.cp.rss.model.RssFeedBuilder;
import com.berryman.cp.rss.model.RssUrl;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Class for concurrent RSS entry retrieval
 *
 * @author cpberryman.
 */
@Component
public class RssRetrieval {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private Cache rssEntryCache;

    @Autowired
    private HttpRssDao httpRssDao;

    @Autowired
    private RssFeedBuilder builder;

   public void fire(final RssUrl rssUrl) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String key = rssUrl.getName();
                httpRssDao.setRssUrl(rssUrl);
                RssFeed rssFeed = builder
                        .title(httpRssDao.retrieveFeedTitle())
                        .rssUrl(rssUrl.getUrl())
                        .rssEntries(httpRssDao.retrieveRssEntries())
                        .build();
                rssEntryCache.put(new Element(key, rssFeed));
            }
        });
    }


}
