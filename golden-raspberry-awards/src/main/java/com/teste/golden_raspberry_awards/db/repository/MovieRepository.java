package com.teste.golden_raspberry_awards.db.repository;
import org.springframework.stereotype.Repository;

import com.teste.golden_raspberry_awards.db.model.Movie;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	List<Movie> findByWinnerOrderByProducersAscYearAsc(boolean winner);
}
