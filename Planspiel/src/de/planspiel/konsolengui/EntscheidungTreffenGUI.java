package de.planspiel.konsolengui;

import java.util.HashMap;
import java.util.Vector;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Haendler;
import de.planspiel.cafe.Lager;
import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Report;
import de.planspiel.cafe.Standort;
import de.planspiel.cafe.Unternehmenskette;
import de.planspiel.entscheidung.FilialeEroeffnenEntscheidung;
import de.planspiel.entscheidung.FilialeVerkaufenEntscheidung;
import de.planspiel.entscheidung.KreditEntscheidung;
import de.planspiel.entscheidung.MarketingEntscheidung;
import de.planspiel.entscheidung.MitarbeiterEinstellenEntscheidung;
import de.planspiel.entscheidung.MitarbeiterEntlassenEntscheidung;
import de.planspiel.entscheidung.RohstoffEntscheidung;
import de.planspiel.entscheidung.VerkaufspreisEntscheidung;
import de.planspiel.ereignis.Ereignis;

/**
 * Interpreter der DSL f�r alle Entscheidungsspezifischen Befehle Hier werden
 * alle Befehle interpretiert, die w�hrend einer Spielrunde vom Spiel
 * entgegengenommen werden k�nnen
 * 
 * @author Marc Becker
 * 
 */

public class EntscheidungTreffenGUI extends KonsolenGUI {

	private Vector<Ereignis> ereignisse = new Vector<Ereignis>();
	
	/**
	 * @see de.planspiel.konsolengui.KonsolenGUI#run()
	 */
	@Override
	public void run() {
		for (Ereignis e : ereignisse) {
			writer.println(e.toString());
		}
		for (int i = 0; i < spiel.holeKettenListe().size(); i++) {
			Unternehmenskette kette = spiel.holeKettenListe().get(i);
			writer.println("---------- ENTSCHEIDUNG TREFFEN F�R " + kette.holeName() + " ----------");
			if (kette.holePleite()) {
				writer.println(kette.holeName() + " ist leider pleite :(");
				continue;
			}
			while (true) {
				try {
					String input = reader.readLine();
					String[] teile = input.split(" ");
					String cmd = teile[0].toUpperCase();
					if (cmd.equals("HILFE")) {
						writer.println("----------  Hilfe ----------");
						writer.println("Es stehen folgende Kommandos zur Verf�gung (Kommandos in Gro�buchstaben, Parameter in Kamelschreibweise)");
						writer.println("HILFE ------------------------------------------------- Zeigt eine Liste aller Kommandos an");
						writer.println("REPORT ------------------------------------------------ Zeigt den Report des Spielers mit allen Informationen an");
						writer.println("STANDORT ---------------------------------------------- Zeigt eine Liste aller Standorte an");
						writer.println("H�NDLER ----------------------------------------------- Zeigt eine Liste aller H�ndler an mit deren Angeboten");
						writer.println("FILIALE ----------------------------------------------- Zeigt eine Liste aller Filialen an");
						writer.println("LAGER ------------------------------------------------- Zeigt den Lagerbestand an");
						writer.println("FILIALE_ER�FFNEN standortName,mitarbeiterAnzahl ------- Erzeugt eine Filiale am angegebenen Standort mit der entsprechend Mitarbeiteranzahl");
						writer.println("FILIALE_VERKAUFEN standortName ------------------------ Verkauft die Filiale am Standort");
						writer.println("KREDIT_AUFNEHMEN betrag ------------------------------- Nimmt einen Kredit in der angegebenen H�he auf");
						writer.println("MARKETING_DURCHF�HREN standortName,betrag ------------- F�hrt Marketing f�r den angegebenen Betrag am Standort durch");
						writer.println("MITARBEITER_EINSTELLEN standortName,mitarbeiterAnzahl - Stellt die Anzahl Mitarbeiter in der Filiale am Standort ein");
						writer.println("MITARBEITER_ENTLASSEN standortName,mitarbeiterAnzahl -- Entl�sst die Anzahl Mitarbeiter in der Filiale am Standort");
						writer.println("ROHSTOFF_EINKAUFEN h�ndlerName,produktName,menge ------ Kauft das entsprechende Produkt in der entsprechenden Menge beim H�ndler ein");
						writer.println("VERKAUFSPREIS_SETZEN produktName,preis ---------------- Setzt den Preis f�r das entsprechende Produkt");
						writer.println("FERTIG ------------------------------------------------ Beendet das Treffen der Entscheidungen und l�sst den n�chsten Spieler dran");
					} else if (cmd.equals("FERTIG")) {
						writer.println("---------- Entscheidungsprozess f�r " + kette.holeName() + " fertig..n�chster Spieler ist dran ----------");
						break;
					} else if (cmd.equals("REPORT")) {
						// Report der Vorrunde
						Report report;
						if (spiel.holeAktuelleRunde() == 1) {
							report = kette.holeReportListe().get(0);
						} else {
							report = kette.holeReportListe().get(spiel.holeAktuelleRunde() - 2);
						}
						writer.println("---------- Report ---------- ");
						writer.println("Kasse: " + runden(report.holeKasse(), 2) + " Euro");
						writer.println("Fremdkapital: " + runden(report.holeEndFremdkapital(), 2) + " Euro");
						writer.println("Gesamtkapital: " + runden(report.holeEndGesamtkapital(), 2) + " Euro\n"); // Umbruch
						writer.println("Filialanschaffungskosten: " + runden(report.holeAnschaffungskosten(), 2) + " Euro");
						writer.println("Filalunterhaltungskosten: " + runden(report.holeUnterhaltungskosten(), 2) + " Euro");
						writer.println("Personalkosten: " + runden(report.holePersonalkosten(), 2) + " Euro");
						writer.println("Rohstoffkosten: " + runden(report.holeRohstoffkosten(), 2) + " Euro");
						writer.println("Marketingkosten: " + runden(report.holeMarketingkosten(), 2) + " Euro");
						writer.println("Kreditkosten: " + runden(report.holeKreditkosten(), 2) + " Euro");
						writer.println("Umsatzerl�se: " + runden(report.holeUmsatzerloese(), 2) + " Euro");
						writer.println("Sonstige Erl�se: " + runden(report.holeSonstigeErloese(), 2) + " Euro");
						writer.println("Gewinn: " + runden(report.berechnenGewinn(), 2) + " Euro");
						writer.println("Gesamtgewinn: " + runden(report.berechnenGesamtgewinn(), 2) + " Euro\n");// Umbruch
						for (int j = 0; j < report.holeVerkaufsListe().holeProduktliste().size(); j++) {
							Produkt produkt = report.holeVerkaufsListe().holeProduktliste().get(j);
							writer.println("Verkaufzahlen " + produkt.holeName() + ": " + produkt.holeMenge());
							writer.println(); // Umbruch
						}
						HashMap<Unternehmenskette, Double> marktanteilMap = report.holeMarktanteil().berechnenMarktanteil();
						Unternehmenskette[] keys = marktanteilMap.keySet().toArray(new Unternehmenskette[marktanteilMap.size()]);
						for (int j = 0; j < keys.length; j++) {
							writer.println("Marktanteil " + keys[j].holeName() + ": " + runden(marktanteilMap.get(keys[j]), 4));
						}
					} else if (cmd.equals("STANDORT")) {
						writer.println("---------- Standorte ----------");
						for (int j = 0; j < spiel.holeStandortListe().size(); j++) {
							Standort standort = spiel.holeStandortListe().get(j);
							writer.println(standort.holeName());
						}
					} else if (cmd.equals("H�NDLER")) {
						writer.println("---------- H�ndler ----------");
						for (int j = 0; j < spiel.holeHaendlerListe().size(); j++) {
							Haendler haendler = spiel.holeHaendlerListe().get(j);
							writer.println(haendler.holeName() + ":");
							for (int k = 0; k < haendler.holeProduktliste().size(); k++) {
								Produkt produkt = haendler.holeProduktliste().get(k);
								writer.println(produkt.holeName() + " mit Qualit�t " + runden(produkt.holeQualitaet(), 4) + " zu Einkaufspreis " + runden(produkt.holeEkpreis(), 2) + " Euro");
							}
						}
					} else if (cmd.equals("FILIALE")) {
						writer.println("---------- Filialen ----------");
						for (int j = 0; j < kette.holeFilialenListe().size(); j++) {
							Filiale filiale = kette.holeFilialenListe().get(j);
							writer.println("Filiale am Standort " + filiale.holeStandort().holeName() + " mit " + filiale.holeMitarbeiter() + " Mitarbeitern und einer Kapazit�t von " + filiale.holeStartKapazitaet());
						}
					} else if (cmd.equals("LAGER")) {
						Lager lager = kette.holeLager();
						writer.println("---------- Lagerbestand ----------");
						for (int j = 0; j < lager.holeProduktliste().size(); j++) {
							Produkt produkt = lager.holeProduktliste().get(j);
							writer.println(produkt.holeMenge() + " " + produkt.holeName() + " zu Einkaufspreis " + runden(produkt.holeEkpreis(), 2) + " Euro mit Qualit�t " + runden(produkt.holeQualitaet(), 4) + " und Verkaufspreis " + runden(produkt.holePreis(), 2) + " Euro");
						}
					} else if (cmd.equals("FILIALE_ER�FFNEN")) {
						String[] param = teile[1].split(",");
						if (param.length == 2) {
							new FilialeEroeffnenEntscheidung(kette, findenStandort(param[0]), Integer.parseInt(param[1]));
							writer.println("Filiale am Standort " + param[0] + " mit " + param[1] + " Mitarbeitern gebaut");
						} else {
							throw new Exception();
						}
					} else if (cmd.equals("FILIALE_VERKAUFEN")) {
						String[] param = teile[1].split(",");
						if (param.length == 1) {
							new FilialeVerkaufenEntscheidung(kette, findenFiliale(kette, findenStandort(param[0])));
							writer.println("Filiale am Standort " + param[0] + " verkauft");
						} else {
							throw new Exception();
						}
					} else if (cmd.equals("KREDIT_AUFNEHMEN")) {
						String[] param = teile[1].split(",");
						if (param.length == 1) {
							new KreditEntscheidung(kette, Double.parseDouble(param[0]));
							writer.println("Kredit �ber " + param[0] + " Euro aufgenommen");
						} else {
							throw new Exception();
						}
					} else if (cmd.equals("MARKETING_DURCHF�HREN")) {
						String[] param = teile[1].split(",");
						if (param.length == 2) {
							new MarketingEntscheidung(kette, findenStandort(param[0]), Double.parseDouble(param[1]));
							writer.println("Marketing am Standort " + param[0] + " mit " + param[1] + " Euro durchgef�hrt");
						} else {
							throw new Exception();
						}
					} else if (cmd.equals("MITARBEITER_EINSTELLEN")) {
						String[] param = teile[1].split(",");
						if (param.length == 2) {
							new MitarbeiterEinstellenEntscheidung(kette, findenFiliale(kette, findenStandort(param[0])), Integer.parseInt(param[1]));
							writer.println(param[1] + " Mitarbeiter in Filiale am Standort " + param[0] + " eingestellt");
						} else {
							throw new Exception();
						}
					} else if (cmd.equals("MITARBEITER_ENTLASSEN")) {
						String[] param = teile[1].split(",");
						if (param.length == 2) {
							new MitarbeiterEntlassenEntscheidung(kette, findenFiliale(kette, findenStandort(param[0])), Integer.parseInt(param[1]));
							writer.println(param[1] + " Mitarbeiter in Filiale am Standort " + param[0] + " entlassen");
						} else {
							throw new Exception();
						}
					} else if (cmd.equals("ROHSTOFF_EINKAUFEN")) {
						String[] param = teile[1].split(",");
						if (param.length == 3) {
							new RohstoffEntscheidung(kette, findenHaendler(param[0]), Produkttyp.valueOf(param[1]), Integer.parseInt(param[2]));
							writer.println("Produkt " + param[1] + " von " + param[0] + " in der Menge " + param[2] + " eingekauft");
						} else {
							throw new Exception();
						}
					} else if (cmd.equals("VERKAUFSPREIS_SETZEN")) {
						String[] param = teile[1].split(",");
						if (param.length == 2) {
							new VerkaufspreisEntscheidung(kette, Produkttyp.valueOf(param[0]), Double.parseDouble(param[1]));
							writer.println("Verkaufspreis f�r " + param[0] + " auf " + param[1] + " Euro gesetzt");
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
		writer.println("---------- Entscheidungen wurden getroffen..Spiel simuliert jetzt.. ----------");
		writer.println("---------- SIMULATION GESTARTET ----------");
	}

	/**
	 * Findet einen Standort anhand des Standort Namens
	 * @param name Name des Standort
	 * @return Standort
	 * @throws Exception wenn zu dem angegebenen Namen kein Standort existiert
	 */
	private Standort findenStandort(String name) throws Exception {
		for (int i = 0; i < spiel.holeStandortListe().size(); i++) {
			Standort s = spiel.holeStandortListe().get(i);
			if (s.holeName().toUpperCase().equals(name.toUpperCase())) {
				return s;
			}
		}
		throw new Exception();
	}

	/**
	 * Findet die Filiale einer Unternehmenskette an einem Standort
	 * @param kette Die Unternehmenskette
	 * @param standort Der Standort, wo die Filiale sich befinden soll
	 * @return die Filiale
	 * @throws Exception Wirft eine Exception, wenn an dem angegebenen Standort keine Filiale existiert
	 */
	private Filiale findenFiliale(Unternehmenskette kette, Standort standort) throws Exception {
		for (int i = 0; i < standort.holeFilialenListe().size(); i++) {
			Filiale f = standort.holeFilialenListe().get(i);
			if (f.holeKette() == kette) {
				return f;
			}
		}
		throw new Exception();
	}

	/**
	 * Findet den entsprechenden H�ndler anhand des Namens
	 * @param name Name des H�ndlers
	 * @return Der H�ndler
	 * @throws Exception Wirft eine Exception, wenn kein H�ndler mit dem Namen existiert
	 */
	private Haendler findenHaendler(String name) throws Exception {
		for (int i = 0; i < spiel.holeHaendlerListe().size(); i++) {
			Haendler h = spiel.holeHaendlerListe().get(i);
			if (h.holeName().toUpperCase().equals(name.toUpperCase())) {
				return h;
			}
		}
		throw new Exception();
	}

	/**
	 * Rundet eine Zahl
	 * @param i die Zahl
	 * @param s Anzahl der Stellen, die nach dem Runden erhalten bleiben sollen
	 * @return die gerundete Zahl
	 */
	private double runden(double i, int s) {
		return Math.round(i * Math.pow(10, s)) / Math.pow(10, s);
		// return i;
	}
	
	/**
	 * F�gt ein Ereignis dem Interpreter zur Ausgabe hinzu
	 * @param e das Ereignis
	 */
	public void hinzufuegenEreignis(Ereignis e) {
		ereignisse.add(e);
	}

}