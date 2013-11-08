package de.planspiel.cafe;

import java.util.Vector;

import de.planspiel.spiel.Zufall;

/**
 * Klasse zur Organisation des Kunden
 * 
 * @author Daniel, Max, Marc Becker
 */
public class Kunde {

	private Vector<Unternehmenskette> kettenListe;
	private Vector<Produkt> produkte;
	private Praeferenz praeferenz;
	private Standort standort;

	/**
	 * Erstellt einen neuen Kunden.
	 * 
	 * @param standort
	 *            Gibt an, an welchem Standort der Kunde agiert.
	 * @param praeferenz
	 *            Gibt an, welche Praefernez der Kunde hat (siehe ENUM
	 *            Praeferenz)
	 */
	public Kunde(Standort standort) {
		this.kettenListe = new Vector<Unternehmenskette>();
		this.produkte = new Vector<Produkt>();
		this.standort = standort;

		// Bestimme Präferenz
		double zahl = Zufall.generierenZufallszahl(3);
		Praeferenz praeferenz = null;
		if (zahl <= 1.0)
			praeferenz = Praeferenz.PREIS;
		else if (zahl <= 2.0)
			praeferenz = Praeferenz.QUALITAET;
		else if (zahl <= 3.0)
			praeferenz = Praeferenz.AVG;
		setzePraeferenz(praeferenz);

		Produkttyp[] produktTypen = Produkttyp.values();
		Produkt neuesProdukt;
		double zufallsQualitaet;
		double zufallsAddition;
		// zufallszahlWahrscheinlichkeit = Wahrscheinlichkeit mit der Produkt 2,
		// 3, 4, etc.
		// geprüft werden ob sie ebenfalls aufgenommen werden sollen
		double zufallsWahrscheinlichkeit = Zufall.generierenZufallszahl(1);
		int zufallsProdukt = (int) Zufall.generierenZufallszahl(produktTypen.length);
		// 1. Produkt hinzufügen
		neuesProdukt = new Produkt(produktTypen[zufallsProdukt], 1);
		// Bereich von 4 bis 7
		zufallsQualitaet = Zufall.generierenQualitaet();
		zufallsAddition = Zufall.generierenZufallszahl(1 - zufallsQualitaet);
		neuesProdukt.setzeQualitaet(zufallsQualitaet);
		neuesProdukt.setzePreis(produktTypen[zufallsProdukt].holeMaxVK() * PreisQualitaetVerhaeltnis.berechnenPreisFaktor(zufallsQualitaet + zufallsAddition));
		hinzufuegenProdukt(neuesProdukt);
		// 2., 3., 4., etc. zufällig hinzufügen
		for (int i = 0; i < produktTypen.length; i++) {
			if (i != zufallsProdukt) {
				if (Zufall.treffenEntscheidung(zufallsWahrscheinlichkeit)) {
					neuesProdukt = new Produkt(produktTypen[i], 1);
					// Bereich von 4 bis 7
					zufallsQualitaet = Zufall.generierenQualitaet();
					zufallsAddition = Zufall.generierenZufallszahl(1 - zufallsQualitaet);
					neuesProdukt.setzeQualitaet(zufallsQualitaet);
					neuesProdukt.setzePreis(produktTypen[zufallsProdukt].holeMaxVK() * PreisQualitaetVerhaeltnis.berechnenPreisFaktor(zufallsQualitaet + zufallsAddition));
					hinzufuegenProdukt(neuesProdukt);
				}
			}
		}
	}

	/**
	 * Der Kunde lernt eine neue Unternehmenskette kennen, insofern er sie nicht
	 * schon kennt.
	 * 
	 * @param kette
	 *            Gibt an, welche Unternehmenskette der Kunde kennenlernen soll.
	 */
	public void kennenlernen(Unternehmenskette kette) {
		if (!(holeKettenListe().contains(kette))) {
			hinzufuegenUnternehmenskette(kette);
		}
	}

	/**
	 * Der Kunde kauft ein.
	 */
	public void simulierenEinkauf() {
		// Unternehmensketten herausfiltern, die eine Filiale am Standort des
		// Kunden haben.
		// filialenListe = alle Filialen die an dem Standort des Kunden sind
		Vector<Filiale> filialenListe = holeStandort().holeFilialenListe();
		Vector<Filiale> favoriten = new Vector<Filiale>();

		Produkt hauptProduktKunde = holeProdukte().get(0); // Prio 1 Produkt

		for (int i = 0; i < filialenListe.size(); i++) {
			Filiale filiale = filialenListe.get(i);
			Unternehmenskette filialenKette = filiale.holeKette();
			Produkt hauptProduktInFiliale = filialenKette.holeLager().suchenProdukt(hauptProduktKunde.holeName());

			if (!filialenKette.holePleite() // Ist die Filiale nicht pleite?
					&& holeKettenListe().contains(filialenKette) // Kennt der
																	// Kunde die
																	// Kette?
					&& hauptProduktKunde.vergleichen(hauptProduktInFiliale) // Existiert
																			// das
																			// Produkt
																			// in
																			// der
																			// entsprechenden
																			// Qualität
																			// und
																			// Preis?
					&& hauptProduktInFiliale.holeMenge() > 0 // Ist das Produkt
																// noch
																// verfügbar?
					&& filiale.holeFreieKapazitaet() > 0) { // Ist noch Freie
															// Kapazität in der
															// Filiale?

				favoriten.add(filiale);
			}
		}

		// 3 Fälle - Die Favoritenliste hat ein Element, hat mehr als 1 Element
		// oder kein Element
		Filiale verkaufsFiliale = null;
		if (favoriten.size() == 1) {
			verkaufsFiliale = favoriten.get(0);
		} else if (favoriten.size() > 1) {
			// favoritenliste hat mehrere Unternehmenskette

			// Die erste Filiale in der Favoritenliste ist pauschal der erste
			// Favorit.
			Filiale favorit = favoriten.get(0);
			Produkt hauptProduktFavorit = favorit.holeKette().holeLager().suchenProdukt(hauptProduktKunde.holeName());
			Vector<Filiale> gleichheitsListe = new Vector<Filiale>();
			gleichheitsListe.add(favorit);

			switch (holePraeferenz()) {
			case PREIS: {
				// Durchlaufe restliche Favoriten
				for (int i = 1; i < favoriten.size(); i++) {
					// Bestimme den nächsten Herausforderer
					Filiale herausforderer = favoriten.get(i);
					Produkt hauptProduktHerausforderer = herausforderer.holeKette().holeLager().suchenProdukt(hauptProduktKunde.holeName());
					// Sind beide exakt gleich füge den Herausforderer zur
					// GleichheitsListe
					if (hauptProduktFavorit.holePreis() == hauptProduktHerausforderer.holePreis()) {
						gleichheitsListe.add(herausforderer);
						// Ansosten prüfe ob der Herausforderer besser ist
					} else if (hauptProduktFavorit.holePreis() > hauptProduktHerausforderer.holePreis()) {
						// Er ist besser, also mach ihn zum Favorit
						favorit = herausforderer;
						hauptProduktFavorit = favorit.holeKette().holeLager().suchenProdukt(hauptProduktKunde.holeName());
						gleichheitsListe.removeAllElements();
						gleichheitsListe.add(favorit);
					}
				}
				break;
			}
			case QUALITAET: {
				// Durchlaufe restliche Favoriten
				for (int i = 1; i < favoriten.size(); i++) {
					// Bestimme den nächsten Herausforderer
					Filiale herausforderer = favoriten.get(i);
					Produkt hauptProduktHerausforderer = herausforderer.holeKette().holeLager().suchenProdukt(hauptProduktKunde.holeName());
					// Sind beide exakt gleich füge den Herausforderer zur
					// GleichheitsListe
					if (hauptProduktFavorit.holeQualitaet() == hauptProduktHerausforderer.holeQualitaet()) {
						gleichheitsListe.add(herausforderer);
						// Ansosten prüfe ob der Herausforderer besser ist
					} else if (hauptProduktFavorit.holeQualitaet() < hauptProduktHerausforderer.holeQualitaet()) {
						// Er ist besser, also mach ihn zum Favorit
						favorit = herausforderer;
						hauptProduktFavorit = favorit.holeKette().holeLager().suchenProdukt(hauptProduktKunde.holeName());
						gleichheitsListe.removeAllElements();
						gleichheitsListe.add(favorit);
					}
				}
				break;
			}
			case AVG: {
				// Average Variablen
				double preis = 0.0;
				double qualitaet = 0.0;
				double avgP = 0.0;
				double avgQ = 0.0;
				// Berechnen der Average-Werte
				for (int i = 0; i < favoriten.size(); i++) {
					Produkt aktuellesProdukt = favoriten.get(i).holeKette().holeLager().suchenProdukt(holeProdukte().get(0).holeName());
					preis = preis + aktuellesProdukt.holePreis();
					qualitaet = qualitaet + aktuellesProdukt.holeQualitaet();
				}
				// Basis Average Werte aller Filialen
				avgP = preis / favoriten.size();
				avgQ = qualitaet / favoriten.size();

				// Average Wert des Favoriten
				double avgAbweichungFavorit = Math.abs(1 - (hauptProduktFavorit.holePreis() / avgP)) + Math.abs(1 - (hauptProduktFavorit.holeQualitaet() / avgQ));

				for (int i = 1; i < favoriten.size(); i++) {
					Filiale herausforderer = favoriten.get(i);
					Produkt hauptProduktHerausforderer = herausforderer.holeKette().holeLager().suchenProdukt(holeProdukte().get(0).holeName());
					double avgAbweichungHerausforderer = Math.abs(1 - (hauptProduktHerausforderer.holePreis() / avgP)) + Math.abs(1 - (hauptProduktHerausforderer.holeQualitaet() / avgQ));

					// Sind beide exakt gleich füge den Herausforderer zur
					// Gleichheitsliste
					if (avgAbweichungFavorit == avgAbweichungHerausforderer) {
						gleichheitsListe.add(herausforderer);
						// Ansosten prüfe ob der Herausforderer besser ist
					} else if (avgAbweichungFavorit > avgAbweichungHerausforderer) {
						// Er ist besser, also mach ihn zum Favorit
						favorit = herausforderer;
						hauptProduktFavorit = favorit.holeKette().holeLager().suchenProdukt(hauptProduktKunde.holeName());
						avgAbweichungFavorit = Math.abs(1 - (hauptProduktFavorit.holePreis() / avgP)) + Math.abs(1 - (hauptProduktFavorit.holeQualitaet() / avgQ));
						gleichheitsListe.removeAllElements();
						gleichheitsListe.add(favorit);
					}
				}
				break;
			}
			}

			if (gleichheitsListe.size() > 0) {
				int zufallszahl = (int) Zufall.generierenZufallszahl(gleichheitsListe.size());
				favorit = gleichheitsListe.get((int) zufallszahl);
			}

			verkaufsFiliale = favorit;
		} else {
			// Kunde kauft bei keiner Filiale ein
			return;
		}

		// Führe Verkaufsprozess durch
		verkaufsFiliale.verkaufen(hauptProduktKunde.holeName(), hauptProduktKunde.holeMenge());
		// Verkaufe alle weiteren Produkte, die der Kunde kauft an den Kunden,
		// wenn sie verfügbar sind und zum Profil des Kunden passen
		for (int i = 1; i < holeProdukte().size(); i++) {
			Produkt aktuellesProduktKunde = holeProdukte().get(i);
			Produkt aktuellesProduktInFiliale = verkaufsFiliale.holeKette().holeLager().suchenProdukt(aktuellesProduktKunde.holeName());
			if (aktuellesProduktKunde != null && aktuellesProduktInFiliale != null && aktuellesProduktKunde.vergleichen(aktuellesProduktInFiliale) // Existiert
																																					// das
																																					// Produkt
																																					// in
																																					// der
																																					// enstprechenden
																																					// Qualität
																																					// und
																																					// Preis?
					&& aktuellesProduktInFiliale.holeMenge() > 0) { // Ist das
																	// Produkt
																	// noch
																	// verfügbar?

				verkaufsFiliale.verkaufen(aktuellesProduktKunde.holeName(), aktuellesProduktKunde.holeMenge()); // Verkaufe
																												// Produkt
				verkaufsFiliale.setzeFreieKapazitaet(verkaufsFiliale.holeFreieKapazitaet() + 1); // Korrigiere
																									// Kapazität
			}
		}
	}

	/**
	 * @return Liste aller Unternehmensketten, die der Kunde kennt
	 */
	public Vector<Unternehmenskette> holeKettenListe() {
		return this.kettenListe;
	}

	/**
	 * Fügt der Unternehmensliste des Kunden ein Unternehmen hinzu
	 * 
	 * @param kette
	 */
	private void hinzufuegenUnternehmenskette(Unternehmenskette kette) {
		if (kette != null) {
			this.kettenListe.add(kette);
		}
	}

	/**
	 * @return Liste aller Produkte, die der Kunde kauft
	 */
	public Vector<Produkt> holeProdukte() {
		return this.produkte;
	}

	/**
	 * Fügt ein Produkt der Produktliste des Kunden hinzu
	 * 
	 * @param produkt
	 */
	public void hinzufuegenProdukt(Produkt produkt) {
		if (produkt != null) {
			this.produkte.add(produkt);
		}
	}

	/**
	 * @return Präferenz beim Einkauf des Kunden, diese ist entweder die
	 *         maximale Qualität, der minimale Preis oder der Durchschnitt
	 */
	public Praeferenz holePraeferenz() {
		return this.praeferenz;
	}

	/**
	 * Setzt die Präferenz des Kunden
	 * 
	 * @param praeferenz
	 */
	public void setzePraeferenz(Praeferenz praeferenz) {
		if (praeferenz != null) {
			this.praeferenz = praeferenz;
		}
	}

	/**
	 * @return Standort, an dem der Kunde sich befindet
	 */
	public Standort holeStandort() {
		return this.standort;
	}

}
