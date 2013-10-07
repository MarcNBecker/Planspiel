package de.planspiel.entscheidung;

import de.planspiel.cafe.Unternehmenskette;
import de.planspiel.spiel.Spiel;

public abstract class Entscheidung {
	
	protected Unternehmenskette kette;
	
	public Entscheidung(Unternehmenskette kette) {
		Spiel.holeSpiel().hinzufuegenRundenEntscheidung(this);
	}
	
	public abstract void ausfuehren();

	public Unternehmenskette holeKette() {
		return kette;
	}
}
