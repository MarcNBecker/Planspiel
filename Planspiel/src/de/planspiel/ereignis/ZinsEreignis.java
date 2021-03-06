package de.planspiel.ereignis;

import de.planspiel.cafe.Kredit;
import de.planspiel.konsolengui.EntscheidungTreffenGUI;
import de.planspiel.spiel.Zufall;

/**
 * Proof of concept f�r ein Eregnis
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

	/**
	 * F�hrt das Ereignis aus in Runde 1. Bei allen anderen Runden bestimmt der
	 * Zufall, ob das Ereignis ausgef�hrt wird oder nicht. Bei Ausf�hrung wird
	 * ein neuer Zinssatz f�r Kredite festgelegt.
	 */
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

	/**
	 * @return Liefert einen String zur�ck, der �ber den aktuellen Zinssatz f�r
	 *         Kredite Auskunft gibt
	 */
	@Override
	public String toString() {
		return "Ereignis: Zinssatz liegt jetzt bei " + (Math.round(Kredit.holeAktuellerZinssatz() * 10000.0) / 100.0) + "%";
	}

	/**
	 * 
	 * @return Liefert die Runde der Entscheidung zur�ck
	 */
	public int holeRunde() {
		return runde;
	}

}
