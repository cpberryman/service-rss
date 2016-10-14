package com.berryman.cp.rss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Main class for Spring Boot application
 *
 * @author cpberryman.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.berryman.cp.rss")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
