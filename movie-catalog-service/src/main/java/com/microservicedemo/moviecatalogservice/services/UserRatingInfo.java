package com.microservicedemo.moviecatalogservice.services;

import com.microservicedemo.moviecatalogservice.models.UserRating;

public interface UserRatingInfo {
    UserRating getUserRating(int userId);
}
