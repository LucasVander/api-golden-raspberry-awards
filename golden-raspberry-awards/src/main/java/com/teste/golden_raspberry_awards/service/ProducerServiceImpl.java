package com.teste.golden_raspberry_awards.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teste.golden_raspberry_awards.db.model.Producer;
import com.teste.golden_raspberry_awards.db.repository.ProducerRepository;
import com.teste.golden_raspberry_awards.exception.model.MoviesNotFound;
import com.teste.golden_raspberry_awards.model.ProducerDTO;
import com.teste.golden_raspberry_awards.model.WinnerProducerMovies;

/**
 *  Serviço responsável por fazer a leitura da lista de produtores e seus filmes
 */
@Service
public class ProducerServiceImpl implements ProducerService {
	
	@Autowired
    private ProducerRepository producerRepository;
	
	
	@Override
	public List<WinnerProducerMovies> getAllProducersWithWinnerMoviesOrderedByNameAndYear() {
		List<Producer> producers = producerRepository.findAllProducersWithWinnerMoviesOrderedByNameAndYear();
		
		if (producers == null) {
			throw new MoviesNotFound("Nenhuma indicação vencedora encontrada");
		}
		
		List<WinnerProducerMovies> winnersProducersMovies = new ArrayList<>();
	    for (Producer movie : producers) {
	    	winnersProducersMovies.addAll(new ProducerDTO(movie).toWinnerProducerMovies());
	    }
	    
	    return winnersProducersMovies;
	}



	@Override
	public List<ProducerDTO> findAll() {
		List<Producer> movies = producerRepository.findAll();
    	List<ProducerDTO> dtos = new ArrayList<>();
        for (Producer movie : movies) {
            dtos.add(new ProducerDTO(movie));
        }
        return dtos;
	}
}
