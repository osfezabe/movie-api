package co.talataa.movieapi.rest.controller;

import co.talataa.movieapi.rest.dto.moviedb.Movie;
import co.talataa.movieapi.rest.dto.moviedb.PagedResponse;
import co.talataa.movieapi.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping(path = "movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("{id}")
    public ResponseEntity<Movie> get(@PathVariable Integer id) {
        log.debug("Consultando los detalles de la película con id {}", id);
        var response = movieService.get(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("popular")
    public ResponseEntity<PagedResponse<Movie>> popular(@RequestParam(name = "page", required = false) Integer pageNumber) {
        return ResponseEntity.ok(movieService.popular(RestValidator.validPageNumberParam(pageNumber)));
    }

    @GetMapping("top")
    public ResponseEntity<PagedResponse<Movie>> top(@RequestParam(name = "page", required = false) Integer pageNumber) {
        return ResponseEntity.ok(movieService.top(RestValidator.validPageNumberParam(pageNumber)));
    }
}
