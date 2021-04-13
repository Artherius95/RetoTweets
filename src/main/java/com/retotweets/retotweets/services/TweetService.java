package com.retotweets.retotweets.services;

import com.retotweets.retotweets.dto.TweetDTO;
import com.retotweets.retotweets.entities.Tweet;
import com.retotweets.retotweets.mappers.TweetMapper;
import com.retotweets.retotweets.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    TweetMapper tweetMapper;

    public void saveAllTweetDTO(List<TweetDTO> tweetList){
        tweetRepository.saveAll(tweetMapper.asTweetList(tweetList));
    }

    public List<TweetDTO> getAll(){
        return tweetMapper.asTweetDTOList(tweetRepository.findAll());
    }

    public List<TweetDTO> getAllValidTweets(){
        return tweetMapper.asTweetDTOList(tweetRepository.getValidTweets());
    }


    public TweetDTO setValid(Integer id){
        Optional<Tweet> tweet = tweetRepository.findById(id);
        TweetDTO tweetdto = new TweetDTO();
        if(tweet.isPresent()){
            tweet.get().setValid(true);
            tweetdto = tweetMapper.asTweetDTO(tweetRepository.save(tweet.get()));


        }
        return tweetdto;
    }

}
