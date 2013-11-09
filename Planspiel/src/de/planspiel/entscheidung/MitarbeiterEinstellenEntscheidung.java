package de.planspiel.entscheidung;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Unternehmenskette;

/**
 * Abstraktion einer Entscheidung zur Einstellung von Mitarbeitern
 * 
 * @author Marc Becker
 */
public class MitarbeiterEinstellenEntscheidung extends Entscheidung {

	private Filiale filiale;
	private int mitarbeiter;

	/**
	 * Erstellt eine neue Entscheidung zur Einstellung von Mitarbeitern
	 * 
	 * @param kette
	 *            Unternehmenskette, die die Entscheidung getroffen hat
	 * @param filiale
	 *            Filiale, die die neuen Mitarbeiter entgegen nimmt
	 * @param mitarbeiter
	 *            Anzahl der Mitarbeiter, die eingestellt werden sollen
	 */
	public MitarbeiterEinstellenEntscheidung(Unternehmenskette kette, Filiale filiale, int mitarbeiter) {
		super(kette);
		this.filiale = filiale;
		this.mitarbeiter = mitarbeiter;
		MitarbeiterEntlassenEntscheidung.addiereZuEinstellungen(kette, mitarbeiter);
	}

	/**
	 * Führt die Entscheidung aus Stellt die Mitarbeiter ein
	 */
	public void ausfuehren() {
		if (filiale != null && mitarbeiter > 0) {
			filiale.setzeMitarbeiter(filiale.holeMitarbeiter() + mitarbeiter);
		}
	}

}
