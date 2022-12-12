package co.talataa.movieapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private Integer id;
    private String title;
    @Column(length = 1000)
    private String overview;
    private float popularity;
    private boolean markedAsFavorite;
}
