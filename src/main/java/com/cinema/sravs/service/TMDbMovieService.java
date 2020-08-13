package com.cinema.sravs.service;


import com.cinema.sravs.domain.TMDBSearchResult;
import reactor.core.publisher.Flux;

public interface TMDbMovieService {
     Flux<TMDBSearchResult> getMovieInfo(String name, String apiKey);
}
