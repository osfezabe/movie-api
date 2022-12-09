package co.talataa.movieapi.rest.dto.moviedb;

import java.util.List;

public record PagedResponse<T>(
        int page,
        List<T> results,
        int total_pages,
        int total_results
) {
}
