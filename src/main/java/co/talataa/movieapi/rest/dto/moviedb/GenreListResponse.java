package co.talataa.movieapi.rest.dto.moviedb;

import java.util.List;

public record GenreListResponse(
        List<Genre> genres
) {
}
