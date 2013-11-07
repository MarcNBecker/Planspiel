package de.planspiel.entscheidung;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Unternehmenskette;

/**
 * Abstraktion einer Entscheidung zum Verkauf einer Filiale
 * 
 * @author Marc
 */
public class FilialeVerkaufenEntscheidung extends Entscheidung {

	private Filiale filiale;

	/**
	 * Erzeugt eine neue Entscheidung zum Verkauf einer Filiale
	 * 
	 * @param kette
	 *            Unternehmenskette, die die Entscheidung getroffen hat
	 * @param filiale
	 *            Filiale die verkauft werden soll
	 */
	public FilialeVerkaufenEntscheidung(Unternehmenskette kette, Filiale filiale) {
		super(kette);
		this.filiale = filiale;
		new MitarbeiterEntlassenEntscheidung(kette, filiale, filiale.holeMitarbeiter());
	}

	/**
	 * Führt die Entscheidung aus Schließt die Filiale
	 */
	public void ausfuehren() {
		kette.schließenFiliale(filiale);
	}

}
