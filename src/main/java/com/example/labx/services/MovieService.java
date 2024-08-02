package com.example.labx.services;

import com.example.labx.entities.Movie;
import com.example.labx.exceptions.BusinessLogicException;
import com.example.labx.repositories.MovieRepository;
import com.example.labx.responses.InternalServerErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;

    @Transactional
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Transactional
    public ResponseEntity<?> getById(Long id) {
        Optional<Movie> movieOptional = repository.findById(id);
        if (movieOptional.isPresent()) {
            return ResponseEntity.ok(movieOptional.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @Transactional
    public ResponseEntity<?> create(Movie movie) {
        try {
            if (repository.findById(movie.getId()).isPresent()) {
                throw new RuntimeException("Фильм с таким id уже существует");
            }
            return ResponseEntity.ok(repository.save(movie));
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new InternalServerErrorResponse(
                            500, e.getMessage()));
        }
    }

    @Transactional
    public ResponseEntity<?> update(Movie movie) {
        try {
            Movie foundMovie = repository.findById(movie.getId())
                    .orElseThrow(() ->
                            new BusinessLogicException("Фильм с таким id не найден"));
            foundMovie.setDirector(movie.getDirector());
            foundMovie.setLength(movie.getLength());
            foundMovie.setRating(movie.getRating());
            foundMovie.setTitle(movie.getTitle());
            foundMovie.setYear(movie.getYear());
            return ResponseEntity.ok(repository.findById(movie.getId())
                    .orElseThrow(() ->
                            new BusinessLogicException("Фильм с таким id не найден")));
        } catch (BusinessLogicException e) {
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new InternalServerErrorResponse(
                            500, e.getMessage()));
        }
    }

    @Transactional
    public ResponseEntity<?> deleteById(Long id) {
        try {
            repository.findById(id)
                    .orElseThrow(() ->
                            new BusinessLogicException("Фильм с таким id не найден"));
            repository.deleteById(id);
            return ResponseEntity.status(202).build();
        } catch (BusinessLogicException e) {
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new InternalServerErrorResponse(
                            500, e.getMessage()));
        }
    }
}
