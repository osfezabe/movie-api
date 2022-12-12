package co.talataa.movieapi.factory;

import co.talataa.movieapi.domain.Movie;
import co.talataa.movieapi.rest.dto.MovieDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class MovieFactory {
    public MovieDTO toDTO(Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getOverview(),
                movie.getPopularity(),
                movie.isMarkedAsFavorite()
        );
    }

    public Movie fromDTO(MovieDTO dto) {
        return new Movie(
                dto.id(),
                dto.title(),
                dto.overview(),
                dto.popularity(),
                dto.markedAsFavorite()
        );
    }

    public List<MovieDTO> toDTOs(List<Movie> movies) {
        return Optional.ofNullable(movies)
                .map(list -> list.stream().map(this::toDTO).toList())
                .orElse(Collections.emptyList());
    }
}
