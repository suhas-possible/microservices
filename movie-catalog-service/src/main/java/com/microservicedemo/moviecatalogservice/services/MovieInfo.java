package com.microservicedemo.moviecatalogservice.services;

import com.microservicedemo.moviecatalogservice.models.Catalog;
import com.microservicedemo.moviecatalogservice.models.Rating;

public interface MovieInfo {
    Catalog getCatalog(Rating rating);
}
