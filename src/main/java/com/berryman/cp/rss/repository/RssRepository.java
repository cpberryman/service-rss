package com.berryman.cp.rss.repository;

import com.berryman.cp.rss.model.RssFeed;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Interface which defines the MongoDB queries relating to RSS feeds
 *
 * @author cpberryman.
 */
public interface RssRepository extends MongoRepository<RssFeed, String> {

    List<RssFeed> findAllRssFeeds();

    RssFeed findRssFeedById(Integer id);

    RssFeed findRssFeedByName(String name);

    void insertRssFeed(RssFeed rssFeed);

    void deleteRssFeed(RssFeed rssFeed);


}
