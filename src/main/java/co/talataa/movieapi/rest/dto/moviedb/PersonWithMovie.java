package co.talataa.movieapi.rest.dto.moviedb;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonWithMovie extends Person {
    private MovieDBRecord known_for;
}