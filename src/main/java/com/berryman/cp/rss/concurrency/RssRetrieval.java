package com.berryman.cp.rss.concurrency;

import com.berryman.cp.rss.dao.HttpRssDao;
import com.berryman.cp.rss.model.RssEntry;
import com.berryman.cp.rss.model.RssFeed;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

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
                List<RssEntry> rssEntries = httpRssDao.retrieveRssEntries(rssFeed);
                rssEntryCache.put(new Element(rssFeed.getId(), httpRssDao.retrieveRssEntries(rssFeed)));
            }
        });
    }

}
