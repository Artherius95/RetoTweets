package com.retotweets.retotweets.controllers;

import com.retotweets.retotweets.dto.TweetDTO;
import com.retotweets.retotweets.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

@RestController
public class RetoTweetController {

    @Autowired
    TweetService tweetService;

    @Value( "${twitter.filter.maxHashtags}" )
    Integer maxHashtags;

    @GetMapping("/tweets")
    public ResponseEntity<List<TweetDTO>> getTweets(){
        return ResponseEntity.ok(tweetService.getAll());
    }

    @PutMapping("/validate/{idTweet}")
    public ResponseEntity<TweetDTO> setValidTweet( @PathVariable Integer idTweet){
        return ResponseEntity.ok(tweetService.setValid(idTweet));
    }

    @GetMapping("/validatedTweets")
    public ResponseEntity<List<TweetDTO>> getValidatedTweets(){
        return ResponseEntity.ok(tweetService.getAllValidTweets());
    }

    @GetMapping("/mostUsedHastag")
    public ResponseEntity<Map<String, Integer>> getMostUsedHashtag(){


        // Get all the tweets
        List<TweetDTO> allTweets = tweetService.getAll();

        // Getting all the hashTags (with duplicates)
        List<String> hashTags = new ArrayList<>();
        allTweets.forEach(tweet ->
                hashTags.addAll(getHashtags(tweet)));

        // Removing duplicates and get unique keys
        List<String> hashtagWithoutDuplicates = hashTags.stream().distinct().collect(Collectors.toList());

        // Count hashTags
        Map<String,Integer> disorderedhashTags = new HashMap<>();
        hashtagWithoutDuplicates.forEach(
                t -> disorderedhashTags.put(t,Collections.frequency(hashTags,t)));

        // Sort by uses
        Map<String, Integer>  orderedhashTags = disorderedhashTags.entrySet().stream()
                .sorted(reverseOrder(Map.Entry.comparingByValue()))
                .limit(maxHashtags)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (newValue, oldValue) -> oldValue, LinkedHashMap::new));

        return ResponseEntity.ok(orderedhashTags);
    }



    private List<String> getHashtags(TweetDTO tweet){
        List<String> hashTags = new ArrayList<>();

        Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
        Matcher mat = MY_PATTERN.matcher(tweet.getText());

        while (mat.find()) {
            hashTags.add(mat.group(1));
        }
        return hashTags;
    }


}
