package com.berryman.cp.rss.config;

import static com.berryman.cp.rss.util.Constants.*;

import com.berryman.cp.rss.model.RssFeed;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Bean configuration class for the application
 *
 * @author cpberryman.
 */
@Configuration
@EnableScheduling
public class ApplicationConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(QUE_CAPACITY);
        return threadPoolTaskExecutor;
    }

    @Bean
    public Cache rssEntryCache() {
        CacheManager cacheManager = CacheManager.getInstance();
        cacheManager.addCache(RSS_ENTRY_CACHE);
        return cacheManager.getCache(RSS_ENTRY_CACHE);
    }


}
