package de.planspiel.konsolengui;

import java.util.Vector;

import de.planspiel.cafe.Unternehmenskette;

/**
 * Interpreter, der keine Befehle entgegennimmt, aber vom Spiel zum Ende
 * aufgerufen wird, um das Ergebnis an den globalen Interpreter out-Stream
 * weiterzugeben
 * 
 * @author Marc Becker
 */
public class SpielBeendenGUI extends KonsolenGUI {

	public void run() {
		writer.println("---------- SPIEL BEENDET ----------");
		writer.println("---------- GEWINNER ----------");
		Vector<Unternehmenskette> gewinnerReihenfolge = spiel.bestimmenGewinner();
		for (int i = 0; i < gewinnerReihenfolge.size(); i++) {
			int rang = i + 1;
			Unternehmenskette kette = gewinnerReihenfolge.get(i);
			if (KonsolenGUI.jUnitTestKlasse != null && rang == 1) {
				KonsolenGUI.jUnitTestKlasse.setzeSiegerfuellung(kette.holeName());
			}
			writer.println(rang + ". " + kette.holeName() + " mit einem Gesamtgewinn von " + kette.holeReportListe().lastElement().berechnenGesamtgewinn());
		}
	}

}
