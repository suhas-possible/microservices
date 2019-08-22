package com.microservicedemo.movieinfoservice.models;

public class MovieDetails {
    private String original_title;
    private String overview;

    public MovieDetails() {
    }

    public MovieDetails(String original_title, String overview) {
        this.original_title = original_title;
        this.overview = overview;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
