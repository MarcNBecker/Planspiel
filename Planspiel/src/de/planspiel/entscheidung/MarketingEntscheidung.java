package de.planspiel.entscheidung;

import java.util.Vector;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Unternehmenskette;


public class MarketingEntscheidung extends Entscheidung {

	private double betrag;
	
	public MarketingEntscheidung(Unternehmenskette kette, double betrag) {
		super(kette);
		this.betrag = betrag;
	}
	
	public void ausfuehren() {
		if (betrag > 0) {
			Vector<Filiale> filialenListe = kette.holeFilialenListe();
			for (int i=0; i<filialenListe.size(); i++) {
				Filiale filiale = filialenListe.get(i);
				filiale.holeStandort().beeinflussenKunden(kette, holeWahrscheinlichkeit(betrag));
			}	
		}
	}
	
	public double holeWahrscheinlichkeit(double betrag) {
		// hier muss noch eine anständige funktion implementiert werden
		// alternativ wird der marketing betrag einfach festgelegt und die wahrscheinlichkeit ist immer gleich
		return 0.5;
	}

}
