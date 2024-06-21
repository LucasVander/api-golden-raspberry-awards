package com.teste.golden_raspberry_awards.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teste.golden_raspberry_awards.model.ProducerIntervalsResponse;
import com.teste.golden_raspberry_awards.model.WinnerProducerMovies;
import com.teste.golden_raspberry_awards.service.MovieService;
import com.teste.golden_raspberry_awards.service.ProducerService;

/**
 * API de consulta de filmes indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards
 */
@RestController
@RequestMapping("/api/golden-raspberry-awards/")
public class GoldenRaspberryAwardsController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private ProducerService producerService;
    
    @GetMapping("/movie")
    public ResponseEntity<List<?>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }
    
    @GetMapping("/producer")
    public ResponseEntity<List<?>> getAllProducers() {
        return ResponseEntity.ok(producerService.findAll());
    }
   
    @GetMapping("/interval")
    public ResponseEntity<ProducerIntervalsResponse> getAwardsInterval() {
    	List<WinnerProducerMovies> winnersProducersMovies = producerService.getAllProducersWithWinnerMoviesOrderedByNameAndYear();
    	
        return ResponseEntity.ok(movieService.getMinAndMaxWinnersInterval(winnersProducersMovies));
    }
}
