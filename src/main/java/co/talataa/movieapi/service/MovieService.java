package co.talataa.movieapi.service;

import co.talataa.movieapi.rest.dto.moviedb.Movie;
import co.talataa.movieapi.rest.dto.moviedb.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private static final String MOVIE_BASE_PATH = "movie";
    @Autowired
    private ApiService apiService;

    public PagedResponse<Movie> popular(int pageNumber) {
        return apiService.getPage(MOVIE_BASE_PATH + "/popular", pageNumber);
    }

    public Movie get(Integer id) {
        return apiService.get(MOVIE_BASE_PATH + "/" + id, Movie.class);
    }
}
