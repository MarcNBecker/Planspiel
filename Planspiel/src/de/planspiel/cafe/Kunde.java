package de.planspiel.cafe;

import java.util.Vector;

public class Kunde {
	private Vector<Unternehmenskette> kettenliste;
	private Vector<Produkt> produkte;
	private int präferenz;
	
	public Kunde() {
		
	}
	
	public void kennenlernen(Unternehmenskette ukette) {
		// TO DO
		hinzufügenUnternehmenskette(ukette);
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
	
	public int holePräferenz() {
		return this.präferenz;
	}
	
	public void setzePräferenz (int präferenz) {
		this.präferenz=präferenz;
	}
	
	public void hinzufügenUnternehmenskette (Unternehmenskette ukette) {
		this.kettenliste.add(ukette);
	}
	
	public void hinzufügenProdukt (Produkt produkt) {
		this.produkte.add(produkt);
	}
}
