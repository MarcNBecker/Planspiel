package de.planspiel.entscheidung;

import de.planspiel.cafe.*;

/**
 * Abstraktion einer Entscheidung zur Eröffnung einer Filiale
 * @author Marc
 */
public class FilialeEroeffnenEntscheidung extends Entscheidung {

	private Standort standort;
	private int mitarbeiter;
	
	/**
	 * Erzeugt eine neue Entscheidung zum Eröffnen einer Filiale
	 * @param kette Unternehmenskette, die die Entscheidung getroffen hat
	 * @param standort Der Standort an dem die neue Filiale eröffnet werden soll
	 * @param mitarbeiter Die Anzahl der Mitarbeiter, die in die Filiale eingestellt werden sollen
	 */
	public FilialeEroeffnenEntscheidung(Unternehmenskette kette, Standort standort, int mitarbeiter) {
		super(kette);
		this.standort = standort;
		this.mitarbeiter = mitarbeiter;
		//Entlassungen mitteilen, dass Mitarbeiter eingestellt wurden
		MitarbeiterEntlassenEntscheidung.addiereZuEinstellungen(kette, mitarbeiter);
	}
	
	/**
	 * Führt die Entscheidung aus
	 * Eröffnet eine neue Filiale und setzt die Mitarbeiteranzahl
	 */
	public void ausfuehren() {
		if(standort != null && mitarbeiter >= 0) {
			Filiale neueFiliale = kette.eroeffnenFiliale(standort);
			neueFiliale.setzeMitarbeiter(mitarbeiter);	
		}
	}

}
