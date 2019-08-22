package com.microservicedemo.ratingsdataservice.service;

import com.microservicedemo.ratingsdataservice.doa.RatingRepository;
import com.microservicedemo.ratingsdataservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating getRating(int userId, int movieId) {
        return ratingRepository.getRatingByUserIdAndMovieId(userId, movieId);
    }

    public List<Rating> getAllRatingsForUserId(int userId) {
        return ratingRepository.getRatingByUserId(userId);
    }

    public boolean replaceRating(int userId, int movieId, Rating rating) {
        rating.setUserId(userId);
        rating.setMovieId(movieId);
        Rating oldRating = ratingRepository.getRatingByUserIdAndMovieId(userId, movieId);
        if(oldRating != null) {
            rating.setRatingId(oldRating.getRatingId());
            ratingRepository.save(rating);
            return true;
        }
        return false;
    }

    public boolean updateRating(int userId, int movieId, Rating rating) {
        Rating oldRating = ratingRepository.getRatingByUserIdAndMovieId(userId, movieId);
        if(oldRating != null) {
            oldRating.setRating(rating.getRating());
            ratingRepository.save(oldRating);
            return true;
        }
        return false;
    }
}
