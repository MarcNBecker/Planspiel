package de.planspiel.cafe;

import java.util.Vector;

public class Kunde {
	private Vector<Unternehmenskette> kettenliste;
	private Vector<Produkt> produkte;
	private int pr�ferenz;
	
	public Kunde() {
		
	}
	
	public void kennenlernen(Unternehmenskette ukette) {
		// TO DO
		hinzuf�genUnternehmenskette(ukette);
	}
	
	public void simulierenEinkauf() {
		// TO DO
	}
	
	public Vector<Unternehmenskette> holeKettenliste() {
		return this.kettenliste;
	}
	
	public Vector<Produkt> holeProdukte() {
		return this.produkte;
	}
	
	public int holePr�ferenz() {
		return this.pr�ferenz;
	}
	
	public void setzePr�ferenz (int pr�ferenz) {
		this.pr�ferenz=pr�ferenz;
	}
	
	public void hinzuf�genUnternehmenskette (Unternehmenskette ukette) {
		this.kettenliste.add(ukette);
	}
	
	public void hinzuf�genProdukt (Produkt produkt) {
		this.produkte.add(produkt);
	}
}
