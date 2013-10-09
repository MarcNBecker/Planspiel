package de.planspiel.cafe;

import java.util.HashMap;

/**
 * Organisation der Rundenst�nde
 * f�r jede Unternehmenskette gibt es pro Runde einen Report, der alle
 * Zwischenergebnisse aufnimmt
 * @author Ann-Kathrin
 * 
 */

public class Report {
	private int runde;
	private Unternehmenskette kette;
	private double kapital;
	private double kreditsumme;
	private VerkaufsListe verkaufsListe;
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
	private double sonstigeerloese;
	private Marktanteil marktanteil;

	// Attribut f�r Ereignisse + getter, setter

	/**
	 * Erzeugt einen neuen Report f�r eine bestimmte Unternehmenskette
	 * @param runde Runde in der der Report erzeugt wurde
	 * @param kette Unternehmenskette des Reports
	 */
	public Report(int runde, Unternehmenskette kette) {
		this.runde = runde;
		this.kette = kette;
		this.filialenListe = new HashMap<Filiale, double[]>(
				kette.holeAnzahlFilialen());
		this.verkaufsListe = new VerkaufsListe(kette);
	}
	
	/**
	 * Berechnet das Rundenergebnis, indem alle addierten Kosten von den
	 * Erl�sen abgezogen werden
	 */
	public double berechnenRundenergebnis() {
		double kosten = this.holeAnschaffungskosten()
				+ this.holeUnterhaltungskosten()
				+ this.holePersonalkosten() + this.holeKreditkosten()
				+ this.holeMarketingkosten() + this.holeRohstoffkosten();
		return this.holeUmsatzerloese() + this.holeSonstigeErloese() - kosten;
	}

	/**
	 * @return Runde, in der der Report erzeugt wurde
	 */
	public int holeRunde() {
		return runde;
	}

	/**
	 * @return Kette f�r die der Report erzeugt wurde
	 */
	public Unternehmenskette holeKette() {
		return kette;
	}

	/**
	 * @return Kapital der Kette in der aktuellen Runde
	 */
	public double holeKapital() {
		return kapital;
	}

	/**
	 * Setzt das Kapital der Kette in der aktuellen Runde
	 * @param kapital
	 */
	public void setzeKapital(double kapital) {
		this.kapital = kapital;
	}

	/**
	 * @return Fremdkapital, also die Summe aller Restbetr�ge der Kredite in der aktuellen Runde
	 */
	public double holeKreditsumme() {
		return kreditsumme;
	}

	/**
	 * Setzt das Fremdkapital, also die Summe aller Restbetr�ge der Kredite in der aktuellen Runde
	 * @param kreditsumme Fremdkapital
	 */
	public void setzeKreditsumme(double kreditsumme) {
		if(kreditsumme < 0){
			kreditsumme = 0;
		}
		this.kreditsumme = kreditsumme;
	}

	/**
	 * @return Gibt die Liste aller Filialen zum Zeitpunkt des Reports zur�ck mit filialenspezifischen Infos
	 */
	public HashMap<Filiale, double[]> holeFilialenListe() {
		return filialenListe;
	}
	
	/**
	 * @return Anschaffungskosten einer Filiale
	 */
	public double holeAnschaffungskosten() {
		return anschaffungskosten;
	}

	/**
	 * Setzt die Anschaffungskosten
	 * @param anschaffungskosten
	 */
	public void setzeAnschaffungskosten(double anschaffungskosten) {
		this.anschaffungskosten = anschaffungskosten;
	}
	
	/**
	 * @return Unterhaltungskosten einer Filiale
	 */
	public double holeUnterhaltungskosten() {
		return unterhaltungskosten;
	}
	
	/**
	 * Setzt die Unterhaltungskosten einer Filiale
	 * @param unterhaltungskosten
	 */
	public void setzeUnterhaltungskosten(double unterhaltungskosten) {
		this.unterhaltungskosten = unterhaltungskosten;
	}
	
	/**
	 * @return Mitarbeiterkosten
	 */
	public double holePersonalkosten() {
		return personalkosten;
	}
	
	/**
	 * Setzt die Personalkosten
	 * @param personalkosten
	 */
	public void setzePersonalkosten(double personalkosten) {
		this.personalkosten = personalkosten;
	}

	/**
	 * @return Kreditkosten (Zinsaufwendungen und Tilgungen)
	 */
	public double holeKreditkosten() {
		return kreditkosten;
	}

	/**
	 * Setzt die Kreditkosten
	 * @param kreditkosten
	 */
	public void setzeKreditkosten(double kreditkosten) {
		this.kreditkosten = kreditkosten;
	}
	
	/**
	 * @return Marketingkosten
	 */
	public double holeMarketingkosten() {
		return marketingkosten;
	}

	/**
	 * Setzt die Marketingkosten
	 * @param marketingkosten
	 */
	public void setzeMarketingkosten(double marketingkosten) {
		this.marketingkosten = marketingkosten;
	}
	
	/**
	 * @return Rohstoffkosten
	 */
	public double holeRohstoffkosten() {
		return rohstoffkosten;
	}
	
	/**
	 * Setzt die Rohstoffkosten
	 * @param rohstoffkosten
	 */
	public void setzeRohstoffkosten(double rohstoffkosten) {
		this.rohstoffkosten = rohstoffkosten;
	}
	
	/**
	 * @return Umsatzerl�se
	 */
	public double holeUmsatzerloese() {
		return umsatzerloese;
	}

	/**
	 * Setzt die Umsatzerl�se
	 * @param umsatzerloese
	 */
	public void setzeUmsatzerloese(double umsatzerloese) {
		this.umsatzerloese = umsatzerloese;
	}
	
	/**
	 * @return Sonstige Erl�se (z.B. Filialverkauf)
 	 */
	public double holeSonstigeErloese() {
		return sonstigeerloese;
	}
	
	/**
	 * Setzt die sonstigen Erl�se (z.B. Filialverkauf)
	 * @param sonstigeerloese
	 */
	public void setzeSonstigeErloese(double sonstigeerloese) {
		this.sonstigeerloese = sonstigeerloese;
	}
	
	/**
	 * @return Marktanteil Objekt der Runde
	 */
	public Marktanteil holeMarktanteil() {
		return marktanteil;
	}

	/**
	 * Setzt das Marktanteil Objekt
	 * @param marktanteil
	 */
	public void setzeMarktanteil(Marktanteil marktanteil) {
		this.marktanteil = marktanteil;
	}
	
	/**
	 * @return Verkaufsliste mit Anzahl verkaufter Produkte der Runde
	 */
	public VerkaufsListe holeVerkaufsListe() {
		return verkaufsListe;
	}
	
}
