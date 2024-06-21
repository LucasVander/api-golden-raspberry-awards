package com.teste.golden_raspberry_awards.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teste.golden_raspberry_awards.db.model.Movie;
import com.teste.golden_raspberry_awards.db.repository.MovieRepository;
import com.teste.golden_raspberry_awards.model.MovieDTO;
import com.teste.golden_raspberry_awards.model.ProducerInterval;
import com.teste.golden_raspberry_awards.model.ProducerIntervalsResponse;
import com.teste.golden_raspberry_awards.model.WinnerProducerMovies;

/**
 *  Serviço responsável por fazer a leitura da lista de filmes indicados e vencedores
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    
    
    @Override
    public List<MovieDTO> findAll() {
    	List<Movie> movies = movieRepository.findAll();
    	List<MovieDTO> dtos = new ArrayList<>();
        for (Movie movie : movies) {
            dtos.add(new MovieDTO(movie));
        }
        return dtos;
    }
    
    @Override
    public ProducerIntervalsResponse getMinAndMaxWinnersInterval(List<WinnerProducerMovies> winnersProducersMovies) {
    	ProducerInterval minInterval = new ProducerInterval();
    	ProducerInterval maxInterval = new ProducerInterval();

    	winnersProducersMovies.stream()
    		    .collect(Collectors.groupingBy(
    		        WinnerProducerMovies::getProducers,
    		        Collectors.toMap(
    		            WinnerProducerMovies::getYear,
    		            (WinnerProducerMovies winnerProducerMovie) -> calculateInterval(winnerProducerMovie, winnersProducersMovies, minInterval, maxInterval),
    		            (interval1, interval2) -> interval1
    		        )
    		    ));

    	ProducerIntervalsResponse response = new ProducerIntervalsResponse();
    	response.getMin().add(minInterval);
    	response.getMax().add(maxInterval);
    	return response;
    }
    
	/**
	 * Método que calcula o intervalo de dois anos consecutivos
	 * @param movie Movie
	 * @param movies List
	 * @param minInterval ProducerInterval
	 * @param maxInterval ProducerInterval
	 * @return int - valor do intervalo
	 */
	private int calculateInterval(WinnerProducerMovies movie, List<WinnerProducerMovies> movies, ProducerInterval minInterval, ProducerInterval maxInterval) {
	    List<WinnerProducerMovies> producerMovies = movies.stream()
	        .filter(m -> m.getProducers().equals(movie.getProducers()))
	        .sorted(Comparator.comparingInt(WinnerProducerMovies::getYear))
	        .collect(Collectors.toList());

	    int index = producerMovies.indexOf(movie);
	    if (index < producerMovies.size() - 1) {
	        int interval = producerMovies.get(index + 1).getYear() - movie.getYear();
	        updateIntervals(minInterval, maxInterval, producerMovies.get(index), interval);
	        return interval;
	    } else {
	        return 0; // Se não houver próximo ano do mesmo produtor, o intervalo é zero
	    }
	}

	/**
	 * Método que atualiza o maior e o menor intervalo de produtores vencedores
	 * @param minInterval ProducerInterval
	 * @param maxInterval ProducerInterval
	 * @param currentMovie Movie
	 * @param interval int
	 */
	private void updateIntervals(ProducerInterval minInterval, ProducerInterval maxInterval, WinnerProducerMovies currentMovie, int interval) {
	    if (minInterval.getInterval() > interval || minInterval.getInterval() == 0) {
	        minInterval.setProducer(currentMovie.getProducers());
	        minInterval.setInterval(interval);
	        minInterval.setPreviousWin(currentMovie.getYear());
	        minInterval.setFollowingWin(currentMovie.getYear() + interval);
	    }

	    if (maxInterval.getInterval() < interval) {
	        maxInterval.setProducer(currentMovie.getProducers());
	        maxInterval.setInterval(interval);
	        maxInterval.setPreviousWin(currentMovie.getYear());
	        maxInterval.setFollowingWin(currentMovie.getYear() + interval);
	    }
	}
}