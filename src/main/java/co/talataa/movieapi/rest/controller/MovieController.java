package co.talataa.movieapi.rest.controller;

import co.talataa.movieapi.rest.dto.moviedb.MovieDBRecord;
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
    public ResponseEntity<MovieDBRecord> get(@PathVariable Integer id) {
        log.debug("Consultando los detalles de la película con id {}", id);
        var response = movieService.get(id);
        log.info("Detalles encontrados para la película {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("popular")
    public ResponseEntity<PagedResponse<MovieDBRecord>> popular(@RequestParam(name = "page", required = false) Integer page) {
        int pageNumber = RestValidator.validPageNumberParam(page);
        log.debug("Consultando listado de películas populares. Página {}", pageNumber);
        var response = movieService.popular(pageNumber);
        log.info("{} elementos encontrados en la página {} de películas populares", response.total_results(), pageNumber);
        return ResponseEntity.ok(movieService.popular(pageNumber));
    }

    @GetMapping("top")
    public ResponseEntity<PagedResponse<MovieDBRecord>> top(@RequestParam(name = "page", required = false) Integer page) {
        int pageNumber = RestValidator.validPageNumberParam(page);
        log.debug("Consultando listado de películas mejor calificadas. Página {}", pageNumber);
        var response = movieService.top(pageNumber);
        log.info("{} elementos encontrados en la página {} de películas mejor calificadas", response.total_results(), pageNumber);
        return ResponseEntity.ok(movieService.popular(pageNumber));
    }
}
