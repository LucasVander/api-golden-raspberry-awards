package com.teste.golden_raspberry_awards.service;

import java.util.List;
import com.teste.golden_raspberry_awards.model.MovieDTO;
import com.teste.golden_raspberry_awards.model.ProducerIntervalsResponse;
import com.teste.golden_raspberry_awards.model.WinnerProducerMovies;

/**
 * Interface referente ao serviço de consulta de filmes
 *
 */
public interface MovieService {
	
	/**
     * Método que retorna todos os filmes e seus produtores
     * @return List - filmes
     */
	public List<MovieDTO> findAll();
	
	/**
     * Método que retorna o menor e o maior intervalo anos consecutivos 
     * em que os mesmos produtores tiverem um filme premiado
     * 
     * @return ProducerIntervalsResponse
     */
    public ProducerIntervalsResponse getMinAndMaxWinnersInterval(List<WinnerProducerMovies> winnersProducersMovies);

}
