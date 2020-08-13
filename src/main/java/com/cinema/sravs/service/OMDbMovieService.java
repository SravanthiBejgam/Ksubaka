package com.cinema.sravs.service;

import com.cinema.sravs.domain.Movie;
import com.cinema.sravs.domain.MovieSearch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OMDbMovieService {
     Mono<Movie> searchMovieByTitle(String apiKey, String title);
     Flux<MovieSearch> getMovieInfo(String name, String apiKey);
}
