package de.planspiel.entscheidung;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Unternehmenskette;


public class FilialeVerkaufenEntscheidung extends Entscheidung {

	private Filiale filiale;
	
	public FilialeVerkaufenEntscheidung(Unternehmenskette kette, Filiale filiale) {
		super(kette);
		this.filiale = filiale;
		new MitarbeiterEntlassenEntscheidung(kette, filiale, filiale.holeMitarbeiter());
	}
	
	public void ausfuehren() {
		kette.schließenFiliale(filiale);
	}

}
