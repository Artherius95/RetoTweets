package com.retotweets.retotweets.loader;

import com.retotweets.retotweets.dto.TweetDTO;
import com.retotweets.retotweets.services.TweetService;
import com.retotweets.retotweets.services.TwitterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TwitterLoader {

    Logger log = LoggerFactory.getLogger(TwitterLoader.class);

    @Autowired
    TwitterService twitterService;

    @Autowired
    TweetService tweetService;


    @Value( "${twitter.loader.users}" )
    String usersToLoad;


    @PostConstruct
    public void loadInitialTweets(){
        log.info("Retrieving inital tweets");
        List<String>  usersList = Arrays.asList(usersToLoad.split(","));
        List<TweetDTO> tweetList = new ArrayList<TweetDTO>();
        usersList.stream()
                .forEach(user -> tweetList.addAll(
                        mapStatusToDTO(getTweets(user)))
                );
        log.info("Recovered Tweets: " + tweetList.size());

        log.info("Saving tweets");
        tweetService.saveAllTweetDTO(tweetList);
    }


    public List<TweetDTO> mapStatusToDTO(List<Status> statusList){
        List<TweetDTO> listaDTO = statusList.stream()
                .map(status -> new TweetDTO(longToInteger(status.getId()),status.getUser().getName(),status.getText(),status.getLang(),false))
                .collect(Collectors.toList());
        return listaDTO;
    }


    public List<Status> getTweets(String twitterUser){
        List<Status> statuses = twitterService.getFilterTweets(twitterUser);
        return statuses;
    }

    public Integer longToInteger(long longValue){
        Long num = longValue;
        Integer integer = num.intValue();
        return integer;
    }

}
