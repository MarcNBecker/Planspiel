package de.planspiel.cafe;

public class Produkt {
	String name;
	double qualität;
	double preis;
	double ekpreis;
	int menge;
	
	public Produkt() {
		
	}
	
	public boolean vergleichen() {
		return true;	
	}
	
	public void setzenVerkaufspreis(double verkaufspreis) {
		
	}
	
	public String holeName() {
		return this.name;
	}
	
	public double holeQualität() {
		return this.qualität;
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
	
	public void setzeName(String name) {
		this.name = name;
	}
	
	public void setzeQualität(double qualität) {
		this.qualität = qualität;
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
