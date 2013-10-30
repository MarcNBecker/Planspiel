package de.planspiel.ereignis;

import de.planspiel.cafe.Kredit;
import de.planspiel.spiel.Zufall;

/**
 * Proof of concept für ein Eregnis
 * @author D059166
 *
 */
public class ZinsEreignis implements Ereignis {

	private int runde;
	
	public ZinsEreignis(int runde) {
		this.runde = runde;
	}
	
	@Override
	public void starten() {
		if(Zufall.treffenEntscheidung(2.0/3.0) || holeRunde() == 1) {
			double zusatz = Zufall.generierenNVZufallszahl() / 100.0;
			Kredit.setzeAktuellerZinssatz(Kredit.holeAktuellerZinssatz() + zusatz);
			if(Kredit.holeAktuellerZinssatz() < 0) {
				Kredit.setzeAktuellerZinssatz(0.001);
			}
			System.out.println("Ereignis: Zinssatz liegt jetzt bei " + (Math.round(Kredit.holeAktuellerZinssatz() * 10000.0) / 100.0) + "%"); //TODO Hier müssten eigentlich die Daten an die GUI weitergegeben werden
		}
	}
	
	public int holeRunde() {
		return runde;
	}

}
