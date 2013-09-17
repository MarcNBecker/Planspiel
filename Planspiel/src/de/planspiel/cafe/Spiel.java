package de.planspiel.cafe;

import java.util.Vector;

public class Spiel {

	public static void main(String[] args) {

	}
	
	private int rundenzahl;
	private Vector<Standort> standortListe;
	private Vector<Unternehmenskette> kettenListe;
	
	public Spiel(int rundenzahl) {
		this.rundenzahl = rundenzahl;
		this.standortListe = new Vector<Standort>();
		this.kettenListe = new Vector<Unternehmenskette>();
	}

	public void spielen() {
		// TODO
	}
	
	public int getRundenzahl() {
		return rundenzahl;
	}

	public Vector<Standort> getStandortListe() {
		return standortListe;
	}

	public void hinzufuegenStandort(Standort standort) {
		standortListe.add(standort);
	}

	public Vector<Unternehmenskette> getKettenListe() {
		return kettenListe;
	}

	public void hinzufuegenUnternehmenskette(Unternehmenskette kette) {
		kettenListe.add(kette);
	}	
}
