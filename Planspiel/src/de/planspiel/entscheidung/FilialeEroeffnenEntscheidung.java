package de.planspiel.entscheidung;

import de.planspiel.cafe.*;

public class FilialeEroeffnenEntscheidung extends Entscheidung {

	private Standort standort;
	private int mitarbeiter;
	
	public FilialeEroeffnenEntscheidung(Unternehmenskette kette, Standort standort, int mitarbeiter) {
		super(kette);
		this.standort = standort;
		this.mitarbeiter = mitarbeiter;
		MitarbeiterEntlassenEntscheidung.addiereZuEinstellungen(kette, mitarbeiter);
	}
	
	public void ausfuehren() {
		if(standort != null && mitarbeiter >= 0) {
			Filiale neueFiliale = kette.eroeffnenFiliale(standort);
			neueFiliale.setzeMitarbeiter(mitarbeiter);	
		}
	}

}
