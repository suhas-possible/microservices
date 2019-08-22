package com.microservicedemo.moviecatalogservice.services;

import com.microservicedemo.moviecatalogservice.models.Catalog;
import com.microservicedemo.moviecatalogservice.models.Movie;
import com.microservicedemo.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoImpl implements MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    @Override
    public Catalog getCatalog(Rating rating) {
        Movie movie = restTemplate.getForObject(
                "http://movie-info-service/movie/" + rating.getMovieId(),
                Movie.class
        );
        return new Catalog(movie.getMovieName(), movie.getMovieDesc(), rating.getRating());
    }

    public Catalog getFallbackCatalog(Rating rating) {
        return new Catalog("Unable to fetch movie name.", "Unable to fetch movie description.", rating.getRating());
    }
}
