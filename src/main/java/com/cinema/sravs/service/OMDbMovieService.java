package com.cinema.sravs.service;

import com.cinema.sravs.domain.Movie;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OMDbMovieService {
     Mono<List<Movie>> getMovieInfo(String name, String apiKey);
}
