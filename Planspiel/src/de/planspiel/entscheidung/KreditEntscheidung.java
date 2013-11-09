package de.planspiel.entscheidung;

import de.planspiel.cafe.Unternehmenskette;

/**
 * Abstraktion einer Entscheidung zum Aufnehmen eines Kredits
 * 
 * @author Marc Becker
 */
public class KreditEntscheidung extends Entscheidung {

	private double betrag;

	/**
	 * Erzeugt eine neue Entscheidung zur Aufnahme eines Kredits
	 * 
	 * @param kette
	 *            Unternehmenskette, die die Entscheidung getroffen hat
	 * @param betrag
	 *            Betrag, in dessen Höhe der Kredit aufgenommen werden soll
	 */
	public KreditEntscheidung(Unternehmenskette kette, double betrag) {
		super(kette);
		this.betrag = betrag;
	}

	/**
	 * Führt die Entscheidung aus Nimmt einen neuen Kredit auf
	 */
	public void ausfuehren() {
		if (betrag > 0) {
			kette.aufnehmenKredit(betrag);
		}
	}

}
