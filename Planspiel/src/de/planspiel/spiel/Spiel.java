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
import de.planspiel.ereignis.ZinsEreignis;
import de.planspiel.konsolengui.EntscheidungTreffenGUI;
import de.planspiel.konsolengui.KonsolenGUI;
import de.planspiel.konsolengui.SpielBeendenGUI;
import de.planspiel.konsolengui.SpielStartenGUI;

public class Spiel {

	public static void main(String[] args) {
		// KonsolenGUI.setzeTestModus(true, "input.txt", "output.txt");
		// Zufall.setzeTestmodus(true);
		// Zufall.setzeDateiTestmodus(true, "zufall.txt");
		Zufall.setzeProtokollModus(true, "zufall.txt");
		new Spiel(5).spielen();
		Zufall.schliessenProtokoll();
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
	 * 
	 * @param rundenzahl
	 *            Zahl der Runden, die das Spiel l�uft
	 */
	public Spiel() {
		Spiel.spiel = this;
		this.rundenzahl = 12; // TODO Start-Wert setzen
		setzeAktuelleRunde(1);
		this.standortListe = new Vector<Standort>();
		this.kettenListe = new Vector<Unternehmenskette>();
		this.haendlerListe = new Vector<Haendler>();
	}

	public Spiel(int rundenzahl) {
		this();
		this.rundenzahl = rundenzahl;
	}

	/**
	 * F�hrt das Spiel durch
	 */
	public void spielen() {
		// Spieler aufnehmen
		new SpielStartenGUI().run();

		// Erzeugen der Standorte
		Standorttyp[] standorte = Standorttyp.values();
		for (int i = 0; i < standorte.length; i++) {
			hinzufuegenStandort(new Standort(standorte[i]));
		}

		// Erzeugen der H�ndler
		Haendlertyp[] haendler = Haendlertyp.values();
		for (int i = 0; i < haendler.length; i++) {
			hinzufuegenHaendler(new Haendler(haendler[i]));
		}

		// Rundenorganisation
		while (holeAktuelleRunde() <= holeRundenzahl()) {
			// Kreditlaufzeit setzen
			Kredit.setzeAktuelleLaufzeit(holeRundenzahl() - (holeAktuelleRunde() - 1));

			// Neue H�ndler Angebote generieren
			for (Haendler h : holeHaendlerListe()) {
				h.generierenAngebot();
			}

			// Marktanteil erzeugen
			setzeAktuellerMarktanteil(new Marktanteil());

			// Report f�r jedes Unternehmen erzeugen
			for (int i = 0; i < holeKettenListe().size(); i++) {
				Unternehmenskette kette = holeKettenListe().get(i);
				// Ignoriere pleite Unternehmen
				if (!kette.holePleite()) {
					Report report = new Report(holeAktuelleRunde(), kette);
					report.setzeMarktanteil(holeAktuellerMarktanteil());
					kette.hinzufuegenReport(report);
				}
			}

			// Entscheidungs HashMap initialisieren
			rundenEntscheidungen = new Vector<Entscheidung>();

			// Entscheidung Treffen GUI anlegen
			EntscheidungTreffenGUI eGUI = new EntscheidungTreffenGUI();

			// Ereignisse starten
			new ZinsEreignis(holeAktuelleRunde(), eGUI).starten();

			// GUI starten
			eGUI.run();

			// Entscheidungen ausf�hren
			for (int i = 0; i < holeRundenEntscheidungen().size(); i++) {
				Entscheidung e = holeRundenEntscheidungen().get(i);
				// Ignoriere pleite Unternehmen
				if (!e.holeKette().holePleite()) {
					e.ausfuehren();
				}
			}

			// Kapazit�ten initialisieren
			for (int i = 0; i < holeKettenListe().size(); i++) {
				Unternehmenskette kette = holeKettenListe().get(i);
				// Ignoriere pleite Unternehmen
				if (!kette.holePleite()) {
					for (int j = 0; j < kette.holeAnzahlFilialen(); j++) {
						Filiale filiale = kette.holeFilialenListe().get(j);
						filiale.initialisierenKapazitaet();
					}
				}
			}

			// Kunden simulieren
			for (int i = 0; i < holeStandortListe().size(); i++) {
				Standort standort = holeStandortListe().get(i);
				for (int j = 0; j < standort.holeKundenkreis().size(); j++) {
					Kunde kunde = standort.holeKundenkreis().get(j);
					kunde.simulierenEinkauf();
				}
			}

			// Unternehmenskosten berechnen und Runde abschlie�en
			for (int i = 0; i < holeKettenListe().size(); i++) {
				Unternehmenskette kette = holeKettenListe().get(i);
				// Ignoriere pleite Unternehmen
				if (!kette.holePleite()) {
					kette.berechnenKosten();
					Report report = kette.holeReportListe().get(holeAktuelleRunde() - 1);
					report.abschlie�enRunde();
				}
			}

			// Runde neu setzen
			setzeAktuelleRunde(holeAktuelleRunde() + 1);
		}

		new SpielBeendenGUI().run();
		KonsolenGUI.close();
	}

	/**
	 * Berechnet die Reihenfolge der Unternehmensketten, abh�ngig von ihrem
	 * Gesamtgewinn Die erste Kette in dem zur�ckgegebenen Vector hat den
	 * gr��ten Gesamtgewinn
	 * 
	 * @return Vector mit Unternehmensketten, die erste Kette hat den gr��ten,
	 *         die letzte den kleinsten Gesamtgewinn
	 */
	public Vector<Unternehmenskette> bestimmenGewinner() {
		Vector<Unternehmenskette> reihenfolge = new Vector<Unternehmenskette>();
		for (int i = 0; i < holeKettenListe().size(); i++) {
			Unternehmenskette aktuelleKette = holeKettenListe().get(i);
			if (reihenfolge.size() == 0) {
				reihenfolge.add(aktuelleKette);
			} else {
				for (int j = 0; j < reihenfolge.size(); j++) {
					Unternehmenskette aktuelleKetteInListe = reihenfolge.get(j);
					double gesamtgewinnInListe = aktuelleKetteInListe.holeReportListe().lastElement().berechnenGesamtgewinn();
					double gesamtgewinnAktuell = aktuelleKette.holeReportListe().lastElement().berechnenGesamtgewinn();
					if (gesamtgewinnInListe < gesamtgewinnAktuell) {
						reihenfolge.add(j, aktuelleKette);
						break;
					} else if (j == (reihenfolge.size() - 1)) {
						reihenfolge.add(aktuelleKette);
						break;
					}
				}
			}
		}
		return reihenfolge;
	}

	/**
	 * @return Gibt das aktuelle Spiel zur�ck
	 */
	public static Spiel holeSpiel() {
		return spiel;
	}

	/**
	 * @return Gibt die aktuelle Rundenzahl zur�ck
	 */
	public int holeRundenzahl() {
		return rundenzahl;
	}

	/**
	 * @return Aktuelle Rundennummer. Die erste Rundennummer ist 1, die letzte
	 *         Rundennummer wird durch Rundenzahl bestimmt
	 */
	public int holeAktuelleRunde() {
		return aktuelleRunde;
	}

	/**
	 * Setzt die neue aktuelle Runde, die z.B. f�r die Kreditlaufzeit oder den
	 * Report-Vektor genutzt wird
	 * 
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
	 * F�gt einen neuen Standort hinzu
	 * 
	 * @param standort
	 *            Standort ungleich null
	 */
	public void hinzufuegenStandort(Standort standort) {
		if (standort != null) {
			standortListe.add(standort);
		}
	}

	/**
	 * @return Liste mit allen H�ndlern
	 */
	public Vector<Haendler> holeHaendlerListe() {
		return haendlerListe;
	}

	/**
	 * F�gt einen H�ndler hinu
	 * 
	 * @param haendler
	 *            H�ndler ungleich null
	 */
	public void hinzufuegenHaendler(Haendler haendler) {
		if (haendler != null) {
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
	 * F�gt ein Unternehmen dem Spiel hinzu
	 * 
	 * @param kette
	 *            Unternehmenskette ungleich null
	 */
	public void hinzufuegenUnternehmenskette(Unternehmenskette kette) {
		if (kette != null) {
			kettenListe.add(kette);
		}
	}

	/**
	 * @return Gibt die Entscheidungen jeder Kette in dieser Runde zur�ck
	 */
	public Vector<Entscheidung> holeRundenEntscheidungen() {
		return rundenEntscheidungen;
	}

	/**
	 * F�gt eine Entscheidungder Entscheidungsliste hinzu
	 * 
	 * @param e
	 *            hinzuzuf�gende Entscheidung
	 */
	public void hinzufuegenRundenEntscheidung(Entscheidung e) {
		rundenEntscheidungen.add(e);
	}

	public Marktanteil holeAktuellerMarktanteil() {
		return aktuellerMarktanteil;
	}

	public void setzeAktuellerMarktanteil(Marktanteil aktuellerMarktanteil) {
		this.aktuellerMarktanteil = aktuellerMarktanteil;
	}

}
