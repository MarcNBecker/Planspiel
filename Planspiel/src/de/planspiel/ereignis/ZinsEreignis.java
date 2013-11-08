package de.planspiel.ereignis;

import de.planspiel.cafe.Kredit;
import de.planspiel.konsolengui.EntscheidungTreffenGUI;
import de.planspiel.spiel.Zufall;

/**
 * Proof of concept für ein Eregnis
 * 
 * @author Marc Becker
 * 
 */
public class ZinsEreignis implements Ereignis {

	private int runde;
	private EntscheidungTreffenGUI gui;

	public ZinsEreignis(int runde, EntscheidungTreffenGUI gui) {
		this.runde = runde;
		this.gui = gui;
	}

	@Override
	public void starten() {
		if (Zufall.treffenEntscheidung(2.0 / 3.0) || holeRunde() == 1) {
			double zusatz = Zufall.generierenNVZufallszahl() / 100.0;
			Kredit.setzeAktuellerZinssatz(Kredit.holeAktuellerZinssatz() + zusatz);
			if (Kredit.holeAktuellerZinssatz() < 0) {
				Kredit.setzeAktuellerZinssatz(0.001);
			}
			gui.hinzufuegenEreignis(this);
		}
	}

	public String toString() {
		return "Ereignis: Zinssatz liegt jetzt bei " + (Math.round(Kredit.holeAktuellerZinssatz() * 10000.0) / 100.0) + "%";
	}

	public int holeRunde() {
		return runde;
	}

}
