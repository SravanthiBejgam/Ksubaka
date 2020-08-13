package com.cinema.sravs.controller;

import com.cinema.sravs.domain.Movie;
import com.cinema.sravs.domain.Result;
import com.cinema.sravs.service.OMDbMovieService;
import com.cinema.sravs.service.TMDbMovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {


    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    private OMDbMovieService omDbMovieService;
    private Environment env;
    private TMDbMovieService tmDbMovieService;

    public MovieController(OMDbMovieService omDbMovieService, Environment env, TMDbMovieService tmDbMovieService){
        this.omDbMovieService=omDbMovieService;
        this.env=env;
        this.tmDbMovieService=tmDbMovieService;
    }

    @RequestMapping(value = "/title", method= RequestMethod.GET)
    public Mono<Movie> getMovieByTitle(@RequestParam String name) {
        String apiKey = env.getProperty("omdb.api.key");
        return omDbMovieService.searchMovieByTitle(apiKey, name);
    }

    @RequestMapping(value = "/omdbInfo", method= RequestMethod.GET)
    public Flux<List<Movie>> getOmDbInfo(@RequestParam String name) {
        return omDbMovieService.getMovieInfo(name, env.getProperty("omdb.api.key")).map(i -> i.getSearch());
    }

    @RequestMapping(value = "/tmdbInfo", method= RequestMethod.GET)
    public Flux<List<Result>> getTMDBMovieInfo(@RequestParam String name) {
        return tmDbMovieService.getMovieInfo(name, env.getProperty("tmdb.api.key")).map(i -> i.getResults());
    }
}
