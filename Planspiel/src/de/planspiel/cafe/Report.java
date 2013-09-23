package de.planspiel.cafe;

import java.util.HashMap;

/**
 * 
 * für jede Unternehmenskette gibt es pro Runde einen Report, der alle
 * Zwischenergebnisse aufnimmt
 * 
 */

public class Report {
	private int runde;
	private Unternehmenskette kette;
	private double kapital;
	private double kreditsumme;
	private Lager lager;
	private HashMap<Filiale, double[]> filialenListe; // double[3]
														// AnzahlMitarbeiter,
														// Auslastung,
														// AnzahlKonkurrenzfilialen
	private double anschaffungskosten;
	private double unterhaltungskosten;
	private double personalkosten;
	private double kreditkosten;
	private double marketingkosten;
	private double rohstoffkosten;
	private double umsatzerloese;
	private double rundenergebnis;
	private int anzahlKunden;
	private Marktanteil marktanteil;

	// Attribut für Ereignisse + getter, setter

	public Report(int runde, Unternehmenskette kette) {
		this.runde = runde;
		this.kette = kette;
		this.filialenListe = new HashMap<Filiale, double[]>(kette.holeAnzahlFilialen());
	}

	public int holeRunde() {
		return runde;
	}

	public Unternehmenskette holeKette() {
		return kette;
	}

	public double holeKapital() {
		return kapital;
	}

	public void setzeKapital(double kapital) {
		this.kapital = kapital;
	}

	public double holeKreditsumme() {
		return kreditsumme;
	}

	public void setzeKreditsumme(double kreditsumme) {
		this.kreditsumme = kreditsumme;
	}

	public Lager holeLager() {
		return lager;
	}

	public void setzeLager(Lager lager) {
		this.lager = lager;
	}

	public HashMap<Filiale, double[]> holeFilialenListe() {
		return filialenListe;
	}

	public void setzeFilialenListe(HashMap<Filiale, double[]> filialenListe) {
		this.filialenListe = filialenListe;
	}

	public double holeAnschaffungskosten() {
		return anschaffungskosten;
	}

	public void setzeAnschaffungskosten(double anschaffungskosten) {
		this.anschaffungskosten = anschaffungskosten;
	}

	public double holeUnterhaltungskosten() {
		return unterhaltungskosten;
	}

	public void setzeUnterhaltungskosten(double unterhaltungskosten) {
		this.unterhaltungskosten = unterhaltungskosten;
	}

	public double holePersonalkosten() {
		return personalkosten;
	}

	public void setzePersonalkosten(double personalkosten) {
		this.personalkosten = personalkosten;
	}

	public double holeKreditkosten() {
		return kreditkosten;
	}

	public void setzeKreditkosten(double kreditkosten) {
		this.kreditkosten = kreditkosten;
	}

	public double holeMarketingkosten() {
		return marketingkosten;
	}

	public void setzeMarketingkosten(double marketingkosten) {
		this.marketingkosten = marketingkosten;
	}

	public double holeRohstoffkosten() {
		return rohstoffkosten;
	}

	public void setzeRohstoffkosten(double rohstoffkosten) {
		this.rohstoffkosten = rohstoffkosten;
	}

	public double holeUmsatzerloese() {
		return umsatzerloese;
	}

	public void setzeUmsatzerloese(double umsatzerloese) {
		this.umsatzerloese = umsatzerloese;
	}

	public double holeRundenergebnis() {
		return rundenergebnis;
	}

	/**
	 * Berechnet das Rundenergebnis, indem alle addierten Kosten von den
	 * Umsatzerlösen abgezogen werden
	 */
	public void berechneRundenergebnis() {
		double kosten = this.holeAnschaffungskosten()
				+ this.holeUnterhaltungskosten() + this.holePersonalkosten()
				+ this.holeKreditkosten() + this.holeMarketingkosten()
				+ this.holeRohstoffkosten();
		if (this.holeRundenergebnis() != 0.0) {
			this.rundenergebnis = this.holeUmsatzerloese() - kosten;
		}
	}

	public int holeAnzahlKunden() {
		return anzahlKunden;
	}

	public void setzeAnzahlKunden(int anzahlKunden) {
		this.anzahlKunden = anzahlKunden;
	}

	public Marktanteil holeMarktanteil() {
		return marktanteil;
	}

	public void setzeMarktanteil(Marktanteil marktanteil) {
		this.marktanteil = marktanteil;
	}

}
