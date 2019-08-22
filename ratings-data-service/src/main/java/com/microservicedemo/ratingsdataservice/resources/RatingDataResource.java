package com.microservicedemo.ratingsdataservice.resources;

import com.microservicedemo.ratingsdataservice.models.Rating;
import com.microservicedemo.ratingsdataservice.models.UserRating;
import com.microservicedemo.ratingsdataservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/{userId}")
public class RatingDataResource {

    @Autowired
    private RatingService ratingService;

    @GetMapping("rating/{movieId}")
    public Rating getRating(@PathVariable int userId, @PathVariable int movieId) {
        return ratingService.getRating(userId, movieId);
    }

    @GetMapping("/ratings")
    public UserRating getUserRatings(@PathVariable int userId) {
        List<Rating> ratings = ratingService.getAllRatingsForUserId(userId);

        return new UserRating(ratings);
    }

    @PostMapping("/rating")
    public Rating addRating(@PathVariable int userId, @RequestBody Rating rating) {
        rating.setUserId(userId);
        return ratingService.addRating(rating);
    }

    @PutMapping("/rating/{movieId}")
    public boolean replaceRating(@PathVariable int userId, @PathVariable int movieId, @RequestBody Rating rating) {
        return ratingService.replaceRating(userId, movieId, rating);
    }

    @PatchMapping("/rating/{movieId}")
    public boolean updateRating(@PathVariable int userId, @PathVariable int movieId, @RequestBody Rating rating) {
        return ratingService.updateRating(userId, movieId, rating);
    }
}
