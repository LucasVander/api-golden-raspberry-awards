package com.teste.golden_raspberry_awards.service;

import java.util.List;
import com.teste.golden_raspberry_awards.model.ProducerDTO;
import com.teste.golden_raspberry_awards.model.WinnerProducerMovies;

/**
 * Interface referente ao serviço de consulta de produtores
 *
 */
public interface ProducerService {
	
	/**
     * Método que retorna todos os produtores e seus filmes
     * @return List - produtores
     */
	public List<ProducerDTO> findAll();
	
	/**
	 * Método que retorna a lista de produtores e seus filmes premiados 
	 * ordenada por nome do produtor e ano do filme
	 * @return List - produtores e seus filmes vencedores
	 */
	public List<WinnerProducerMovies> getAllProducersWithWinnerMoviesOrderedByNameAndYear();
}
