package co.talataa.movieapi.rest.controller;

import co.talataa.movieapi.rest.dto.moviedb.Genre;
import co.talataa.movieapi.service.GenreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = "genre", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("list")
    public ResponseEntity<List<Genre>> list() {
        log.debug("Consultando lista de géneros de películas y programas de TV");
        var response = genreService.list();
        log.info("Se encontraron {} géneros de películas y programas de TV", response.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("movie/list")
    public ResponseEntity<List<Genre>> movieList() {
        log.debug("Consultando lista de géneros de películas");
        var response = genreService.movieList();
        log.info("Se encontraron {} géneros de películas", response.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("tv/list")
    public ResponseEntity<List<Genre>> tvList() {
        log.debug("Consultando lista de géneros de programas de TV");
        var response = genreService.tvList();
        log.info("Se encontraron {} géneros de programas de TV", response.size());
        return ResponseEntity.ok(response);
    }
}
