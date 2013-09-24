package de.planspiel.cafe;

/**
 * Klasse zur Organisation von Filialen
 * @author Daniel
 *
 */
public class Filiale {

	private int mitarbeiter;
	private Standort standort;
	private Unternehmenskette kette;
	private int freieKapazitaet;
	
	/**
	 * Erzeugt eine neue Filiale an einem bestimmten Standort und f�gt diese auch dem Standort hinzu
	 * @param standort Standort der Filiale
	 * @param kette Unternehmenskette, zu der die Filiale geh�rt
	 */
	public Filiale(Standort standort, Unternehmenskette kette) {
		this.standort = standort;
		this.kette = kette;
		setzeMitarbeiter(0);
		initialisierenKapazitaet();
		standort.hinzufuegenFiliale(this);
	}
	

	/**
	 * Gibt die Menge zur�ck, die tats�chlich an den Kunden verkauft wurde.
	 * @param name
	 *            Produkttyp, der verkauft werden soll
	 * @param menge
	 *            Menge, die von dem Produkttypen verkauft werden soll
	 * @return Menge, die tats�chlich verkauft wird (z.B. bei Minderbestand)
	 */
	public int verkaufen(Produkttyp name, int menge) {
		Produkt lagerProdukt = this.holeKette().holeLager().auslagern(name, menge);
		// TODO kette.verbuchenUmsatz();
		return lagerProdukt.holeMenge();
	}

	/**
	 * Pr�ft, ob das gew�nschte Produkt vorhanden ist. Dabei werden die Eigenschaften (Qualit�t, etc.) ber�cksichtigt.
	 * @param produkt
	 *            Ist das gewollte Produkt vom Kunden. Es wird gepr�ft, ob das
	 *            Produkt im Lager ist und ob die Eigenschaften (Qualit�t, etc.)
	 *            stimmen.
	 * @return Gibt zur�ck, ob ein Produkt vorhanden ist und, ob die
	 *         gewollten Eigenschaften (Qualit�t, etc.) mit dem Produkt im Lager
	 *         �bereinstimmen.
	 */
	public boolean pruefenKundenprodukt(Produkt produkt) {
		// Pruefe, ob das Produkt vorhanden ist.
		Produkt lagerProdukt = this.holeKette().holeLager()
				.suchenProdukt(produkt.holeName());
		if (lagerProdukt != null) {
			// pruefe, ob die Eigenschaften, wie Qualit�t, �bereinstimmen
			if (produkt.vergleichen(lagerProdukt)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verbucht die Gesamtkosten der Filiale.
	 */
	public void verbuchenKosten() {
		// Kosten f�r die Mitarbeiter
		double kosten = (this.holeMitarbeiter() * this.holeKette().holeGehalt());
		this.holeKette().verbuchenKosten(Kostenverursacher.PERSONAL, kosten);
		// Kosten f�r den Standort
		double filialKosten = (this.holeStandort().holeLaufendeFilialkosten());
		this.holeKette().verbuchenKosten(Kostenverursacher.FILIALE_UNTERHALTUNG, filialKosten);
	}

	/**
	 * Muss jede Runde einmal aufgerufen werden.
	 */
	public void initialisierenKapazitaet() {
		this.setzeFreieKapazitaet(this.holeStandort().berechnenKapazitaet(this.holeMitarbeiter()));
	}
	
	/**
	 * Gibt Mitarbeiter der Filiale zur�ck
	 * @return Mitarbeiteranzahl
	 */
	public int holeMitarbeiter() {
		return this.mitarbeiter;
	}

	/** 
	 * Setzt die Anzahl der neuen Mitarbeiter
	 * @param mitarbeiter Anzahl der neuen Mitarbeiter gr��er gleich 0
	 */
	public void setzeMitarbeiter(int mitarbeiter) {
		if(mitarbeiter >= 0) {
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
	 * @return Freie Kapazit�t der Filiale, so viele Kunden k�nnen noch bedient werden
	 */
	public int holeFreieKapazitaet() {
		return this.freieKapazitaet;
	}
	
	/**
	 * Setzt die neue freie Kapazit�t der Filiale, diese wird zu Beginn initalisiert und bei jedem Verkauf gemindert.
	 * @param freieKapazitaet neue freie Kapazit�t gr��er gleich 0
	 */
	public void setzeFreieKapazitaet(int freieKapazitaet) {
		if (freieKapazitaet >= 0) {
			this.freieKapazitaet = freieKapazitaet;
		}
	}

}

