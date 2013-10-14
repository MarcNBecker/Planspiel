package de.planspiel.cafe;

import java.util.HashMap;
import java.util.Vector;

import de.planspiel.spiel.Spiel;

/**
 * Organisation der Rundenstände für jede Unternehmenskette gibt es pro Runde
 * einen Report, der alle Zwischenergebnisse aufnimmt
 * 
 * @author Ann-Kathrin
 * 
 */

public class Report {
	private int runde;
	private Unternehmenskette kette;
	private double kasse;
	private double startGesamtkapital;
	private double startFremdkapital;
	private double endGesamtkapital;
	private double endFremdkapital;
	private VerkaufsListe verkaufsListe;
	private HashMap<Filiale, double[]> filialenListe; // double[4]
														// AnzahlMitarbeiter,
														// Auslastung,
														// AnzahlKonkurrenzfilialen
														// AnzahlKunden
	private double anschaffungskosten;
	private double unterhaltungskosten;
	private double personalkosten;
	private double kreditkosten;
	private double marketingkosten;
	private double rohstoffkosten;
	private double umsatzerloese;
	private double sonstigeerloese;
	private Marktanteil marktanteil;

	// Attribut für Ereignisse + getter, setter

	/**
	 * Erzeugt einen neuen Report für eine bestimmte Unternehmenskette
	 * 
	 * @param runde
	 *            Runde in der der Report erzeugt wurde
	 * @param kette
	 *            Unternehmenskette des Reports
	 */
	public Report(int runde, Unternehmenskette kette) {
		this.runde = runde;
		this.kette = kette;
		this.kasse = kette.holeKasse();
		this.startGesamtkapital = kette.berechnenGesamtkapital();
		this.startFremdkapital = kette.berechnenFremdkapital();
	}

	/**
	 * Berechnet das Rundenergebnis, indem alle addierten Kosten von den Erlösen
	 * abgezogen werden
	 * @deprecated
	 */
	public double berechnenRundenergebnis() {
		double kosten = this.holeAnschaffungskosten()
				+ this.holeUnterhaltungskosten() + this.holePersonalkosten()
				+ this.holeKreditkosten() + this.holeMarketingkosten()
				+ this.holeRohstoffkosten();
		return this.holeUmsatzerloese() + this.holeSonstigeErloese() - kosten;
	}

	/**
	 * Schließt die Runde ab, indem die FilialenListe erzeugt wird. Neben der
	 * Filiale wird die Anzahl der Mitarbeiter, die Auslastung und die Anzahl
	 * der Konkurrenzfilialen gespeichert.
	 */
	public void abschließenRunde() {
		this.filialenListe = new HashMap<Filiale, double[]>(
				holeKette().holeAnzahlFilialen());
		Vector<Filiale> filialen = holeKette().holeFilialenListe();
		double[] infos = new double[3];
		for (int i = 0; i < holeFilialenListe().size(); i++) {
			Filiale filiale = filialen.get(i);
			// Bestimme Mitarbeiter
			infos[0] = filiale.holeMitarbeiter();
			// Bestimme Auslastung
			infos[1] = 1 - (filiale.holeFreieKapazitaet() / filiale.holeStartKapazitaet());
			// Bestimme Konkurenzfilialen
			infos[2] = filiale.holeStandort().holeFilialenListe().size() - 1;
			// Bestimme AnzahlKunden
			Vector<Kunde> kunden = filiale.holeStandort().holeKundenkreis();
			int counter = 0;
			for(int j=0; j<kunden.size(); j++){
				Kunde kunde = kunden.get(j);
				if(kunde.holeKettenListe().contains(holeKette())){
					counter++;
				}
			}
			infos[3] = counter;
			holeFilialenListe().put(filiale, infos);
		}
		setzeEndGesamtkapital(holeKette().berechnenGesamtkapital());
		setzeEndFremdkapital(holeKette().berechnenFremdkapital());
		if(verkaufsListe == null){
			verkaufsListe = new VerkaufsListe(holeKette());
		}
	}
	
	/**
	 * @return Gewinn der aktuellen Periode
	 */
	public double berechnenGewinn() {
		if(holeRunde() == 1 && Spiel.holeSpiel().holeAktuelleRunde() == 1) {
			return 0;
		}
		double ekVorher = holeStartGesamtkapital() - holeStartFremdkapital();
		double ekNachher = holeEndGesamtkapital() - holeEndFremdkapital();
		double gewinn = ekNachher - ekVorher;
		return gewinn;
	}
	
	/**
	 * @return Gesamter Gewinn bis zu dieser Runde
	 */
	public double berechnenGesamtgewinn() {
		if(holeRunde() == 1  && Spiel.holeSpiel().holeAktuelleRunde() == 1) {
			return 0;
		}
		Report ersterReport = holeKette().holeReportListe().get(0);
		double ekVorher = ersterReport.holeStartGesamtkapital() - ersterReport.holeStartFremdkapital();
		double ekNachher = holeEndGesamtkapital() - holeEndFremdkapital();
		double gesamtgewinn = ekNachher - ekVorher;
		return gesamtgewinn;
	}

	/**
	 * @return Runde, in der der Report erzeugt wurde
	 */
	public int holeRunde() {
		return runde;
	}

	/**
	 * @return Kette für die der Report erzeugt wurde
	 */
	public Unternehmenskette holeKette() {
		return kette;
	}

	/**
	 * @return Kapital der Kette in der aktuellen Runde
	 */
	public double holeKasse() {
		return kasse;
	}

	/**
	 * Setzt das Kapital der Kette in der aktuellen Runde
	 * 
	 * @param kapital
	 */
	public void setzeKasse(double kasse) {
		if(kasse < 0){
			kasse = 0;
		}
		this.kasse = kasse;
	}
	
	/**
	 * @return Gesamtkapital, vor der aktuellen Runde
	 */
	public double holeStartGesamtkapital() {
		return startGesamtkapital;
	}
	
	/**
	 * Setzt das Gesamtkapital vor der aktuellen Runde
	 * @param gesamtkapital
	 */
	public void setzeStartGesamtkapital(double gesamtkapital) {
		if (gesamtkapital < 0) {
			gesamtkapital = 0;
		}
		startGesamtkapital = gesamtkapital;
	}
	
	/**
	 * Setzt das Gesamtkapital nach der aktuellen Runde
	 * @param gesamtkapital
	 */
	public void setzeEndGesamtkapital(double gesamtkapital) {
		if (gesamtkapital < 0) {
			gesamtkapital = 0;
		}
		endGesamtkapital = gesamtkapital;
	}
	
	/**
	 * @return Gesamtkapital nach der aktuellen Runde
	 */
	public double holeEndGesamtkapital() {
		return endGesamtkapital;
	}
	
	/**
	 * @return Fremdkapital, also die Summe aller Restbeträge der Kredite, vor der
	 *         aktuellen Runde
	 */
	public double holeStartFremdkapital() {
		return startFremdkapital;
	}

	/**
	 * Setzt das Fremdkapital, also die Summe aller Restbeträge der Kredite in
	 * der aktuellen Runde
	 * 
	 * @param fremdkapital
	 *            Fremdkapital, vor der Runde des Reports
	 */
	public void setzeStartFremdkapital(double fremdkapital) {
		if (fremdkapital < 0) {
			fremdkapital = 0;
		}
		this.startFremdkapital = fremdkapital;
	}
	
	/**
	 * @return Fremdkapital, also die Summe aller Restbeträge der Kredite, nach der aktuellen Runde
	 */
	public double holeEndFremdkapital() {
		return endFremdkapital;
	}
	
	/**
	 * Setzt das Fremdkapital, nach der aktuellen Runde
	 * @param fremdkapital Fremdkapital, nach der aktuellen Runde
	 */
	public void setzeEndFremdkapital(double fremdkapital) {
		if (fremdkapital < 0) {
			fremdkapital = 0;
		}
		this.endFremdkapital = fremdkapital;	
	}

	/**
	 * @return Gibt die Liste aller Filialen zum Zeitpunkt des Reports zurück
	 *         mit filialenspezifischen Infos
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * @param rohstoffkosten
	 */
	public void setzeRohstoffkosten(double rohstoffkosten) {
		this.rohstoffkosten = rohstoffkosten;
	}

	/**
	 * @return Umsatzerlöse
	 */
	public double holeUmsatzerloese() {
		return umsatzerloese;
	}

	/**
	 * Setzt die Umsatzerlöse
	 * 
	 * @param umsatzerloese
	 */
	public void setzeUmsatzerloese(double umsatzerloese) {
		this.umsatzerloese = umsatzerloese;
	}

	/**
	 * @return Sonstige Erlöse (z.B. Filialverkauf)
	 */
	public double holeSonstigeErloese() {
		return sonstigeerloese;
	}

	/**
	 * Setzt die sonstigen Erlöse (z.B. Filialverkauf)
	 * 
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
	 * 
	 * @param marktanteil
	 */
	public void setzeMarktanteil(Marktanteil marktanteil) {
		this.marktanteil = marktanteil;
	}

	/**
	 * @return Verkaufsliste mit Anzahl verkaufter Produkte der Runde
	 */
	public VerkaufsListe holeVerkaufsListe() {
		if(verkaufsListe == null) {
			verkaufsListe = new VerkaufsListe(holeKette());
		}
		return verkaufsListe;
	}
	
}
