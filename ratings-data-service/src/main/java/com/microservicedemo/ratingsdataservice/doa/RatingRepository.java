package com.microservicedemo.ratingsdataservice.doa;

import com.microservicedemo.ratingsdataservice.models.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {
    Rating getRatingByUserIdAndMovieId(int userId, int movieId);
    List<Rating> getRatingByUserId(int userId);
}
