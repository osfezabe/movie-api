package co.talataa.movieapi.repository;

import co.talataa.movieapi.domain.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    List<Movie> findAll();
}
