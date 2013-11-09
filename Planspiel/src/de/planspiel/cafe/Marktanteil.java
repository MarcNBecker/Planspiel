package de.planspiel.cafe;

import java.util.HashMap;

import de.planspiel.spiel.Spiel;

/**
 * Klasse zur Organisation von Marktanteilen
 * 
 * @author Ann-Kathrin Gessat
 * 
 */
public class Marktanteil {

	private int gesamtKunden;
	private HashMap<Unternehmenskette, Integer> verkaufsListe;

	/**
	 * Erzeugt einen neuen Marktanteil mit einer HashMap der Größe aller
	 * Unternehmenskette
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
	 * Fügt das Produkt, das verkauft wurde, in die Verkaufsliste der
	 * Unternehmenskette ein, zählt die Gesamtanzahl an Kunden und die Anzahl
	 * der Kunden der Unternehmenskette um eins hoch und fügt diese Werte
	 * ebenfalls in die Verkaufsliste ein.
	 * 
	 * @param kette
	 *            Unternehmenskette, die etwas verkauft hat
	 */
	public void mitteilenVerkauf(Unternehmenskette kette) {
		if (!verkaufsListe.containsKey(kette)) {
			verkaufsListe.put(kette, 0);
		}
		int anzahlKundenKette = verkaufsListe.get(kette);
		gesamtKunden++;
		anzahlKundenKette++;
		verkaufsListe.put(kette, anzahlKundenKette);
	}

	/**
	 * @return Gibt eine Liste mit Verkaufszahlen pro Unternehmen zurück
	 */
	public HashMap<Unternehmenskette, Integer> holeVerkaufsListe() {
		return verkaufsListe;
	}

	/**
	 * Berechnte die Marktanteile auf Basis der Verkaufszahlen und gesamten
	 * Kunden
	 * 
	 * @return Liste mit Marktanteilen
	 */
	public HashMap<Unternehmenskette, Double> berechnenMarktanteil() {
		HashMap<Unternehmenskette, Double> marktanteilListe = new HashMap<Unternehmenskette, Double>(this.verkaufsListe.size());
		Unternehmenskette[] kettenListe = verkaufsListe.keySet().toArray(new Unternehmenskette[marktanteilListe.size()]);
		for (int i = 0; i < kettenListe.length; i++) {
			double marktanteil = (double) verkaufsListe.get(kettenListe[i]) / (double) this.holeGesamtKunden();
			marktanteilListe.put(kettenListe[i], marktanteil);
		}
		return marktanteilListe;
	}

}
