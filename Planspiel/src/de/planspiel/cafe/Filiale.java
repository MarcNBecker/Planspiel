package de.planspiel.cafe;

import de.planspiel.spiel.Spiel;

/**
 * Klasse zur Organisation von Filialen
 * 
 * @author Daniel
 * 
 */
public class Filiale {

	private int mitarbeiter;
	private Standort standort;
	private Unternehmenskette kette;
	private int freieKapazitaet;
	private int startKapazitaet;

	/**
	 * Erzeugt eine neue Filiale an einem bestimmten Standort und fügt diese
	 * auch dem Standort hinzu
	 * 
	 * @param standort
	 *            Standort der Filiale
	 * @param kette
	 *            Unternehmenskette, zu der die Filiale gehört
	 */
	public Filiale(Standort standort, Unternehmenskette kette) {
		this.standort = standort;
		this.kette = kette;
		setzeMitarbeiter(0);
		initialisierenKapazitaet();
		standort.hinzufuegenFiliale(this);
	}

	/**
	 * Gibt die Menge zurück, die tatsächlich an den Kunden verkauft wurde.
	 * 
	 * @param name
	 *            Produkttyp, der verkauft werden soll
	 * @param menge
	 *            Menge, die von dem Produkttypen verkauft werden soll
	 */
	public void verkaufen(Produkttyp name, int menge) {
		Produkt lagerProdukt = this.holeKette().holeLager().auslagern(name, menge);
		holeKette().verbuchenErtrag(Ertragsverursacher.UMSATZERLOESE, lagerProdukt.holeMenge() * lagerProdukt.holePreis());
		Report report = holeKette().holeReportListe().get(holeKette().holeReportListe().size() - 1); // Ist
																										// das
																										// der
																										// richtige
																										// Report??
		report.holeVerkaufsListe().erhoehenVerkaufsmenge(name, lagerProdukt.holeMenge());
		Spiel.holeSpiel().holeAktuellerMarktanteil().mitteilenVerkauf(holeKette());
		setzeFreieKapazitaet(holeFreieKapazitaet() - 1);
	}

	/**
	 * Prüft, ob das gewünschte Produkt vorhanden ist. Dabei werden die
	 * Eigenschaften (Qualität, etc.) berücksichtigt.
	 * 
	 * @param produkt
	 *            Ist das gewollte Produkt vom Kunden. Es wird geprüft, ob das
	 *            Produkt im Lager ist und ob die Eigenschaften (Qualität, etc.)
	 *            stimmen.
	 * @return Gibt zurück, ob ein Produkt vorhanden ist und, ob die gewollten
	 *         Eigenschaften (Qualität, etc.) mit dem Produkt im Lager
	 *         übereinstimmen.
	 */
	public boolean pruefenKundenprodukt(Produkt produkt) {
		// Pruefe, ob das Produkt vorhanden ist.
		Produkt lagerProdukt = this.holeKette().holeLager().suchenProdukt(produkt.holeName());
		if (lagerProdukt != null) {
			// pruefe, ob die Eigenschaften, wie Qualität, übereinstimmen
			if (produkt.vergleichen(lagerProdukt)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Berechnet den Wert der Filiale
	 * 
	 * @return Wert der Filiale
	 */
	public double berechnenWert() {
		return holeStandort().holeStartFilialkosten();
	}

	/**
	 * Verbucht die Gesamtkosten der Filiale.
	 */
	public void berechnenKosten() {
		// Kosten für die Mitarbeiter
		double kosten = (this.holeMitarbeiter() * this.holeKette().holeGehalt());
		this.holeKette().verbuchenKosten(Kostenverursacher.PERSONAL, kosten);
		// Kosten für den Standort
		double filialKosten = (this.holeStandort().holeLaufendeFilialkosten());
		this.holeKette().verbuchenKosten(Kostenverursacher.FILIALE_UNTERHALTUNG, filialKosten);
	}

	/**
	 * Muss jede Runde einmal aufgerufen werden.
	 */
	public void initialisierenKapazitaet() {
		this.setzeFreieKapazitaet(this.holeStandort().berechnenKapazitaet(this.holeMitarbeiter()));
		startKapazitaet = freieKapazitaet;
	}

	/**
	 * Gibt Mitarbeiter der Filiale zurück
	 * 
	 * @return Mitarbeiteranzahl
	 */
	public int holeMitarbeiter() {
		return this.mitarbeiter;
	}

	/**
	 * Setzt die Anzahl der neuen Mitarbeiter
	 * 
	 * @param mitarbeiter
	 *            Anzahl der neuen Mitarbeiter größer gleich 0
	 */
	public void setzeMitarbeiter(int mitarbeiter) {
		if (mitarbeiter >= 0) {
			this.mitarbeiter = mitarbeiter;
		}
	}

	/**
	 * @return Standort der Filiale
	 */
	public Standort holeStandort() {
		return this.standort;
	}

	/**
	 * @return Unternehemenskette der Filiale
	 */
	public Unternehmenskette holeKette() {
		return this.kette;
	}

	/**
	 * @return Freie Kapazität der Filiale, so viele Kunden können noch bedient
	 *         werden
	 */
	public int holeFreieKapazitaet() {
		return this.freieKapazitaet;
	}

	/**
	 * Setzt die neue freie Kapazität der Filiale, diese wird zu Beginn
	 * initalisiert und bei jedem Verkauf gemindert.
	 * 
	 * @param freieKapazitaet
	 *            neue freie Kapazität größer gleich 0
	 */
	public void setzeFreieKapazitaet(int freieKapazitaet) {
		if (freieKapazitaet >= 0) {
			this.freieKapazitaet = freieKapazitaet;
		}
	}

	/**
	 * @return Liefert die Startkapazität zurück
	 */
	public int holeStartKapazitaet() {
		return startKapazitaet;
	}
}
