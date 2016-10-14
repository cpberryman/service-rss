package com.berryman.cp.rss.rest;

import com.berryman.cp.rss.config.ApplicationConfig;
import com.berryman.cp.rss.model.RssUrl;
import com.berryman.cp.rss.model.RssUrlBuilder;
import com.berryman.cp.rss.service.impl.RssServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static com.berryman.cp.rss.util.TestUtil.*;
import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Test for {@link RssController}
 *
 * @author cpberryman.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class RssControllerTest {

    RssController rssController;

    MockMvc mockMvc;

    RssServiceImpl rssServiceMock;

    RssUrl url;

    RssUrlBuilder rssUrlBuilder;

    @Before
    public void setUp() {
        rssController = new RssController();
        rssUrlBuilder = new RssUrlBuilder();
        ReflectionTestUtils.setField(rssController, "rssUrlBuilder", rssUrlBuilder);
        rssServiceMock = mock(RssServiceImpl.class);
        ReflectionTestUtils.setField(rssController, "rssService", rssServiceMock);
        mockMvc = standaloneSetup(rssController).build();
    }

    @Test
    public void shouldAddRssUrlCorrectly() throws Exception {

        //given...
        url = getTestRssUrls().get(0);
        when(rssServiceMock.addRssUrl(any(RssUrl.class))).thenReturn(url);

        //when... //then...
        mockMvc.perform(post("/rss/add?id=test1&name=foo&url=foo.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("test1")))
                .andExpect(jsonPath("$.name", is("foo")))
                .andExpect(jsonPath("$.url", is("foo.com")));

        verify(rssServiceMock, times(1)).addRssUrl(any(RssUrl.class));
        verifyNoMoreInteractions(rssServiceMock);

    }

    @Test
    public void shouldRetrieveAllRssUrlsCorrectly() throws Exception {

        //given...
        when(rssServiceMock.retrieveAllRssUrls()).thenReturn(getTestRssUrls());

        //when... //then...
        mockMvc.perform(get("/rss/urls/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("test1")))
                .andExpect(jsonPath("$[0].name", is("foo")))
                .andExpect(jsonPath("$[0].url", is("foo.com")))
                .andExpect(jsonPath("$[1].id", is("test2")))
                .andExpect(jsonPath("$[1].name", is("bar")))
                .andExpect(jsonPath("$[1].url", is("bar.com")));


        verify(rssServiceMock, times(1)).retrieveAllRssUrls();
        verifyNoMoreInteractions(rssServiceMock);


    }

    @Test
    public void shouldRetrieveEntriesForAllFeedsByNumberCorrectly() throws Exception {

        //given...
        when(rssServiceMock.retrieveEntriesForAllFeedsByNumber(anyInt())).thenReturn(getTestRssFeeds());

        //when... //then...
        mockMvc.perform(get("/rss/entries/all/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("foo title")))
                .andExpect(jsonPath("$[0].rssUrl", is("foo.com")))
                .andExpect(jsonPath("$[0].rssEntries", hasSize(2)))
                .andExpect(jsonPath("$[1].title", is("bar title")))
                .andExpect(jsonPath("$[1].rssUrl", is("bar.com")))
                .andExpect(jsonPath("$[1].rssEntries", hasSize(2)));


        verify(rssServiceMock, times(1)).retrieveEntriesForAllFeedsByNumber(anyInt());
        verifyNoMoreInteractions(rssServiceMock);

    }

    @Test
    public void shouldRetrieveEntriesForFeedByNumberCorrectly() throws Exception {

        //given...
        when(rssServiceMock.retrieveEntriesForFeedByNumber(anyString(), anyInt())).thenReturn(getTestRssFeeds().get(0));

        //when... //then...
        mockMvc.perform(get("/rss/entries/foo title/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("foo title")))
                .andExpect(jsonPath("$.rssUrl", is("foo.com")))
                .andExpect(jsonPath("$.rssEntries", hasSize(2)));


        verify(rssServiceMock, times(1)).retrieveEntriesForFeedByNumber(anyString(), anyInt());
        verifyNoMoreInteractions(rssServiceMock);

    }

}
