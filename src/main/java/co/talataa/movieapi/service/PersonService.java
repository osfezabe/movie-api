package co.talataa.movieapi.service;

import co.talataa.movieapi.rest.dto.moviedb.PagedResponse;
import co.talataa.movieapi.rest.dto.moviedb.Person;
import co.talataa.movieapi.rest.dto.moviedb.PersonWithMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private static final String PERSON_BASE_PATH = "person";
    @Autowired
    private ApiService apiService;

    public Person get(Integer id) {
        return apiService.get(PERSON_BASE_PATH + "/" + id, Person.class);
    }

    public PagedResponse<PersonWithMovie> popular(int pageNumber) {
        return apiService.getPage(PERSON_BASE_PATH + "/popular", pageNumber);
    }
}
