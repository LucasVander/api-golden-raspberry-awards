package com.teste.golden_raspberry_awards.model;

import java.util.ArrayList;
import java.util.List;

public class ProducerIntervalsResponse {
    private List<ProducerInterval> min;
    private List<ProducerInterval> max;
        
	public ProducerIntervalsResponse() {
		this.min = new ArrayList<>();
		this.max = new ArrayList<>();
	}

	public List<ProducerInterval> getMin() {
		return min;
	}
	public void setMin(List<ProducerInterval> min) {
		this.min = min;
	}
	public List<ProducerInterval> getMax() {
		return max;
	}
	public void setMax(List<ProducerInterval> max) {
		this.max = max;
	}
}