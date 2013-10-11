package de.planspiel.konsolengui;

import de.planspiel.cafe.Unternehmenskette;

public class SpielStartenGUI extends KonsolenGUI {	

	public void run() {
		System.out.println("---------- SPIELER HINZUFÜGEN ----------");
		while(true){
			try {
				String input = reader.readLine();
				String[] teile = input.split(" ");
				String cmd = teile[0].toUpperCase();
				if(cmd.equals("HILFE")){
					System.out.println("Es stehen folgende Kommandos zur Verfügung (Kommandos in Großbuchstaben, Parameter in Kamelschreibweise)");
					System.out.println("HILFE - Zeigt eine Liste aller Kommandos an");
					System.out.println("NEU nameDesSpielers - Fügt einen neuen Spieler mit dem entsprechenden Namen hinzu");
					System.out.println("FERTIG - Beendet das Hinzufügen der Spieler");
				} else if (cmd.equals("FERTIG")) {
					System.out.println("..Spieler hinzufügen beendet..Spiel wird gestartet");
					break;
				} else if (cmd.equals("NEU")) {
					String[] param = teile[1].split(",");
					if(param.length == 1){
						spiel.hinzufuegenUnternehmenskette(new Unternehmenskette(param[0]));
					} else {
						throw new Exception();
					}
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Kommando nicht erkannt, bitte nutze doch \"HILFE\"");
			}
		}
	}
	
}
