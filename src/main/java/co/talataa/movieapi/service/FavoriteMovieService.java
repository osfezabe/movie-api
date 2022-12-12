package co.talataa.movieapi.service;

import co.talataa.movieapi.domain.Movie;
import co.talataa.movieapi.factory.MovieFactory;
import co.talataa.movieapi.repository.MovieRepository;
import co.talataa.movieapi.rest.dto.MovieDTO;
import co.talataa.movieapi.rest.dto.moviedb.MovieDBRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteMovieService {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieFactory movieFactory;


    public List<MovieDTO> list() {
        return movieRepository.findAll().stream().map(movieFactory::toDTO).toList();
    }

    public MovieDTO get(Integer id) {
        return movieRepository.findById(id).map(movieFactory::toDTO).orElseThrow();
    }

    public MovieDBRecord getDetailed(Integer id) {
        return movieRepository.findById(id).map(Movie::getId).map(movieService::get).orElseThrow();
    }

    public List<MovieDBRecord> detailedList() {
        return movieRepository.findAll().parallelStream().map(Movie::getId).map(movieService::get).toList();
    }

    public MovieDTO addToFavorites(MovieDTO movieDTO) {
        Movie movie = movieFactory.fromDTO(movieDTO);
        movie.setMarkedAsFavorite(true);
        movieRepository.save(movie);
        return movieFactory.toDTO(movie);
    }

    public void removeFromFavorites(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        movieRepository.delete(movie);
    }
}
