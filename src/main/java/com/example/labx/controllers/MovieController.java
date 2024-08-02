package com.example.labx.controllers;

import com.example.labx.entities.Movie;
import com.example.labx.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return movieService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return movieService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Movie movie) {
        return movieService.create(movie);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> update(@RequestBody Movie movie) {
        return movieService.update(movie);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return movieService.deleteById(id);
    }
}
