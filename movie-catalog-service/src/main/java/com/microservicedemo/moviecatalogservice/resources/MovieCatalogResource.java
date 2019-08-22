package com.microservicedemo.moviecatalogservice.resources;

import com.microservicedemo.moviecatalogservice.models.Catalog;
import com.microservicedemo.moviecatalogservice.models.UserRating;
import com.microservicedemo.moviecatalogservice.services.MovieInfo;
import com.microservicedemo.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{userId}")
public class MovieCatalogResource {

    @Autowired
    private MovieInfo movieInfo;

    @Autowired
    private UserRatingInfo userRatingInfo;

    @GetMapping("/catalogs")
    public List<Catalog> getCatalogs(@PathVariable int userId) {
        UserRating userRating = userRatingInfo.getUserRating(userId);

        return userRating.getUserRating().stream()
                .map(rating -> movieInfo.getCatalog(rating))
                .collect(Collectors.toList());
    }
}

