package com.teste.golden_raspberry_awards.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.golden_raspberry_awards.db.model.Movie;
import com.teste.golden_raspberry_awards.model.ProducerIntervalsResponse;
import com.teste.golden_raspberry_awards.service.MovieService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * API de consulta de filmes indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards
 */
@RestController
@RequestMapping("/api/golden-raspberry-awards/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }
   
    @GetMapping("/interval")
    public ResponseEntity<ProducerIntervalsResponse> getAwardsInterval() {
        return ResponseEntity.ok(movieService.getMinAndMaxWinnersInterval());
    }   
}
