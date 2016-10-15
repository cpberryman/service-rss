package com.berryman.cp.rss.scheduling;

import com.berryman.cp.rss.concurrency.RssRetrieval;
import com.berryman.cp.rss.model.RssFeed;
import com.berryman.cp.rss.repository.RssUrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Class which describes scheduled tasks for the RSS application
 *
 * @author cpberryman.
 */
@Component
public class RssScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(RssScheduledTasks.class);

    @Autowired
    private RssUrlRepository rssRepository;

    @Autowired
    private RssRetrieval rssRetrieval;

//    @PostConstruct
//    @Scheduled(cron = "0 0 * * * *")
//    public void loadRssEntries() {
//        List<RssFeed> rssFeeds = rssRepository.findAll();
//        if (rssFeeds != null) {
//            if(!rssFeeds.isEmpty()) {
//                for (RssFeed rssFeed : rssFeeds) {
//                    rssRetrieval.fire(rssFeed);
//                }
//            } else {
//                LOGGER.debug("Error retrieving RSS Feeds from database");
//            }
//        } else {
//            LOGGER.debug("Error retrieving RSS Feeds from database");
//        }
//    }

}
