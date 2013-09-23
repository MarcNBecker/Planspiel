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

	public void setzeMarktanteilListe(
			HashMap<Unternehmenskette, Integer> verkaufsListe) {
		this.verkaufsListe = verkaufsListe;
	}
	
	public HashMap<Unternehmenskette, Double> berechnenMarktanteil()
	{
		HashMap<Unternehmenskette,Double> marktanteilListe = new HashMap<Unternehmenskette,Double>(this.verkaufsListe.size());
		Unternehmenskette[] kettenListe = (Unternehmenskette[]) marktanteilListe.entrySet().toArray();
		Object[] zwischenListe = marktanteilListe.values().toArray();
		int[] anzahlKunden = new int[zwischenListe.length];
		for(int i = 0; i < zwischenListe.length - 1; i++)
		{
			anzahlKunden[i] = Integer.parseInt(zwischenListe[i].toString());
		}
		for(int i = 0; i < this.verkaufsListe.size(); i++)
		{
			double marktanteil =  Math.round(anzahlKunden[i] / this.gesamtKunden * 100.) / 100.;
			marktanteilListe.put(kettenListe[i], marktanteil);
		}
		return marktanteilListe;
	}

}
