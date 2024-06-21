package com.teste.golden_raspberry_awards.db.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.teste.golden_raspberry_awards.db.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Integer> {

	Producer findByName(String name);
	
	@Query("SELECT DISTINCT p FROM Producer p LEFT JOIN FETCH p.movies m WHERE m.winner is True ORDER BY p.name ASC, m.year ASC")
    List<Producer> findAllProducersWithWinnerMoviesOrderedByNameAndYear();
}
