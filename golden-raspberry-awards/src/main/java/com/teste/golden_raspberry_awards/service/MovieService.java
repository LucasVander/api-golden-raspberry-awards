package com.teste.golden_raspberry_awards.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.golden_raspberry_awards.db.model.Movie;
import com.teste.golden_raspberry_awards.db.repository.MovieRepository;
import com.teste.golden_raspberry_awards.exception.model.FileNotFoundException;
import com.teste.golden_raspberry_awards.exception.model.MoviesNotFound;
import com.teste.golden_raspberry_awards.model.ProducerInterval;
import com.teste.golden_raspberry_awards.model.ProducerIntervalsResponse;

import jakarta.annotation.PostConstruct;

/**
 *  Serviço responsável por gravar e fazer a leitura da lista de filmes indicados e vencedores
 */
@Service
public class MovieService {
	/**
	 * Nome do csv da lista de filmes indicados e vencedores do Golden Raspberry Awards
	 */
    private static final String CSV_FILE_NAME = "movielist.csv";

    @Autowired
    private MovieRepository movieRepository;

    @PostConstruct
    private void init() {
    	try {
			importFromCSV(CSV_FILE_NAME);
		} catch (IOException e) {
			throw new FileNotFoundException("Ocorreu um erro ao ler o arquivo: " + e);
		}
    }

    /**
     * Método responsável por inserir os itens da lista de indicados e vencedores em um banco de dados
     * @param filePath String - caminho para o arquivo
     * @throws IOException
     */
    public void importFromCSV(String fileName) throws IOException {
    	InputStream file = getClass().getClassLoader().getResourceAsStream(fileName);
    	
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                Movie movie = new Movie();
                movie.setYear(Integer.parseInt(data[0]));
                movie.setTitle(data[1]);
                movie.setProducers(data[2]);
                movie.setStudios(data[3]);
                
                if (data.length > 4) {
                	movie.setWinner(true);
                } else {
                	movie.setWinner(false);
                }
                
                movieRepository.save(movie);
            }
        }
    }

    /**
     * Método que retorna todos os filmes 
     * @return List - filmes
     */
    public List<Movie> findAll() {
    	return movieRepository.findAll();
    }
    
    
    /**
     * Método que retorna o menor e o maior intervalo anos consecutivos em que os mesmos produtores foram vencedores
     * @return ProducerIntervalsResponse
     */
    public ProducerIntervalsResponse getMinAndMaxWinnersInterval() {
    	ProducerInterval minInterval = new ProducerInterval();
    	ProducerInterval maxInterval = new ProducerInterval();

    	List<Movie> movies = movieRepository.findByWinnerOrderByProducersAscYearAsc(true);
    	
    	if (movies == null) {
    		throw new MoviesNotFound("Nenhuma indicação vencedora encontrada");
    	}
    	
    	movies.stream()
    	    .collect(Collectors.groupingBy(
    	        Movie::getProducers,
    	        Collectors.toMap(
    	            Movie::getYear,
    	            movie -> calculateInterval(movie, movies, minInterval
    	            		, maxInterval),
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
	private int calculateInterval(Movie movie, List<Movie> movies, ProducerInterval minInterval, ProducerInterval maxInterval) {
	    List<Movie> producerMovies = movies.stream()
	        .filter(m -> m.getProducers().equals(movie.getProducers()))
	        .sorted(Comparator.comparingInt(Movie::getYear))
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
	private void updateIntervals(ProducerInterval minInterval, ProducerInterval maxInterval, Movie currentMovie, int interval) {
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