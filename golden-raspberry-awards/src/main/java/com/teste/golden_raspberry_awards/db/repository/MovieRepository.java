package com.teste.golden_raspberry_awards.db.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.teste.golden_raspberry_awards.db.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
