package de.planspiel.cafe;

import java.util.HashMap;

public class Marktanteil {

	private int gesamtKunden;
	private HashMap<Unternehmenskette, Integer> verkaufsListe;

	public Marktanteil() {
		verkaufsListe = new HashMap<Unternehmenskette, Integer>(Spiel
				.holeSpiel().holeKettenListe().size());
	}

	public int holeGesamtKunden() {
		return gesamtKunden;
	}

	public void setzeGesamtKunden(int gesamtKunden) {
		this.gesamtKunden = gesamtKunden;
	}

	public HashMap<Unternehmenskette, Integer> holeVerkaufsListe() {
		return verkaufsListe;
	}

	public HashMap<Unternehmenskette, Double> berechnenMarktanteil() {
		HashMap<Unternehmenskette, Double> marktanteilListe = new HashMap<Unternehmenskette, Double>(
				this.verkaufsListe.size());
		Unternehmenskette[] kettenListe = (Unternehmenskette[]) verkaufsListe
				.entrySet().toArray();
		for (int i = 0; i < kettenListe.length; i++) {
			double marktanteil = Math.round(verkaufsListe.get(kettenListe[i]) / this.holeGesamtKunden()
					* 100.0) / 100.0;
			marktanteilListe.put(kettenListe[i], marktanteil);
		}
		return marktanteilListe;
	}

}
