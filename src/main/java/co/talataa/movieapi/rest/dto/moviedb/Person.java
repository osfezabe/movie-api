package co.talataa.movieapi.rest.dto.moviedb;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Person {
    private Integer id;
    private String known_for_department;
    private String birthDay;
    private String deathDay;
    private String name;
    private List<String> also_known_as;
    private Integer gender;
    private String biography;
    private float popularity;
    private String place_of_birth;
    private String profile_path;
    private boolean adult;
    private String imdb_id;
    private String homepage;
}