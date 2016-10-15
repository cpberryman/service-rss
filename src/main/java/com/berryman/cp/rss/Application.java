package com.berryman.cp.rss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Main class for Spring Boot application
 *
 * @author cpberryman.
 */
@SpringBootApplication
@EnableAsync
@ComponentScan("com.berryman.cp.rss")
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
