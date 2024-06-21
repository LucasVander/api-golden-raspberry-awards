package com.teste.golden_raspberry_awards.model;

import java.util.ArrayList;
import java.util.List;
import com.teste.golden_raspberry_awards.db.model.Movie;
import com.teste.golden_raspberry_awards.db.model.Producer;

public class MovieDTO {
	
	private Integer year;
	
	private String title;
	
	private String studios;
	
	private List<ProducerDTO> producers = new ArrayList<>();
	
	private boolean winner;
	
	public MovieDTO() {}

	public MovieDTO(Movie movie) {
        
        this.year = movie.getYear();
        this.title = movie.getTitle();
        this.studios = movie.getStudios();
    	this.winner = movie.isWinner();
        for(Producer producer : movie.getProducers()) {
        	 ProducerDTO producerDTO = new ProducerDTO();
        	 producerDTO.setName(producer.getName());
             this.producers.add(producerDTO);
        }
    }

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public List<ProducerDTO> getProducers() {
		return producers;
	}

	public void setProducers(List<ProducerDTO> producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
