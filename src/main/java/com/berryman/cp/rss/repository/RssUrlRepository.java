package com.berryman.cp.rss.repository;

import com.berryman.cp.rss.model.RssUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Interface which defines the MongoDB queries relating to RSS feeds
 *
 * @author cpberryman.
 */
    public interface RssUrlRepository extends MongoRepository<RssUrl, Long> {

    RssUrl findById(String id);

    RssUrl findByName(String name);

}
