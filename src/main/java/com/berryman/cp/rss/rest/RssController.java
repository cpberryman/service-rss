package com.berryman.cp.rss.rest;

import com.berryman.cp.rss.model.RssFeed;
import com.berryman.cp.rss.model.RssUrl;
import com.berryman.cp.rss.model.RssUrlBuilder;
import com.berryman.cp.rss.service.RssService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller to handle RSS requests
 *
 * @author cpberryman.
 */
@RestController
@RequestMapping("/rss")
public class RssController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RssController.class);

    @Autowired
    RssService rssService;

    @Autowired
    public RssUrlBuilder rssUrlBuilder;

    @ResponseBody
    @RequestMapping(value = "/add",
            method = RequestMethod.GET,
            produces = "application/json")
    public RssUrl addRssUrl(@RequestParam("id") String id,
                            @RequestParam("name") String name,
                            @RequestParam("url") String url) throws ParseException {

        return rssService.addRssUrl(asRssUrl(id, name, url));

    }

    @RequestMapping(value = "/urls/all",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<RssUrl> retrieveAllRssUrls() throws ParseException {

        return rssService.retrieveAllRssUrls();

    }

    @ResponseBody
    @RequestMapping(value = "/entries/all/{number}",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<RssFeed> retrieveEntriesForAllFeedsByNumber(@PathVariable("number") Integer number) throws ParseException {

        return rssService.retrieveEntriesForAllFeedsByNumber(number);

    }

    @ResponseBody
    @RequestMapping(value = "/entries/{feedName}/{number}",
            method = RequestMethod.GET,
            produces = "application/json")
    public String retrieveEntriesForFeedByNumber(@PathVariable("feedName") String feedName,
                                                        @PathVariable("number") Integer number) throws ParseException {

        return asJsonString(rssService.retrieveEntriesForFeedByNumber(feedName, number));

    }

    private String asJsonString(RssFeed rssFeed) {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = mapper.writeValueAsString(rssFeed);
        } catch (JsonProcessingException e) {
            jsonString = "Error when processing RssFeed object " + rssFeed.toString() + " : " + e;
            LOGGER.info(jsonString);
        }

        return jsonString;
    }


    private RssUrl asRssUrl(String id, String name, String url) {
        return rssUrlBuilder.id(id)
                .name(name)
                .url(url)
                .build();
    }


}
