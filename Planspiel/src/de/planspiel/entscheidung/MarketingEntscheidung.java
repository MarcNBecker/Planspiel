package de.planspiel.entscheidung;

import de.planspiel.cafe.Kostenverursacher;
import de.planspiel.cafe.Standort;
import de.planspiel.cafe.Unternehmenskette;

/**
 * Abstraktion einer Entscheidung zur Durchführung von Marketing
 * 
 * @author Marc Becker
 */
public class MarketingEntscheidung extends Entscheidung {

	private double betrag;
	private Standort standort;
	private double minBetrag = 1000.0;
	private double maxBetrag = 10000.0;

	/**
	 * Erstellt eine neue Entscheidung zum Marketing
	 * 
	 * @param kette
	 *            Unternehmenskette, die die Entscheidung getroffen hat
	 * @param betrag
	 *            Betrag, für den Marketing gemacht werden soll, dieser
	 *            beeinflusst die Wahrscheinlichkeit, dass ein neuer Kunde das
	 *            Unternehmen kennenlernt
	 */
	public MarketingEntscheidung(Unternehmenskette kette, Standort standort, double betrag) {
		super(kette);
		this.standort = standort;
		this.betrag = betrag;
	}

	/**
	 * Führt die Entscheidung aus Beeinflusst die Kunden am ausgewählten
	 * Standort mit einer bestimmten Wahrscheinlichkeit, abhängig vom Betrag
	 */
	public void ausfuehren() {
		if (betrag > 0) {
			standort.beeinflussenKunden(kette, holeWahrscheinlichkeit(betrag));
			kette.verbuchenKosten(Kostenverursacher.MARKETING, betrag);
		}
	}

	/**
	 * Bestimmt die Wahrscheinlichkeit, dass ein Kunde eine Unternehmenskette am
	 * Standort durch Marketing kennenlernt, abhängig vom Marketing Budget
	 * 
	 * @param betrag
	 *            Höhe des Marketing Budgets, welches die Wahrscheinlichkeit
	 *            beeinflusst
	 * @return Wahrscheinlichkeit zwischen 0 und 1
	 */
	public double holeWahrscheinlichkeit(double betrag) {
		double b = 0.2;
		double maxWahrscheinlichkeit = 0.8;
		double m = (maxWahrscheinlichkeit - b) / (maxBetrag - minBetrag);
		double wahrscheinlichkeit;
		if (betrag < minBetrag) {
			wahrscheinlichkeit = 0.0;
		} else if (betrag > maxBetrag) {
			wahrscheinlichkeit = maxWahrscheinlichkeit;
		} else {
			wahrscheinlichkeit = (m * betrag) + b;
		}
		return wahrscheinlichkeit;
	}
}
