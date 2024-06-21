package com.teste.golden_raspberry_awards.db.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "`year`")
	private Integer year;
	
	private String title;
	
	private String studios;
	
	@ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
        name = "movie_producer",
        joinColumns = { @JoinColumn(name = "movie_id") },
        inverseJoinColumns = { @JoinColumn(name = "producer_id") }
    )
    private Set<Producer> producers = new HashSet<>();
	
	private boolean winner;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public Set<Producer> getProducers() {
		return producers;
	}


	public void setProducers(Set<Producer> producers) {
		this.producers = producers;
	}


	public boolean isWinner() {
		return winner;
	}


	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}