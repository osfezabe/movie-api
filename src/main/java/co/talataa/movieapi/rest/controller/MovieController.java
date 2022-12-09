package co.talataa.movieapi.rest.controller;

import co.talataa.movieapi.rest.dto.moviedb.Movie;
import co.talataa.movieapi.rest.dto.moviedb.PagedResponse;
import co.talataa.movieapi.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Log4j2
@RequestMapping(path = "movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {
    private static final int DEFAULT_PAGE = 1;
    @Autowired
    private MovieService movieService;

    @GetMapping("popular")
    public ResponseEntity<PagedResponse<Movie>> latest(@RequestParam(name = "page", required = false) Integer pageNumber) {
        int page = Optional.ofNullable(pageNumber).map(number -> Math.max(number, DEFAULT_PAGE)).orElse(DEFAULT_PAGE);
        return ResponseEntity.ok(movieService.popular(page));
    }
}
