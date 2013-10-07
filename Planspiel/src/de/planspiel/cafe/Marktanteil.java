package de.planspiel.cafe;

import java.util.HashMap;

import de.planspiel.spiel.Spiel;

/**
 * Klasse zur Organisation von Marktanteilen
 * @author Ann-Kathrin
 *
 */
public class Marktanteil {

	private int gesamtKunden;
	private HashMap<Unternehmenskette, Integer> verkaufsListe;

	/**
	 * Erzeugt einen neuen Marktanteil mit einer HashMap der Größe aller Unternehmenskette
	 */
	public Marktanteil() {
		verkaufsListe = new HashMap<Unternehmenskette, Integer>(Spiel.holeSpiel().holeKettenListe().size());
	}
	
	/**
	 * @return Kundenanzahl die zur Basis des Marktanteils dient
	 */
	public int holeGesamtKunden() {
		return gesamtKunden;
	}
	
	/**
	 * Setzt die Kundenanzahl, die zur Basis des Marktanteils dient
	 * @param gesamtKunden Kundenanzahl größer 0
	 */
	public void setzeGesamtKunden(int gesamtKunden) {
		if(gesamtKunden > 0) {
			this.gesamtKunden = gesamtKunden;
		}
	}
	
	/**
	 * @return Gibt eine Liste mit Verkaufszahlen pro Unternehmen zurück
	 */
	public HashMap<Unternehmenskette, Integer> holeVerkaufsListe() {
		return verkaufsListe;
	}
	
	/**
	 * Berechnte die Marktanteile auf Basis der Verkaufszahlen und gesamten Kunden
	 * @return Liste mit Marktanteilen
	 */
	public HashMap<Unternehmenskette, Double> berechnenMarktanteil() {
		HashMap<Unternehmenskette, Double> marktanteilListe = new HashMap<Unternehmenskette, Double>(this.verkaufsListe.size());
		Unternehmenskette[] kettenListe = (Unternehmenskette[]) verkaufsListe.entrySet().toArray();
		for (int i = 0; i < kettenListe.length; i++) {
			double marktanteil = Math.round(verkaufsListe.get(kettenListe[i]) / this.holeGesamtKunden() * 100.0) / 100.0;
			marktanteilListe.put(kettenListe[i], marktanteil);
		}
		return marktanteilListe;
	}

}
