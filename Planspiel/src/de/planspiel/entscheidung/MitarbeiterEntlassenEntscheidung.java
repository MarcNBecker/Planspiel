package de.planspiel.entscheidung;

import java.util.HashMap;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Kostenverursacher;
import de.planspiel.cafe.Unternehmenskette;
import de.planspiel.spiel.Spiel;


public class MitarbeiterEntlassenEntscheidung extends Entscheidung {

	private static HashMap<Unternehmenskette, Integer> einstellungen;
	
	static {
		einstellungen = new HashMap<Unternehmenskette, Integer>(Spiel.holeSpiel().holeKettenListe().size());
	}
	
	public static void addiereZuEinstellungen(Unternehmenskette kette, int mitarbeiter) {
		if (kette != null && mitarbeiter > 0) {
			int aktuelleEinstellungen = einstellungen.get(kette);
			einstellungen.put(kette, aktuelleEinstellungen+mitarbeiter);	
		}
	}
	
	private Filiale filiale;
	private int mitarbeiter;

	public MitarbeiterEntlassenEntscheidung(Unternehmenskette kette, Filiale filiale, int mitarbeiter) {
		super(kette);
		this.filiale = filiale;
		this.mitarbeiter = mitarbeiter;
	}
	
	public void ausfuehren() {
		int maxEntlassungen = filiale.holeMitarbeiter();
		if (mitarbeiter > maxEntlassungen){
			this.mitarbeiter = maxEntlassungen;
		}
		if(filiale != null && mitarbeiter > 0) {
			int e = einstellungen.get(kette);
			int restE = e - mitarbeiter;
			if (restE < 0) {
				einstellungen.put(kette, 0);
				int kostenpflichtigeEntlassungen = Math.abs(restE);
				double betrag = kette.holeEntlassungskosten() * kostenpflichtigeEntlassungen;
				kette.verbuchenKosten(Kostenverursacher.PERSONAL, betrag);
			} else {
				einstellungen.put(kette, restE);	
			}
			filiale.setzeMitarbeiter(filiale.holeMitarbeiter() - mitarbeiter);
		}
	}
}
