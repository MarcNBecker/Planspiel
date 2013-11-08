package de.planspiel.konsolengui;

import de.planspiel.cafe.Unternehmenskette;

/**
 * Interpreter Klasse zur Initialisierung des Spiels.
 * Diese kann neue Spieler hinzuf�gen und im Test-Modus eine Siegbedingung entgegennehmen
 * @author Marc Becker
 */
public class SpielStartenGUI extends KonsolenGUI {

	public void run() {
		writer.println("---------- SPIELER HINZUF�GEN ----------");
		while (true) {
			try {
				String input = reader.readLine();
				String[] teile = input.split(" ", 2);
				String cmd = teile[0].toUpperCase();
				if (cmd.equals("HILFE")) {
					writer.println("---------- HILFE ----------");
					writer.println("Es stehen folgende Kommandos zur Verf�gung (Kommandos in Gro�buchstaben, Parameter in Kamelschreibweise)");
					writer.println("HILFE - Zeigt eine Liste aller Kommandos an");
					writer.println("NEU nameDesSpielers - F�gt einen neuen Spieler mit dem entsprechenden Namen hinzu");
					if (KonsolenGUI.jUnitTestKlasse != null) {
						writer.println("SIEGBEDINGUNG nameDesSpielers - Setzt im TestModus die Siegbedingung");
					}
					writer.println("FERTIG - Beendet das Hinzuf�gen der Spieler");
				} else if (cmd.equals("FERTIG")) {
					writer.println("---------- Spieler hinzuf�gen beendet..Spiel wird gestartet ----------");
					break;
				} else if (cmd.equals("NEU")) {
					String[] param = teile[1].split(",");
					if (param.length == 1) {
						spiel.hinzufuegenUnternehmenskette(new Unternehmenskette(param[0]));
						writer.println("Spieler " + param[0] + " hinzugef�gt");
					} else {
						throw new Exception();
					}
				} else if (cmd.equals("SIEGBEDINGUNG") && KonsolenGUI.jUnitTestKlasse != null) {
					String[] param = teile[1].split(",");
					if (param.length == 1) {
						KonsolenGUI.jUnitTestKlasse.setzeSiegbedingung(param[0]);
						writer.println("Siegbedingung festgelegt: " + param[0] + " ist Sieger");
					} else {
						throw new Exception();
					}
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				writer.println("Kommando nicht erkannt, bitte nutze doch \"HILFE\"");
			}
		}
	}

}
