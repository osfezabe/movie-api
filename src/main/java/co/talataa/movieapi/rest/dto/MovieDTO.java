package co.talataa.movieapi.rest.dto;

public record MovieDTO(
        Integer id,
        String title,
        String overview,
        float popularity,
        boolean markedAsFavorite
) {
}
