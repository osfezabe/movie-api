package co.talataa.movieapi.rest.dto.moviedb;

import java.time.LocalDate;
import java.util.List;

public record MovieDBRecord(
        Integer id,
        boolean adult,
        String backdrop_path,
        Object belongs_to_collection,
        long budget,
        List<Genre> genres,
        String homepage,
        String imdb_id,
        String original_language,
        String original_title,
        String overview,
        float popularity,
        String poster_path,
        List<ProductionCompany> production_companies,
        List<ProductionCountry> production_countries,
        LocalDate release_date,
        Long revenue,
        Integer runtime,
        List<SpokenLanguage> spoken_languages,
        MovieStatus status,
        String tagline,
        String title,
        boolean video,
        float vote_average,
        int vote_count,
        boolean markedAsFavorite
) {
}