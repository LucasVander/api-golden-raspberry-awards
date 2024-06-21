package com.teste.golden_raspberry_awards.model;

import java.util.ArrayList;
import java.util.List;
import com.teste.golden_raspberry_awards.db.model.Movie;
import com.teste.golden_raspberry_awards.db.model.Producer;

public class ProducerDTO {
    
    private String name;
    
    private List<MovieDTO> movies = new ArrayList<>();
    
    public ProducerDTO() {}    
    
    public ProducerDTO(Producer producer) {
        this.name = producer.getName();
        for(Movie movie : producer.getMovies()) {
             MovieDTO movieDTO = new MovieDTO();
             movieDTO.setYear(movie.getYear());
             movieDTO.setTitle(movie.getTitle());
             movieDTO.setStudios(movie.getStudios());
             movieDTO.setWinner(movie.isWinner());
             this.movies.add(movieDTO);
        }
    }
    
    public List<WinnerProducerMovies> toWinnerProducerMovies() {
    	List<WinnerProducerMovies> winnerProducerMovies = new ArrayList<WinnerProducerMovies>();
    	for (MovieDTO movie : this.getMovies()) {
    		winnerProducerMovies.add(new WinnerProducerMovies(this.getName(), movie));
    	}
    	return winnerProducerMovies;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MovieDTO> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieDTO> movies) {
		this.movies = movies;
	}
}
