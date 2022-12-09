package co.talataa.movieapi.rest.dto.moviedb;

public record ProductionCompany(
        Integer id,
        String name,
        String logo_path,
        String origin_country
) {
}
