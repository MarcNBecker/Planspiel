package de.planspiel.entscheidung;

import java.util.Vector;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Unternehmenskette;

/**
 * Abstraktion einer Entscheidung zur Durchf�hrung von Marketing
 * @author Marc
 */
public class MarketingEntscheidung extends Entscheidung {

	private double betrag;
	
	/**
	 * Erstellt eine neue Entscheidung zum Marketing
	 * @param kette Unternehmenskette, die die Entscheidung getroffen hat
	 * @param betrag Betrag, f�r den Marketing gemacht werden soll, dieser beeinflusst die Wahrscheinlichkeit, dass
	 * 				 ein neuer Kunde das Unternehmen kennenlernt
	 */
	public MarketingEntscheidung(Unternehmenskette kette, double betrag) {
		super(kette);
		this.betrag = betrag;
	}
	
	/**
	 * F�hrt die Entscheidung aus
	 * Beeinflusst die Kunde an allen Standorten mit einer bestimmten Wahrscheinlichkeit, abh�ngig vom Betrag
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
	 * Bestimmt die Wahrscheinlichkeit, dass ein Kunde eine Unternehmenskette durch Marketing kennen lernt, abh�ngig
	 * vom Marketing Budget
	 * @param betrag H�he des Marketing Budgets, welches die Wahrscheinlichkeit beeinflusst
	 * @return Wahrscheinlichkeit zwischen 0 und 1
	 */
	public double holeWahrscheinlichkeit(double betrag) {
		// TODO 
		// hier muss noch eine anst�ndige funktion implementiert werden
		// alternativ wird der marketing betrag einfach festgelegt und die wahrscheinlichkeit ist immer gleich
		// werden automtisch alle standorte vom marketing betroffen? => NEIN STANDORT �BERGEBEN!
		return 0.5;
	}

}
