package de.planspiel.entscheidung;

import java.util.HashMap;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Kostenverursacher;
import de.planspiel.cafe.Unternehmenskette;
import de.planspiel.spiel.Spiel;

/**
 * Abstraktion einer Entscheidung zur Entlassung von Mitarbeitern
 * 
 * @author Marc Becker
 */
public class MitarbeiterEntlassenEntscheidung extends Entscheidung {

	private static HashMap<Unternehmenskette, Integer> einstellungen = new HashMap<Unternehmenskette, Integer>(Spiel.holeSpiel().holeKettenListe().size());

	/**
	 * Addiert einen bestimmten Wert zu der Anzahl der Einstellungen pro
	 * Unternehmenskette
	 * 
	 * @param kette
	 *            Kette, die Mitarbeiter einstellt
	 * @param mitarbeiter
	 *            Anzahl der zusätzlichen Mitarbeiter
	 */
	public static void addiereZuEinstellungen(Unternehmenskette kette, int mitarbeiter) {
		if (kette != null && mitarbeiter > 0) {
			Integer aktuelleEinstellungen = einstellungen.get(kette);
			if (aktuelleEinstellungen == null) {
				aktuelleEinstellungen = new Integer(0);
			}
			einstellungen.put(kette, aktuelleEinstellungen + mitarbeiter);
		}
	}

	private Filiale filiale;
	private int mitarbeiter;

	/**
	 * Erzeugt eine neue Entscheidung zur Entlassung von Mitarbeitern
	 * 
	 * @param kette
	 *            Unternehmenskette, die die Entscheidung getroffen hat
	 * @param filiale
	 *            Filiale, die Mitarbeiter entlässt
	 * @param mitarbeiter
	 *            Anzahl der Mitarbeiter, die entlassen werden sollen
	 */
	public MitarbeiterEntlassenEntscheidung(Unternehmenskette kette, Filiale filiale, int mitarbeiter) {
		super(kette);
		this.filiale = filiale;
		this.mitarbeiter = mitarbeiter;
	}

	/**
	 * Führt die Entscheidung aus Entlässt die Mitarbeiter und verbucht die
	 * Kosten dafür, ggf. werden lediglich Mitarbeiter von einer Abteilung in
	 * die andere umgeschichtet
	 */
	public void ausfuehren() {
		// Bestimme maximale Entlassungen
		int maxEntlassungen = filiale.holeMitarbeiter();
		if (mitarbeiter > maxEntlassungen) {
			this.mitarbeiter = maxEntlassungen;
		}
		if (filiale != null && mitarbeiter > 0) {
			int e = einstellungen.get(holeKette());
			// Bestimme die restlichen Einstellungen, bis zu dieser Grenze sind
			// alle Entlassungen kostenlos
			int restE = e - mitarbeiter;
			// Wenn diese negativ sind, dann müssen Leute entlassen werden
			if (restE < 0) {
				// Keine Einstellungen mehr vorhanden
				einstellungen.put(kette, 0);
				// Verbuche Kosten
				int kostenpflichtigeEntlassungen = Math.abs(restE);
				double betrag = kette.holeEntlassungskosten() * kostenpflichtigeEntlassungen;
				kette.verbuchenKosten(Kostenverursacher.PERSONAL, betrag);
			} else {
				// Es sind noch Einstellungen vorhanden, die die Entlassungen
				// ausgleichen können
				einstellungen.put(kette, restE);
			}
			// Bestimme die letztendliche neue Mitarbeiteranzahl
			filiale.setzeMitarbeiter(filiale.holeMitarbeiter() - mitarbeiter);
		}
	}
}
