package de.planspiel.konsolengui;

import java.util.Vector;

import de.planspiel.cafe.Unternehmenskette;

public class SpielBeendenGUI extends KonsolenGUI {

	public void run() {
		System.out.println("---------- SPIEL BEENDET ----------");
		System.out.println("---------- GEWINNER ----------");
		Vector<Unternehmenskette> gewinnerReihenfolge = spiel.bestimmenGewinner();
		for(int i=0; i<gewinnerReihenfolge.size(); i++) {
			int rang = i+1;
			Unternehmenskette kette = gewinnerReihenfolge.get(i);
			System.out.println(rang + ". " + kette.holeName() + " mit einem Gesamtgewinn von " + kette.holeReportListe().get(spiel.holeAktuelleRunde()).berechnenGesamtgewinn());
		}
	}
	
}
