package de.planspiel.entscheidung;

import java.util.Vector;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Unternehmenskette;

/**
 * Abstraktion einer Entscheidung zur Durchführung von Marketing
 * @author Marc
 */
public class MarketingEntscheidung extends Entscheidung {

	private double betrag;
	
	/**
	 * Erstellt eine neue Entscheidung zum Marketing
	 * @param kette Unternehmenskette, die die Entscheidung getroffen hat
	 * @param betrag Betrag, für den Marketing gemacht werden soll, dieser beeinflusst die Wahrscheinlichkeit, dass
	 * 				 ein neuer Kunde das Unternehmen kennenlernt
	 */
	public MarketingEntscheidung(Unternehmenskette kette, double betrag) {
		super(kette);
		this.betrag = betrag;
	}
	
	/**
	 * Führt die Entscheidung aus
	 * Beeinflusst die Kunde an allen Standorten mit einer bestimmten Wahrscheinlichkeit, abhängig vom Betrag
	 */
	public void ausfuehren() {
		if (betrag > 0) {
			Vector<Filiale> filialenListe = kette.holeFilialenListe();
			for (int i=0; i<filialenListe.size(); i++) {
				Filiale filiale = filialenListe.get(i);
				filiale.holeStandort().beeinflussenKunden(kette, holeWahrscheinlichkeit(betrag));
			}	
		}
	}
	
	/**
	 * Bestimmt die Wahrscheinlichkeit, dass ein Kunde eine Unternehmenskette durch Marketing kennen lernt, abhängig
	 * vom Marketing Budget
	 * @param betrag Höhe des Marketing Budgets, welches die Wahrscheinlichkeit beeinflusst
	 * @return Wahrscheinlichkeit zwischen 0 und 1
	 */
	public double holeWahrscheinlichkeit(double betrag) {
		// TODO 
		// hier muss noch eine anständige funktion implementiert werden
		// alternativ wird der marketing betrag einfach festgelegt und die wahrscheinlichkeit ist immer gleich
		// werden automtisch alle standorte vom marketing betroffen? => NEIN STANDORT ÜBERGEBEN!
		return 0.5;
	}

}
