package com.microservicedemo.moviecatalogservice.services;

import com.microservicedemo.moviecatalogservice.models.Rating;
import com.microservicedemo.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
@Service
public class UserRatingInfoImpl implements UserRatingInfo{

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    @Override
    public UserRating getUserRating(@PathVariable int userId) {
        return restTemplate.getForObject(
                "http://ratings-data-service/" + userId + "/ratings/",
                UserRating.class
        );
    }

    public UserRating getFallbackUserRating(@PathVariable int userId) {
        UserRating userRating = new UserRating();
        userRating.setUserRating(Arrays.asList(new Rating(userId, 0, 0)));

        return userRating;
    }
}
