package de.planspiel.entscheidung;

import de.planspiel.cafe.Unternehmenskette;


public class KreditEntscheidung extends Entscheidung {

	private double betrag;
	
	public KreditEntscheidung(Unternehmenskette kette, double betrag) {
		super(kette);
		this.betrag = betrag;
	}
	
	public void ausfuehren() {
		if(betrag > 0 ){
			kette.aufnehmenKredit(betrag);	
		}
	}

}
