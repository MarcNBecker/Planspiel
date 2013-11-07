package de.planspiel.entscheidung;

import de.planspiel.cafe.Unternehmenskette;
import de.planspiel.spiel.Spiel;

/**
 * Abstrakte Klasse f�r eine Entscheidung Die Funktion dieser ist sich darum zu
 * k�mmern, dass das Spiel alle Entscheidungen mitgeteilt bekommt
 * 
 * @author Marc
 * 
 */
public abstract class Entscheidung {

	protected Unternehmenskette kette;

	/**
	 * Erzeugt eine neue Entscheidung und teilt dem Spiel mit, dass diese
	 * Entscheidung f�r eine bestimmte Kette erzeugt wurde
	 * 
	 * @param kette
	 *            Unternehmenskette, die die Entscheidung getroffen hat
	 */
	public Entscheidung(Unternehmenskette kette) {
		this.kette = kette;
		Spiel.holeSpiel().hinzufuegenRundenEntscheidung(this);
	}

	/**
	 * Diese Methode kann vom Spiel f�r jede Entscheidung aufgerufen werden, um
	 * den Effekt der Entscheidung durchzuf�hren
	 */
	public abstract void ausfuehren();

	/**
	 * @return Unternehmenskette, die die Entscheidung getroffen hat
	 */
	public Unternehmenskette holeKette() {
		return kette;
	}
}
