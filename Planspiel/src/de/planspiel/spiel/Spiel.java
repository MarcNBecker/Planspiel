package de.planspiel.spiel;

import java.util.Vector;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Haendler;
import de.planspiel.cafe.Kredit;
import de.planspiel.cafe.Kunde;
import de.planspiel.cafe.Marktanteil;
import de.planspiel.cafe.Report;
import de.planspiel.cafe.Standort;
import de.planspiel.cafe.Standorttyp;
import de.planspiel.cafe.Haendlertyp;
import de.planspiel.cafe.Unternehmenskette;
import de.planspiel.entscheidung.Entscheidung;
import de.planspiel.konsolengui.EntscheidungTreffenGUI;
import de.planspiel.konsolengui.SpielBeendenGUI;
import de.planspiel.konsolengui.SpielStartenGUI;

public class Spiel {

	public static void main(String[] args) {
		new Spiel().spielen();
	}

	private static Spiel spiel;
	private int rundenzahl;
	private int aktuelleRunde;
	private Vector<Standort> standortListe;
	private Vector<Haendler> haendlerListe;
	private Vector<Unternehmenskette> kettenListe;
	private Vector<Entscheidung> rundenEntscheidungen;
	private Marktanteil aktuellerMarktanteil;
	
	/**
	 * Startet ein neues Spiel
	 * @param rundenzahl Zahl der Runden, die das Spiel läuft
	 */
	public Spiel() {
		Spiel.spiel = this;
		this.rundenzahl = 10; // TODO Start-Wert setzen
		setzeAktuelleRunde(1);
		this.standortListe = new Vector<Standort>();
		this.kettenListe = new Vector<Unternehmenskette>();
		this.haendlerListe = new Vector<Haendler>();
	}
	
	/**
	 * Führt das Spiel durch
	 */
	public void spielen() {
		// Erzeugen der Standorte
		Standorttyp[] standorte = Standorttyp.values();
		for(int i=0; i<standorte.length; i++){
			hinzufuegenStandort(new Standort(standorte[i]));
		}
		
		// Erzeugen der Händler
		Haendlertyp[] haendler = Haendlertyp.values();
		for(int i=0; i<haendler.length; i++) {
			hinzufuegenHaendler(new Haendler(haendler[i]));
		}
		
		new SpielStartenGUI().run();
		
		// Rundenorganisation
		while(holeAktuelleRunde() <= holeRundenzahl()) {
			// Kreditlaufzeit setzen
			Kredit.setzeAktuelleLaufzeit(holeRundenzahl() - (holeAktuelleRunde() - 1));
			
			// Marktanteil erzeugen
			setzeAktuellerMarktanteil(new Marktanteil());
			
			// Report für jedes Unternehmen erzeugen
			for(int i=0; i<holeKettenListe().size(); i++) {
				Unternehmenskette kette = holeKettenListe().get(i);
				// Ignoriere pleite Unternehmen
				if (!kette.holePleite()) {
					Report report = new Report(holeAktuelleRunde(), kette);
					report.setzeMarktanteil(holeAktuellerMarktanteil());
					report.setzeStartGesamtkapital(kette.berechnenGesamtkapital());
					report.setzeStartFremdkapital(kette.berechnenFremdkapital());
					kette.hinzufuegenReport(report);
				}
			}
			
			// Entscheidungs HashMap initialisieren
			rundenEntscheidungen = new Vector<Entscheidung>();
						
			new EntscheidungTreffenGUI().run();
			
			// Entscheidungen ausführen
			for(int i=0; i<holeRundenEntscheidungen().size(); i++){
				Entscheidung e = holeRundenEntscheidungen().get(i);
				// Ignoriere pleite Unternehmen
				if(!e.holeKette().holePleite()) {
					e.ausfuehren();
				}
			}
			
			// Kapazitäten initialisieren
			for(int i=0; i<holeKettenListe().size(); i++) {
				Unternehmenskette kette = holeKettenListe().get(i);
				// Ignoriere pleite Unternehmen
				if(!kette.holePleite()) {
					for(int j=0; j<kette.holeAnzahlFilialen(); j++){
						Filiale filiale = kette.holeFilialenListe().get(j);
						filiale.initialisierenKapazitaet();
					}
				}
			}
			
			// Kunden simulieren
			for(int i=0; i<holeStandortListe().size(); i++) {
				Standort standort = holeStandortListe().get(i);
				for(int j=0; j<standort.holeKundenkreis().size(); j++){
					Kunde kunde = standort.holeKundenkreis().get(j);
					kunde.simulierenEinkauf();
				}
			}
			
			// Unternehmenskosten berechnen und Runde abschließen
			for(int i=0; i<holeKettenListe().size(); i++) {
				Unternehmenskette kette = holeKettenListe().get(i);
				// Ignoriere pleite Unternehmen
				if(!kette.holePleite()) {
					kette.berechnenKosten();
					Report report = kette.holeReportListe().get(holeAktuelleRunde() - 1);
					report.abschließenRunde();
				}
			}
			
			// Runde neu setzen
			setzeAktuelleRunde(holeAktuelleRunde() + 1);
		}
		
		new SpielBeendenGUI().run();
		
	}
	
	/**
	 * Berechnet die Reihenfolge der Unternehmensketten, abhängig von ihrem Gesamtgewinn
	 * Die erste Kette in dem zurückgegebenen Vector hat den größten Gesamtgewinn
	 * @return Vector mit Unternehmensketten, die erste Kette hat den größten, die letzte den kleinsten Gesamtgewinn
	 */
	public Vector<Unternehmenskette> bestimmenGewinner() {
		Vector<Unternehmenskette> reihenfolge = new Vector<Unternehmenskette>();
		for(int i=0; i<holeKettenListe().size(); i++) {
			Unternehmenskette aktuelleKette = holeKettenListe().get(i);
			if(reihenfolge.size() == 0) {
				reihenfolge.add(aktuelleKette);
			} else {
				for(int j=0; j<reihenfolge.size(); j++) {
					Unternehmenskette aktuelleKetteInListe = reihenfolge.get(i);
					double gesamtgewinnInListe = aktuelleKetteInListe.holeReportListe().get(holeAktuelleRunde()).berechnenGesamtgewinn();
					double gesamtgewinnAktuell = aktuelleKette.holeReportListe().get(holeAktuelleRunde()).berechnenGesamtgewinn();
					if(gesamtgewinnInListe < gesamtgewinnAktuell) {
						reihenfolge.add(j, aktuelleKette);
						break;
					}
				}
			}
		}
		return reihenfolge;
	}
	
	/**
	 * @return Gibt das aktuelle Spiel zurück
	 */
	public static Spiel holeSpiel(){
		return spiel;
	}
	
	
	/**
	 * @return Gibt die aktuelle Rundenzahl zurück
	 */
	public int holeRundenzahl() {
		return rundenzahl;
	}
	
	/**
	 * @return Aktuelle Rundennummer. Die erste Rundennummer ist 1, die letzte Rundennummer wird durch Rundenzahl bestimmt
	 */
	public int holeAktuelleRunde() {
		return aktuelleRunde;
	}
	
	/**
	 * Setzt die neue aktuelle Runde, die z.B. für die Kreditlaufzeit oder den Report-Vektor genutzt wird
	 * @param aktuelleRunde
	 */
	public void setzeAktuelleRunde(int aktuelleRunde) {
		this.aktuelleRunde = aktuelleRunde;
	}
	
	/**
	 * @return Liste mit allen Standorten
	 */
	public Vector<Standort> holeStandortListe() {
		return standortListe;
	}

	/**
	 * Fügt einen neuen Standort hinzu
	 * @param standort Standort ungleich null
	 */
	public void hinzufuegenStandort(Standort standort) {
		if(standort != null) {
			standortListe.add(standort);
		}
	}

	/**
	 * @return Liste mit allen Händlern
	 */
	public Vector<Haendler> holeHaendlerListe() {
		return haendlerListe;
	}
	
	/**
	 * Fügt einen Händler hinu
	 * @param haendler Händler ungleich null
	 */
	public void hinzufuegenHaendler(Haendler haendler) {
		if(haendler != null) {
			haendlerListe.add(haendler);
		}
	}
	
	/**
	 * @return Liste aller Unternehmensketten
	 */
	public Vector<Unternehmenskette> holeKettenListe() {
		return kettenListe;
	}
	
	/**
	 * Fügt ein Unternehmen dem Spiel hinzu
	 * @param kette Unternehmenskette ungleich null
	 */
	public void hinzufuegenUnternehmenskette(Unternehmenskette kette) {
		if(kette != null) {
			kettenListe.add(kette);
		}
	}
	
	/**
	 * @return Gibt die Entscheidungen jeder Kette in dieser Runde zurück
	 */
	public Vector<Entscheidung> holeRundenEntscheidungen() {
		return rundenEntscheidungen;
	}
	
	/**
	 * Fügt eine Entscheidungder Entscheidungsliste hinzu
	 * @param e hinzuzufügende Entscheidung
	 */
	public void hinzufuegenRundenEntscheidung(Entscheidung e){
		rundenEntscheidungen.add(e);
	}
	
	public Marktanteil holeAktuellerMarktanteil() {
		return aktuellerMarktanteil;
	}
	
	public void setzeAktuellerMarktanteil(Marktanteil aktuellerMarktanteil) {
		this.aktuellerMarktanteil = aktuellerMarktanteil;
	}

}
