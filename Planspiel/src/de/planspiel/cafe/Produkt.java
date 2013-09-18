package de.planspiel.cafe;

public class Produkt {
	
	private String name;
	private double qualitaet;
	private double preis;
	private double ekpreis;
	private int menge;
	
	public Produkt(String name) {
		this.name = name;
		this.qualitaet = -1;
		this.preis = -1;
		this.ekpreis = -1;
		this.menge = 0;
	}
	
	public boolean vergleichen() {
		// TODO
		return true;	
	}
	
	public String holeName() {
		return this.name;
	}
	
	public double holeQualitaet() {
		return this.qualitaet;
	}
	
	public double holePreis() {
		return this.preis;
	}
	
	public double holeEkpreis() {
		return this.ekpreis;
	}
	
	public int holeMenge() {
		return this.menge;
	}
	
	public void setzeQualitaet(double qualitaet) {
		this.qualitaet = qualitaet;
	}
	
	public void setzePreis(double preis) {
		this.preis = preis;
	}
	
	public void setzeEkpreis(double ekpreis) {
		this.ekpreis = ekpreis;
	}
	
	public void setzeMenge(int menge) {
		this.menge = menge;
	}
}
