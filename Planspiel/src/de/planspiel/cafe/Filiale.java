package de.planspiel.cafe;

/**
 * 
 * @author Daniel Degraf
 * 
 */

public class Filiale {

	private int mitarbeiter;
	private Standort standort;
	private Unternehmenskette kette;
	private int freieKapazitaet;

	public Filiale(Standort standort, Unternehmenskette kette) {
		this.standort = standort;
		this.kette = kette;
		this.mitarbeiter = 0;
		this.freieKapazitaet = 0;
	}

	/**
	 * 
	 * @param name
	 *            Produkttyp, der verkauft werden soll
	 * @param menge
	 *            Menge, die von dem Produkttypen verkauft werden soll
	 * @return Menge, die tatsächlich verkauft wird (z.B. bei Minderbestand)
	 */
	public int verkaufen(Produkttypen name, int menge) {
		if (pruefenKundenprodukt(this.holeKette().holeLager()
				.suchenProdukt(name))==true) {
			Produkt lagerProdukt = this.holeKette().holeLager()
					.auslagern(name, menge);
			return lagerProdukt.holeMenge();
		} else {
			return 0;
		}
	}

	/**
	 * @param produkt
	 *            Ist das gewollte Produkt vom Kunden. Es wird geprüft, ob das
	 *            Produkt im Lager ist und ob die Eigenschaften (Qualität, etc.)
	 *            stimmen.
	 * @return boolean Gibt zurück, ob ein Produkt vorhanden ist und, ob die
	 *         gewollten Eigenschaften (Qualität, etc.) mit dem Produkt im Lager
	 *         übereinstimmen.
	 */
	public boolean pruefenKundenprodukt(Produkt produkt) {
		// Pruefe, ob das Produkt vorhanden ist.
		Produkt lagerProdukt = this.holeKette().holeLager()
				.suchenProdukt(produkt.holeName());
		if (lagerProdukt != null) {
			// pruefe, ob die Eigenschaften, wie Qualität, übereinstimmen
			if (produkt.vergleichen(lagerProdukt)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return help Double-Wert, der die Höhe der Mitarbeiter- und
	 *         Standort-Kosten gebündelt zurückgibt.
	 */
	public double berechnenKosten() {
		// TODO
		double kosten = 0.0;
		// Kosten für die Mitarbeiter
		kosten = (this.holeMitarbeiter() * this.holeKette().holeGehalt());
		// Kosten für den Standort
		kosten = kosten + (this.holeStandort().holeLaufendeFilialkosten());
		return kosten;
	}

	/**
	 * @param anzahl
	 *            Int-Wert, der die Anzahl der zu einstellenden Mitarbeitern
	 *            angibt.
	 */
	public void einstellenMitarbeiter(int anzahl) {
		this.setzeMitarbeiter(this.holeMitarbeiter() + anzahl);
	}

	/**
	 * @param anzahl
	 *            Int-Wert, der die Anzahl der zu entlassenden Mitarbeitern
	 *            angibt.
	 */
	public void entlassenMitarbeiter(int anzahl) {
		this.setzeMitarbeiter(this.holeMitarbeiter() - anzahl);
		for (int i = 0; i < anzahl; i++) {
			this.holeKette().verbuchenKosten(Kostenverursacher.PERSONAL,
					this.holeKette().holeEntlassungskosten());
		}
	}

	public void initialisierenKapazitaet() {
		// TODO Bitte in Verbindung mit berechneKapazität in der Klasse Standort nochmal prüfen
		this.setzeFreieKapazitaet(this.holeStandort().berechnenKapazitaet(
				this.holeMitarbeiter()));
	}

	public int holeMitarbeiter() {
		return this.mitarbeiter;
	}

	public void setzeMitarbeiter(int mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public Standort holeStandort() {
		return this.standort;
	}

	public Unternehmenskette holeKette() {
		return this.kette;
	}

	public int holeFreieKapazitaet() {
		return this.freieKapazitaet;
	}

	public void setzeFreieKapazitaet(int freieKapazitaet) {
		this.freieKapazitaet = freieKapazitaet;
	}

}
