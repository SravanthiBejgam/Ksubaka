package com.cinema.sravs.service;

import com.cinema.sravs.domain.Movie;
import com.cinema.sravs.domain.MovieSearch;
import com.cinema.sravs.error.ApplicationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
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
public class OMDbMovieServiceImpl implements OMDbMovieService {

    private static final Logger logger = LoggerFactory.getLogger(OMDbMovieServiceImpl.class);

    private static final String OMDB_MIME_TYPE = "application/json";
    private static final String OMDB_API_BASE_URL = "http://omdbapi.com";
    private static final String USER_AGENT = "Spring 5 WebClient";


    private final WebClient webClient;

    public OMDbMovieServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(OMDB_API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, OMDB_MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
    }
    @Override
    public Mono<List<Movie>> getMovieInfo(String searchTerm, String apiKey) {
        if (!StringUtils.isEmpty(searchTerm) && !StringUtils.isEmpty(apiKey))
            return webClient.get().uri("/?apikey=" + apiKey + "&s=" + searchTerm +"&type=movie")
                .retrieve()
                .onStatus(httpStatus -> HttpStatus.UNAUTHORIZED.equals(httpStatus), (ClientResponse clientResponse) -> {
                    return Mono.just(new UnknownHostException("Access Unauthorised"));
                })
                .onStatus(httpStatus -> HttpStatus.SERVICE_UNAVAILABLE.equals(httpStatus), (ClientResponse clientResponse) -> {
                    return Mono.just(new ServiceUnavailableException("Oops, something went wrong"));
                })
                .bodyToFlux(MovieSearch.class)
                .map(MovieSearch::getSearch).map(movieList -> movieList.stream().map(movie -> {

                    return getDirectorInfo(movie.getImdbID(), apiKey);

                })).collectList().flatMap(mapper -> getResult(mapper));
        else
            throw new ApplicationError("Please provide valid key and search term.");
    }

    protected Mono<List<Movie>> getResult(List<Stream<Mono<Movie>>> mov) {
        List<Mono<Movie>> movieList = new ArrayList<>();
        for (int i = 0; i < mov.size(); i++) {
            mov.get(i).forEach(movie -> {
                movieList.add(movie);
            });
        }
        return Flux.fromIterable(movieList).flatMap(movie -> movie).collectList();
    }

    protected Mono<Movie> getDirectorInfo(String imdbId, String apiKey) {
        return webClient.post().uri("/?apikey=" + apiKey + "&i=+" + imdbId).retrieve().bodyToMono(Movie.class);
    }

}
