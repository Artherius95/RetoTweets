package com.retotweets.retotweets.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TwitterService {

    Logger log = LoggerFactory.getLogger(TwitterService.class);

    @Value( "${twitter.filter.minFollowers}" )
    Integer minFollowers;

    @Value( "${twitter.filter.allowedLangs}" )
    String allowedLangs;


    public List<Status> getFilterTweets(String user){
        Twitter twitter = new TwitterFactory().getInstance();
        List<Status> statuses = null;
        try {
            statuses = twitter.getUserTimeline(user);
            log.info("Retrieving info from  @" + user + "'s tweets.");
        } catch (TwitterException te) {
            te.printStackTrace();
            log.error("Failed to get tweets from : @" +user);
            log.error(te.getMessage());
        }
        return filterTweets(statuses);
    }

    public  List<Status> filterTweets(List<Status> statuses){
        List<String>  allowedList = Arrays.asList(allowedLangs.split(","));
        return statuses.stream()
                .filter(status -> status.getUser().getFollowersCount() >= minFollowers)
                .filter(status -> allowedList.contains(status.getLang()))
                .collect(Collectors.toList());
    }
}
