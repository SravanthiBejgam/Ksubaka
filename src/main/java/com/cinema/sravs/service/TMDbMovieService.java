package com.cinema.sravs.service;
import com.cinema.sravs.domain.Result;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TMDbMovieService {
     Mono<List<Result>> getMovieInfo(String name, String apiKey);
}
