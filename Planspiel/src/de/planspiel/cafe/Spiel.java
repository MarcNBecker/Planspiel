package de.planspiel.cafe;

import java.util.Vector;

public class Spiel {

	public static void main(String[] args) {

	}

	private static Spiel spiel;
	private int rundenzahl;
	private Vector<Standort> standortListe;
	private Vector<Unternehmenskette> kettenListe;
	
	public Spiel(int rundenzahl) {
		Spiel.spiel = this;
		this.rundenzahl = rundenzahl;
		this.standortListe = new Vector<Standort>();
		this.kettenListe = new Vector<Unternehmenskette>();
	}

	public void spielen() {
		// TODO
	}
	
	public static Spiel holeSpiel(){
		return spiel;
	}
	
	/**
	 * Zufallszahl zwischen 0 und Grenzen mit zwei Nachkommastellen erzeugen - optional auch mit Untergrenze
	 */
	public static double generierenZufallszahl(int grenze){
		double zufallszahl = Math.random()*grenze;
		int zufallszahl100 = (int) zufallszahl * 100;
		zufallszahl = zufallszahl100 / 100;
		
		return zufallszahl;
	}
	
	public int holeRundenzahl() {
		return rundenzahl;
	}

	public Vector<Standort> holeStandortListe() {
		return standortListe;
	}

	public void hinzufuegenStandort(Standort standort) {
		standortListe.add(standort);
	}

	public Vector<Unternehmenskette> holeKettenListe() {
		return kettenListe;
	}

	public void hinzufuegenUnternehmenskette(Unternehmenskette kette) {
		kettenListe.add(kette);
	}

}
