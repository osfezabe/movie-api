package co.talataa.movieapi.rest.controller;

import co.talataa.movieapi.rest.dto.moviedb.PagedResponse;
import co.talataa.movieapi.rest.dto.moviedb.Person;
import co.talataa.movieapi.rest.dto.moviedb.PersonWithMovie;
import co.talataa.movieapi.service.PersonService;
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
@RequestMapping(path = "person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("{id}")
    public ResponseEntity<Person> get(@PathVariable Integer id) {
        log.debug("Consultando los detalles de la persona/actor con id {}", id);
        var response = personService.get(id);
        log.info("Detalles encontrados para la persona/actor {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("popular")
    public ResponseEntity<PagedResponse<PersonWithMovie>> popular(@RequestParam(name = "page", required = false) Integer page) {
        int pageNumber = RestValidator.validPageNumberParam(page);
        log.debug("Consultando listado de personas/actores populares. Página {}", pageNumber);
        var response = personService.popular(pageNumber);
        log.info("{} elementos encontrados en la página {} de personas/actores populares", response.total_results(), pageNumber);
        return ResponseEntity.ok(personService.popular(pageNumber));
    }
}
