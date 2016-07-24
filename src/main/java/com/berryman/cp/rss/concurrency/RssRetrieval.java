package com.berryman.cp.rss.concurrency;

import com.berryman.cp.rss.dao.HttpRssDao;
import com.berryman.cp.rss.model.RssFeed;
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

   public void fire(final RssFeed rssFeed) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                httpRssDao.setFeed(rssFeed);
                rssFeed.setRssEntries(httpRssDao.retrieveRssEntries());
                rssEntryCache.put(new Element(rssFeed.getId(), rssFeed));
            }
        });
    }


}
