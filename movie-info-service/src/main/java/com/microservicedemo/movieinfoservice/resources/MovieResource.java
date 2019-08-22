package com.microservicedemo.movieinfoservice.resources;

import com.microservicedemo.movieinfoservice.models.Movie;
import com.microservicedemo.movieinfoservice.models.MovieDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable int movieId) {
        MovieDetails movieDetails = restTemplate.getForObject(
            "http://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey,
            MovieDetails.class
        );
        return new Movie(movieId, movieDetails.getOriginal_title(), movieDetails.getOverview());
    }
}
