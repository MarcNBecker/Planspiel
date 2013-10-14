package de.planspiel.konsolengui;

import java.util.HashMap;

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

public class EntscheidungTreffenGUI extends KonsolenGUI {

	public void run() {
		for(int i=0; i<spiel.holeKettenListe().size(); i++){
			Unternehmenskette kette = spiel.holeKettenListe().get(i);
			System.out.println("---------- ENTSCHEIDUNG TREFFEN FÜR "+ kette.holeName() +" ----------");
			if(kette.holePleite()) {
				System.out.println(kette.holeName() + " ist leider pleite :(");
				continue;
			}
			while(true){
				try {
					String input = reader.readLine();
					String[] teile = input.split(" ");
					String cmd = teile[0].toUpperCase();
					if(cmd.equals("HILFE")){
						System.out.println("----------  Hilfe ----------");
						System.out.println("Es stehen folgende Kommandos zur Verfügung (Kommandos in Großbuchstaben, Parameter in Kamelschreibweise)");
						System.out.println("HILFE ------------------------------------------------- Zeigt eine Liste aller Kommandos an");
						System.out.println("REPORT ------------------------------------------------ Zeigt den Report des Spielers mit allen Informationen an");
						System.out.println("STANDORT ---------------------------------------------- Zeigt eine Liste aller Standorte an");
						System.out.println("HÄNDLER ----------------------------------------------- Zeigt eine Liste aller Händler an mit deren Angeboten");
						System.out.println("FILIALE ----------------------------------------------- Zeigt eine Liste aller Filialen an");
						System.out.println("LAGER ------------------------------------------------- Zeigt den Lagerbestand an");
						System.out.println("FILIALE_ERÖFFNEN standortName,mitarbeiterAnzahl ------- Erzeugt eine Filiale am angegebenen Standort mit der entsprechend Mitarbeiteranzahl");
						System.out.println("FILIALE_VERKAUFEN standortName ------------------------ Verkauft die Filiale am Standort");
						System.out.println("KREDIT_AUFNEHMEN betrag ------------------------------- Nimmt einen Kredit in der angegebenen Höhe auf");
						System.out.println("MARKETING_DURCHFÜHREN standortName,betrag ------------- Führt Marketing für den angegebenen Betrag am Standort durch");
						System.out.println("MITARBEITER_EINSTELLEN standortName,mitarbeiterAnzahl - Stellt die Anzahl Mitarbeiter in der Filiale am Standort ein");
						System.out.println("MITARBEITER_ENTLASSEN standortName,mitarbeiterAnzahl -- Entlässt die Anzahl Mitarbeiter in der Filiale am Standort");
						System.out.println("ROHSTOFF_EINKAUFEN händlerName,produktName,menge ------ Kauft das entsprechende Produkt in der entsprechenden Menge beim Händler ein");
						System.out.println("VERKAUFSPREIS_SETZEN produktName,preis ---------------- Setzt den Preis für das entsprechende Produkt");
						System.out.println("FERTIG ------------------------------------------------ Beendet das Treffen der Entscheidungen und lässt den nächsten Spieler dran");
					} else if (cmd.equals("FERTIG")) {
						System.out.println("---------- Entscheidungsprozess für "+ kette.holeName() +" fertig..nächster Spieler ist dran ----------");
						break;
					} else if (cmd.equals("REPORT")) {
						// Report der Vorrunde
						Report report;
						if(spiel.holeAktuelleRunde() == 1){
							report = kette.holeReportListe().get(0);
						} else {
							report = kette.holeReportListe().get(spiel.holeAktuelleRunde()-2);	
						}
						System.out.println("---------- Report ---------- ");
						System.out.println("Kasse: " + runden(report.holeKasse(), 2) + " Euro");
						System.out.println("Fremdkapital: " + runden(report.holeEndFremdkapital(), 2) + " Euro");
						System.out.println("Gesamtkapital: " + runden(report.holeEndGesamtkapital(), 2) + " Euro\n"); //Umbruch
						System.out.println("Filialanschaffungskosten: " + runden(report.holeAnschaffungskosten(), 2) + " Euro");
						System.out.println("Filalunterhaltungskosten: " + runden(report.holeUnterhaltungskosten(), 2) + " Euro");
						System.out.println("Personalkosten: " + runden(report.holePersonalkosten(), 2) + " Euro");
						System.out.println("Rohstoffkosten: " + runden(report.holeRohstoffkosten(), 2) + " Euro");	
						System.out.println("Marketingkosten: " + runden(report.holeMarketingkosten(), 2) + " Euro");					
						System.out.println("Kreditkosten: " + runden(report.holeKreditkosten(), 2) + " Euro");
						System.out.println("Umsatzerlöse: " + runden(report.holeUmsatzerloese(), 2) + " Euro");
						System.out.println("Sonstige Erlöse: " + runden(report.holeSonstigeErloese(), 2) + " Euro");
						System.out.println("Gewinn: " + runden(report.berechnenGewinn(), 2) + " Euro bzw. Rundenergebnis: " + runden(report.berechnenRundenergebnis(), 2) + " Euro"); 
						System.out.println("Gesamtgewinn: " + runden(report.berechnenGesamtgewinn(), 2) + " Euro\n");//Umbruch
						for(int j=0; j<report.holeVerkaufsListe().holeProduktliste().size(); j++){
							Produkt produkt = report.holeVerkaufsListe().holeProduktliste().get(j);
							System.out.println("Verkaufzahlen " + produkt.holeName() + ": " + produkt.holeMenge());
							System.out.println(); //Umbruch
						}
						HashMap<Unternehmenskette, Double> marktanteilMap = report.holeMarktanteil().berechnenMarktanteil();
						Unternehmenskette[] keys = marktanteilMap.keySet().toArray(new Unternehmenskette[marktanteilMap.size()]);
						for (int j=0; j<keys.length; j++){
							System.out.println("Marktanteil " + keys[j].holeName() + ": " + runden(marktanteilMap.get(keys[j]), 4));
						}						
					} else if (cmd.equals("STANDORT")) {
						System.out.println("---------- Standorte ----------");
						for(int j=0; j<spiel.holeStandortListe().size(); j++) {
							Standort standort = spiel.holeStandortListe().get(j);
							System.out.println(standort.holeName());
						}
					} else if (cmd.equals("HÄNDLER")) {
						System.out.println("---------- Händler ----------");
						for(int j=0; j<spiel.holeHaendlerListe().size(); j++) {
							Haendler haendler = spiel.holeHaendlerListe().get(j);
							System.out.println(haendler.holeName()+":");
							for(int k=0; k<haendler.holeProduktliste().size(); k++) {
								Produkt produkt = haendler.holeProduktliste().get(k);
								System.out.println(produkt.holeName() + " mit Qualität " + runden(produkt.holeQualitaet(), 4) + " zu Einkaufspreis " + runden(produkt.holeEkpreis(), 2) + " Euro");
							}
						}
					} else if (cmd.equals("FILIALE")) {
						System.out.println("---------- Filialen ----------");
						for(int j=0; j<kette.holeFilialenListe().size(); j++){
							Filiale filiale = kette.holeFilialenListe().get(j);
							System.out.println("Filiale am Standort " + filiale.holeStandort().holeName() + " mit " + filiale.holeMitarbeiter() + " Mitarbeitern und einer Kapazität von " + filiale.holeStartKapazitaet());
						}
					} else if (cmd.equals("LAGER")) {
						Lager lager = kette.holeLager();
						System.out.println("---------- Lagerbestand ----------");
						for(int j=0; j<lager.holeProduktliste().size(); j++) {
							Produkt produkt = lager.holeProduktliste().get(j);
							System.out.println(produkt.holeMenge() + " " + produkt.holeName() + " zu Einkaufspreis " + runden(produkt.holeEkpreis(), 2) + " Euro mit Qualität " + runden(produkt.holeQualitaet(), 4) + " und Verkaufspreis " + runden(produkt.holePreis(), 2) + " Euro");
						}
					} else if (cmd.equals("FILIALE_ERÖFFNEN")) {
						String[] param = teile[1].split(",");
						if(param.length == 2){
							new FilialeEroeffnenEntscheidung(kette, findeStandort(param[0]), Integer.parseInt(param[1]));
							System.out.println("Filiale am Standort " + param[0] + " mit " + param[1] + " Mitarbeitern gebaut");
						} else {
							throw new Exception();
						}
					} else if (cmd.equals("FILIALE_VERKAUFEN")) {
						String[] param = teile[1].split(",");
						if(param.length == 1){
							new FilialeVerkaufenEntscheidung(kette, findeFiliale(kette, findeStandort(param[0])));
							System.out.println("Filiale am Standort " + param[0] + " verkauft");
						} else {
							throw new Exception();
						}						
					} else if (cmd.equals("KREDIT_AUFNEHMEN")) {
						String[] param = teile[1].split(",");
						if(param.length == 1){
							new KreditEntscheidung(kette, Double.parseDouble(param[0]));
							System.out.println("Kredit über " + param[0] + " Euro aufgenommen");
						} else {
							throw new Exception();
						}						
					} else if (cmd.equals("MARKETING_DURCHFÜHREN")) {
						String[] param = teile[1].split(",");
						if(param.length == 2){
							new MarketingEntscheidung(kette, findeStandort(param[0]), Double.parseDouble(param[1]));
							System.out.println("Marketing am Standort " + param[0] + " mit " + param[1] + " Euro durchgeführt");
						} else {
							throw new Exception();
						}
					} else if (cmd.equals("MITARBEITER_EINSTELLEN")) {
						String[] param = teile[1].split(",");
						if(param.length == 2){
							new MitarbeiterEinstellenEntscheidung(kette, findeFiliale(kette, findeStandort(param[0])), Integer.parseInt(param[1]));
							System.out.println(param[1] + " Mitarbeiter in Filiale am Standort " + param[0] + " eingestellt");
						} else {
							throw new Exception();
						}	
					} else if (cmd.equals("MITARBEITER_ENTLASSEN")) {
						String[] param = teile[1].split(",");
						if(param.length == 2){
							new MitarbeiterEntlassenEntscheidung(kette, findeFiliale(kette, findeStandort(param[0])), Integer.parseInt(param[1]));
							System.out.println(param[1] + " Mitarbeiter in Filiale am Standort " + param[0] + " entlassen");
						} else {
							throw new Exception();
						}	
					} else if (cmd.equals("ROHSTOFF_EINKAUFEN")) {
						String[] param = teile[1].split(",");
						if(param.length == 3){
							new RohstoffEntscheidung(kette, findeHaendler(param[0]), Produkttyp.valueOf(param[1]), Integer.parseInt(param[2]));
							System.out.println("Produkt " + param[1] + " von " + param[0] + " in der Menge " + param[2] + " eingekauft");
						} else {
							throw new Exception();
						}
					} else if (cmd.equals("VERKAUFSPREIS_SETZEN")) {
						String[] param = teile[1].split(",");
						if(param.length == 2){
							new VerkaufspreisEntscheidung(kette, Produkttyp.valueOf(param[0]), Double.parseDouble(param[1]));
							System.out.println("Verkaufspreis für " + param[0] + " auf " + param[1] + " Euro gesetzt");
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
		System.out.println("---------- Entscheidungen wurden getroffen..Spiel simuliert jetzt.. ----------");
		System.out.println("---------- SIMULATION GESTARTET ----------");
	}
	
	private Standort findeStandort(String name) throws Exception {
		for(int i=0; i<spiel.holeStandortListe().size(); i++) {
			Standort s = spiel.holeStandortListe().get(i);
			if(s.holeName().toUpperCase().equals(name.toUpperCase())){
				return s;
			}
		}
		throw new Exception();
	}
	
	private Filiale findeFiliale(Unternehmenskette kette, Standort standort) throws Exception {
		for(int i=0; i<standort.holeFilialenListe().size(); i++) {
			Filiale f = standort.holeFilialenListe().get(i);
			if(f.holeKette() == kette) {
				return f;
			}
		}
		throw new Exception();
	}
	
	private Haendler findeHaendler(String name) throws Exception {
		for(int i=0; i<spiel.holeHaendlerListe().size(); i++) {
			Haendler h = spiel.holeHaendlerListe().get(i);
			if(h.holeName().toUpperCase().equals(name.toUpperCase())){
				return h;
			}
		}
		throw new Exception();
	}
	
	private double runden(double i, int s) {
		//return (double)(((int)Math.round(i * 100.0)) / 100);
		return Math.round(i*10.0*s) / (10.0*s);
	}
	
}
