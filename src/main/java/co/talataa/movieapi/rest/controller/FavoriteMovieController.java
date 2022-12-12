package co.talataa.movieapi.rest.controller;

import co.talataa.movieapi.rest.dto.DemoMovies;
import co.talataa.movieapi.rest.dto.MovieDTO;
import co.talataa.movieapi.rest.dto.moviedb.MovieDBRecord;
import co.talataa.movieapi.service.FavoriteMovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = "favorites", produces = MediaType.APPLICATION_JSON_VALUE)
public class FavoriteMovieController {
    @Autowired
    private FavoriteMovieService favoriteMovieService;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> list() {
        log.debug("Consultando lista de películas favoritas registradas de forma local");
        var response = favoriteMovieService.list();
        log.info("Se encontraron {} películas marcadas como favoritas", response.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("detailed")
    public ResponseEntity<List<MovieDBRecord>> detailed() {
        log.debug("Consultando lista detallada de las películas que están marcadas como favoritas");
        var response = favoriteMovieService.detailedList();
        log.info("Se encontraron {} películas marcadas como favoritas", response.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDTO> get(@PathVariable Integer id) {
        log.debug("Petición para obtener una película favorita con base en el ID {}", id);
        MovieDTO movieDTO = favoriteMovieService.get(id);
        log.info("Se encontró la película {} en la lista de favoritas", movieDTO);
        return ResponseEntity.ok(movieDTO);
    }

    @GetMapping("{id}/detailed")
    public ResponseEntity<MovieDBRecord> getDetailed(@PathVariable Integer id) {
        log.debug("Petición para obtener los detalles completos de una película favorita con base en el ID {}", id);
        MovieDBRecord response = favoriteMovieService.getDetailed(id);
        log.info("Se encontró la película con ID {} en la lista de favoritas. Retornando los detalles completos", response.id());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> addFavorite(@RequestBody MovieDTO movieDTO) {
        log.debug("Petición para añadir la película {} como favorita", movieDTO);
        MovieDTO response = favoriteMovieService.addToFavorites(movieDTO);
        log.info("Película {} añadida como favorita de forma correcta", response);
        return ResponseEntity.created(URI.create("/favorites/" + response.id())).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFavorite(@PathVariable Integer id) {
        log.debug("Petición para remover la película {} de la lista de películas favoritas", id);
        favoriteMovieService.removeFromFavorites(id);
        log.info("Película con id {} removida de la lista de favoritas", id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("clear")
    public ResponseEntity<String> clearFavorites() {
        log.debug("Recibida nueva solicitud para limpiar las películas favoritas existentes");
        List<MovieDTO> existingMovies = favoriteMovieService.list();
        existingMovies.stream().map(MovieDTO::id).forEach(favoriteMovieService::removeFromFavorites);
        log.info("Se eliminaron las películas favoritas actuales");
        return ResponseEntity.ok("");
    }

    @PostMapping("initialize")
    public ResponseEntity<String> initialize() {
        log.debug("Recibida nueva solicitud para configurar la lista inicial demostrativa de películas");
        clearFavorites();

        DemoMovies.loadDemoList().forEach(favoriteMovieService::addToFavorites);
        log.info("Lista inicial de películas recreada de forma correcta");
        return ResponseEntity.ok("");
    }
}
