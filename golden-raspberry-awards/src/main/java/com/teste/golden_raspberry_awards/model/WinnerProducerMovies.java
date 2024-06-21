package com.teste.golden_raspberry_awards.model;

public class WinnerProducerMovies {

	private Integer year;
	
	private String title;
	
	private String studios;
	
	private String producers;
	
	private boolean winner;
	
	public WinnerProducerMovies (String producer_name, MovieDTO movie) {		
		this.year = movie.getYear();
		this.title = movie.getTitle();
		this.studios = movie.getStudios();
		this.winner = movie.isWinner();
		this.producers = producer_name;
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

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
