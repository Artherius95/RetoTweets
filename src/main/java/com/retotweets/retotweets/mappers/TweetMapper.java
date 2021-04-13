package com.retotweets.retotweets.mappers;

import com.retotweets.retotweets.dto.TweetDTO;
import com.retotweets.retotweets.entities.Tweet;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper
public interface TweetMapper {

    List<Tweet> asTweetList(List<TweetDTO> src);

    List<TweetDTO> asTweetDTOList(List<Tweet> src);

    TweetDTO asTweetDTO(Tweet src);


}
