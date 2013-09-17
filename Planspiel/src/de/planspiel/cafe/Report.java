package de.planspiel.cafe;

public class Report {
	
	private int runde;
	private double umsatz;
	
	public Report(int runde, double umsatz){
		this.runde = runde;
		this.umsatz = umsatz;
	}
	
	public int holeRunde() {
		return runde;
	}
	
	public double holeUmsatz() {
		return umsatz;
	}
	
}
