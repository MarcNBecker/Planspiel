package de.planspiel.konsolengui;

import java.util.Vector;

import de.planspiel.cafe.Unternehmenskette;

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
