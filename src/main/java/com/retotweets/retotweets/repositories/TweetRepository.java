package com.retotweets.retotweets.repositories;

import com.retotweets.retotweets.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {


    @Query(value=" FROM com.retotweets.retotweets.entities.Tweet t " +
            " WHERE t.valid = true")
    List<Tweet> getValidTweets();

}
