package com.cinema.sravs.service;

import com.cinema.sravs.domain.Result;
import com.cinema.sravs.domain.TMDBSearchResult;
import com.cinema.sravs.error.ApplicationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TMDbMovieServiceImpl implements TMDbMovieService {

    private static final Logger logger = LoggerFactory.getLogger(TMDbMovieServiceImpl.class);
    private static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3";

    private final WebClient webClient1;

    public TMDbMovieServiceImpl() {
        this.webClient1 = WebClient.builder()
                .baseUrl(TMDB_API_BASE_URL)
                .build();
    }

    @Override
    public Mono<List<Result>> getMovieInfo(String searchTerm, String api_key) {

        if (!StringUtils.isEmpty(searchTerm) && !StringUtils.isEmpty(api_key))
            return webClient1.get().uri("/search/movie?query="+searchTerm+"&api_key="+api_key).retrieve()
                .onStatus(httpStatus -> HttpStatus.UNAUTHORIZED.equals(httpStatus), (ClientResponse clientResponse) -> {
                    return Mono.just(new UnknownHostException("Access Unauthorised"));
                })
                .onStatus(httpStatus -> HttpStatus.SERVICE_UNAVAILABLE.equals(httpStatus), (ClientResponse clientResponse) -> {
                    return Mono.just(new ServiceUnavailableException("Service Unavailable"));
                }).bodyToFlux( TMDBSearchResult.class)
                    .map(TMDBSearchResult::getResults).map(resultList -> resultList.stream().map(result -> {

                        return getDirectorInfo(result.getId(), api_key);

                    })).collectList().flatMap(mapper -> getResult(mapper));
        else
            throw new ApplicationError("Please provide valid key and search term.");

    }

    protected Mono<List<Result>> getResult(List<Stream<Mono<Result>>> mov) {
        List<Mono<Result>> resultList = new ArrayList<>();
        for (int i = 0; i < mov.size(); i++) {
            mov.get(i).forEach(movie -> {
                resultList.add(movie);
            });
        }
        return Flux.fromIterable(resultList).flatMap(result -> result).collectList();
    }

    protected Mono<Result> getDirectorInfo(String imdbId, String apiKey) {
        return webClient1.get().uri("/movie/" + imdbId + "?api_key=" + apiKey).retrieve().bodyToMono(Result.class);
    }

}
