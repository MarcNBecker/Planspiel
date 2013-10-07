package de.planspiel.spiel;

import java.util.Vector;

import de.planspiel.cafe.Haendler;
import de.planspiel.cafe.Standort;
import de.planspiel.cafe.Unternehmenskette;

public class Spiel {

	public static void main(String[] args) {

	}

	private static Spiel spiel;
	private int rundenzahl;
	private int aktuelleRunde;
	private Vector<Standort> standortListe;
	private Vector<Haendler> haendlerListe;
	private Vector<Unternehmenskette> kettenListe;
	
	/**
	 * Startet ein neues Spiel
	 * @param rundenzahl Zahl der Runden, die das Spiel läuft
	 */
	public Spiel(int rundenzahl) {
		Spiel.spiel = this;
		this.rundenzahl = rundenzahl;
		setzeAktuelleRunde(1);
		this.standortListe = new Vector<Standort>();
		this.kettenListe = new Vector<Unternehmenskette>();
	}
	
	/**
	 * Führt das Spiel durch
	 */
	public void spielen() {
		// TODO
	}
	
	/**
	 * @return Gibt das aktuelle Spiel zurück
	 */
	public static Spiel holeSpiel(){
		return spiel;
	}
	
	
	/**
	 * @return Gibt die aktuelle Rundenzahl zurück
	 */
	public int holeRundenzahl() {
		return rundenzahl;
	}
	
	/**
	 * @return Aktuelle Rundennummer. Die erste Rundennummer ist 1, die letzte Rundennummer wird durch Rundenzahl bestimmt
	 */
	public int holeAktuelleRunde() {
		return aktuelleRunde;
	}
	
	/**
	 * Setzt die neue aktuelle Runde, die z.B. für die Kreditlaufzeit oder den Report-Vektor genutzt wird
	 * @param aktuelleRunde
	 */
	public void setzeAktuelleRunde(int aktuelleRunde) {
		this.aktuelleRunde = aktuelleRunde;
	}
	
	/**
	 * @return Liste mit allen Standorten
	 */
	public Vector<Standort> holeStandortListe() {
		return standortListe;
	}

	/**
	 * Fügt einen neuen Standort hinzu
	 * @param standort Standort ungleich null
	 */
	public void hinzufuegenStandort(Standort standort) {
		if(standort != null) {
			standortListe.add(standort);
		}
	}

	/**
	 * @return Liste mit allen Händlern
	 */
	public Vector<Haendler> holeHaendlerListe() {
		return haendlerListe;
	}
	
	/**
	 * Fügt einen Händler hinu
	 * @param haendler Händler ungleich null
	 */
	public void hinzufuegenHaendler(Haendler haendler) {
		if(haendler != null) {
			haendlerListe.add(haendler);
		}
	}
	
	/**
	 * @return Liste aller Unternehmensketten
	 */
	public Vector<Unternehmenskette> holeKettenListe() {
		return kettenListe;
	}
	
	/**
	 * Fügt ein Unternehmen dem Spiel hinzu
	 * @param kette Unternehmenskette ungleich null
	 */
	public void hinzufuegenUnternehmenskette(Unternehmenskette kette) {
		if(kette != null) {
			kettenListe.add(kette);
		}
	}

}
