package com.teste.golden_raspberry_awards.model;

public class ProducerInterval {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;

    public ProducerInterval() {
		this.setProducer("");
		this.setInterval(0);
		this.setPreviousWin(0);
		this.setFollowingWin(0);
	}
    
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getPreviousWin() {
		return previousWin;
	}
	public void setPreviousWin(int previousWin) {
		this.previousWin = previousWin;
	}
	public int getFollowingWin() {
		return followingWin;
	}
	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	}
}
