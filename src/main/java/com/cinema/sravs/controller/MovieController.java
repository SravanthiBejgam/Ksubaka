package com.cinema.sravs.controller;

import com.cinema.sravs.GeneralConstants;
import com.cinema.sravs.domain.Movie;
import com.cinema.sravs.domain.Result;
import com.cinema.sravs.service.OMDbMovieService;
import com.cinema.sravs.service.TMDbMovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/omdbInfo", method= RequestMethod.GET)
    public Mono<List<Movie>> getOmDbInfo(@RequestParam String name) {
        logger.info("OMDB Search Term", name);
        return omDbMovieService.getMovieInfo(name,env.getProperty(GeneralConstants.OMDB_KEY));
    }

    @RequestMapping(value = "/tmdbInfo", method= RequestMethod.GET)
    public Mono<List<Result>> getTmDBMovieInfo(@RequestParam String name) {
        logger.info("TMDB Search Term", name);
        return tmDbMovieService.getMovieInfo(name, env.getProperty(GeneralConstants.TMDB_KEY));
    }
}
