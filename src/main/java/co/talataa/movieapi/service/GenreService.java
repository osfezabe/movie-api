package co.talataa.movieapi.service;

import co.talataa.movieapi.rest.dto.moviedb.Genre;
import co.talataa.movieapi.rest.dto.moviedb.GenreListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private ApiService apiService;

    private List<Genre> list(String path) {
        GenreListResponse response = apiService.get(path, GenreListResponse.class);
        return Optional.ofNullable(response).map(GenreListResponse::genres).orElse(List.of());
    }

    public List<Genre> list() {
        return list("genre/list");
    }

    public List<Genre> movieList() {
        return list("genre/movie/list");
    }

    public List<Genre> tvList() {
        return list("genre/tv/list");
    }
}
