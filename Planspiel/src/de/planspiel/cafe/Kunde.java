package de.planspiel.cafe;

import java.util.Vector;

public class Kunde {
	
	private Vector<Unternehmenskette> kettenListe;
	private Vector<Produkt> produkte;
	private int praeferenz; // TODO WIE WIRD DIE GESETZT? ENUM VLT EHER?
	
	public Kunde() {
		kettenListe = new Vector<Unternehmenskette>();
		produkte = new Vector<Produkt>();
		praeferenz = 0;
	}
	
	public void kennenlernen(Unternehmenskette kette) {
		// TODO
		hinzufuegenUnternehmenskette(kette);
	}
	
	public void simulierenEinkauf() {
		// TODO
	}
	
	public Vector<Unternehmenskette> holeKettenListe() {
		return this.kettenListe;
	}
	
	public void hinzufuegenUnternehmenskette (Unternehmenskette ukette) {
		this.kettenListe.add(ukette);
	}
	
	public Vector<Produkt> holeProdukte() {
		return this.produkte;
	}
	
	public void hinzufuegenProdukt (Produkt produkt) {
		this.produkte.add(produkt);
	}
	
	public int holePraeferenz() {
		return this.praeferenz;
	}
	
	public void setzePraeferenz (int praeferenz) {
		this.praeferenz=praeferenz;
	}

}
