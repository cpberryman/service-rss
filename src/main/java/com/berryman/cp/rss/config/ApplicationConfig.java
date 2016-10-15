package com.berryman.cp.rss.config;

import static com.berryman.cp.rss.util.Constants.*;

import com.berryman.cp.rss.model.RssFeed;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Bean configuration class for the application
 *
 * @author cpberryman.
 */
@Configuration
@EnableScheduling
@EnableMongoRepositories(basePackages = "com.berryman.cp.rss")
public class ApplicationConfig extends AbstractMongoConfiguration {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(QUE_CAPACITY);
        return threadPoolTaskExecutor;
    }

    @Bean
    public Cache cache() {
        CacheManager cacheManager = CacheManager.getInstance();
        return cacheManager.getCache(RSS_ENTRY_CACHE);
    }


    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost:27017");
    }

    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("rss_retrieval-");
        executor.initialize();
        return executor;
    }
}
