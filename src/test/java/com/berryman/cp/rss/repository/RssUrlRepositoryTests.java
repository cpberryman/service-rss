package com.berryman.cp.rss.repository;

import com.berryman.cp.rss.config.ApplicationConfig;
import com.berryman.cp.rss.model.RssUrl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.berryman.cp.rss.util.TestUtil.getTestRssUrls;
import static junit.framework.TestCase.assertEquals;

/**
 * Tests for {@link RssUrlRepository}
 *
 * @author cpberryman.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class RssUrlRepositoryTests {

    @Autowired
    RssUrlRepository repository;

    RssUrl url1;
    RssUrl url2;

    @Before
    public void setUp() {
        url1 = getTestRssUrls().get(0);
        url2 = getTestRssUrls().get(1);
    }

    @Test
    public void insertsRssUrlCorrectly() {

        //given... //when...
        repository.insert(url1);
        RssUrl actual = repository.findByName("foo");

        //then...
        assertEquals(actual.getName(), url1.getName());
        assertEquals(actual.getId(), url1.getId());
        assertEquals(actual.getUrl(), url1.getUrl());

    }

    @Test
    public void findRssUrlByIdCorrectly() {

        //given...
        repository.insert(url1);

        //when...
        RssUrl actual = repository.findById("test1");

        //then...
        assertEquals(actual.getName(), url1.getName());

    }

    @Test
    public void findRssUrlByNameCorrectly() {

        //given...
        repository.insert(url1);

        //when...
        RssUrl actual = repository.findByName("foo");

        //then...
        assertEquals(actual.getName(), url1.getName());

    }

    @Test
    public void deleteRssUrlByInstanceCorrectly() {

        //given...
        repository.insert(url1);
        repository.insert(url2);

        //when...
        repository.delete(url2);
        List<RssUrl> rssUrls =repository.findAll();

        //then...
        assertEquals(rssUrls.size(), 1);

    }

    @After
    public void after() {
        repository.deleteAll();
    }

}
