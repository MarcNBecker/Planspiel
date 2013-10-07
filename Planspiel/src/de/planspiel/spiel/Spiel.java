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
	 * @param rundenzahl Zahl der Runden, die das Spiel l�uft
	 */
	public Spiel(int rundenzahl) {
		Spiel.spiel = this;
		this.rundenzahl = rundenzahl;
		setzeAktuelleRunde(1);
		this.standortListe = new Vector<Standort>();
		this.kettenListe = new Vector<Unternehmenskette>();
	}
	
	/**
	 * F�hrt das Spiel durch
	 */
	public void spielen() {
		// TODO
	}
	
	/**
	 * @return Gibt das aktuelle Spiel zur�ck
	 */
	public static Spiel holeSpiel(){
		return spiel;
	}
	
	
	/**
	 * @return Gibt die aktuelle Rundenzahl zur�ck
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
	 * Setzt die neue aktuelle Runde, die z.B. f�r die Kreditlaufzeit oder den Report-Vektor genutzt wird
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
	 * F�gt einen neuen Standort hinzu
	 * @param standort Standort ungleich null
	 */
	public void hinzufuegenStandort(Standort standort) {
		if(standort != null) {
			standortListe.add(standort);
		}
	}

	/**
	 * @return Liste mit allen H�ndlern
	 */
	public Vector<Haendler> holeHaendlerListe() {
		return haendlerListe;
	}
	
	/**
	 * F�gt einen H�ndler hinu
	 * @param haendler H�ndler ungleich null
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
	 * F�gt ein Unternehmen dem Spiel hinzu
	 * @param kette Unternehmenskette ungleich null
	 */
	public void hinzufuegenUnternehmenskette(Unternehmenskette kette) {
		if(kette != null) {
			kettenListe.add(kette);
		}
	}

}
