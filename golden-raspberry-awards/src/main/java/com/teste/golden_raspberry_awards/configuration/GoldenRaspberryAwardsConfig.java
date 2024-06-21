package com.teste.golden_raspberry_awards.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.teste.golden_raspberry_awards.db.model.Movie;
import com.teste.golden_raspberry_awards.db.model.Producer;
import com.teste.golden_raspberry_awards.db.repository.MovieRepository;
import com.teste.golden_raspberry_awards.db.repository.ProducerRepository;
import com.teste.golden_raspberry_awards.exception.model.FileNotFoundException;

import jakarta.annotation.PostConstruct;

@Configuration
public class GoldenRaspberryAwardsConfig {
	
	@Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private ProducerRepository producerRepository;
	
	
	/**
	 * Nome do csv da lista de filmes indicados e vencedores do Golden Raspberry Awards
	 */
    private static final String CSV_FILE_NAME = "movielist.csv";

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
                movie.setStudios(data[2]);
                
                String[] producerNames = data[3].split(", and | and |, ");
                for (String producerName : producerNames) {
                    String trimmedProducerName = producerName.trim();
                    Producer producer = producerRepository.findByName(trimmedProducerName);
                    if (producer == null) {
                        producer = new Producer();
                        producer.setName(trimmedProducerName);
                        producerRepository.save(producer);
                    }
                    // Associa o produtor ao filme (assumindo um relacionamento muitos-para-muitos)
                    movie.getProducers().add(producer);
                }
                
                if (data.length > 4) {
                	movie.setWinner(true);
                } else {
                	movie.setWinner(false);
                }
                
                movieRepository.save(movie);
            }
        }
    }
}
