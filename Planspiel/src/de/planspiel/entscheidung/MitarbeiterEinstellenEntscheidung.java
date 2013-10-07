package de.planspiel.entscheidung;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Unternehmenskette;


public class MitarbeiterEinstellenEntscheidung extends Entscheidung {

	private Filiale filiale;
	private int mitarbeiter;
	
	public MitarbeiterEinstellenEntscheidung(Unternehmenskette kette, Filiale filiale, int mitarbeiter){
		super(kette);
		this.filiale = filiale;
		this.mitarbeiter = mitarbeiter;
		MitarbeiterEntlassenEntscheidung.addiereZuEinstellungen(kette, mitarbeiter);
	}
	
	public void ausfuehren() {
		if(filiale != null && mitarbeiter > 0) {
			filiale.setzeMitarbeiter(filiale.holeMitarbeiter() + mitarbeiter);
		}
	}

}
