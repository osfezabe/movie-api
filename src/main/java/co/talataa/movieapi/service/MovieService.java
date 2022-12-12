package co.talataa.movieapi.service;

import co.talataa.movieapi.rest.dto.moviedb.MovieDBRecord;
import co.talataa.movieapi.rest.dto.moviedb.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private static final String MOVIE_BASE_PATH = "movie";
    @Autowired
    private ApiService apiService;

    public MovieDBRecord get(Integer id) {
        return apiService.get(MOVIE_BASE_PATH + "/" + id, MovieDBRecord.class);
    }

    public PagedResponse<MovieDBRecord> popular(int pageNumber) {
        return apiService.getPage(MOVIE_BASE_PATH + "/popular", pageNumber);
    }

    public PagedResponse<MovieDBRecord> top(int pageNumber) {
        return apiService.getPage(MOVIE_BASE_PATH + "/top_rated", pageNumber);
    }
}
