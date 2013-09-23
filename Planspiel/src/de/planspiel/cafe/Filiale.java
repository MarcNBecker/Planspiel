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
	 * Gibt die Menge zur�ck, die tats�chlich an den Kunden verkauft wurde.
	 * @param name
	 *            Produkttyp, der verkauft werden soll
	 * @param menge
	 *            Menge, die von dem Produkttypen verkauft werden soll
	 * @return Menge, die tats�chlich verkauft wird (z.B. bei Minderbestand)
	 */
	public int verkaufen(Produkttyp name, int menge) {
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
	 * Pr�ft, ob das gew�nschte Produkt vorhanden ist. Dabei werden die Eigenschaften (Qualit�t, etc.) ber�cksichtigt.
	 * @param produkt
	 *            Ist das gewollte Produkt vom Kunden. Es wird gepr�ft, ob das
	 *            Produkt im Lager ist und ob die Eigenschaften (Qualit�t, etc.)
	 *            stimmen.
	 * @return boolean Gibt zur�ck, ob ein Produkt vorhanden ist und, ob die
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
	 * Berechnet die Gesamtkosten der Filiale.
	 * @return help Double-Wert, der die H�he der Mitarbeiter- und
	 *         Standort-Kosten geb�ndelt zur�ckgibt.
	 */
	public double berechnenKosten() {
		// TODO
		double kosten = 0.0;
		// Kosten f�r die Mitarbeiter
		kosten = (this.holeMitarbeiter() * this.holeKette().holeGehalt());
		// Kosten f�r den Standort
		kosten = kosten + (this.holeStandort().holeLaufendeFilialkosten());
		return kosten;
	}

	/**
	 * Stellt eine Menge von Mitarbeitern ein.
	 * @param anzahl
	 *            Int-Wert, der die Anzahl der zu einstellenden Mitarbeitern
	 *            angibt.
	 */
	public void einstellenMitarbeiter(int anzahl) {
		this.setzeMitarbeiter(this.holeMitarbeiter() + anzahl);
	}

	/**
	 * Entl�sst eine Menge von Mitarbeitern und verbucht die daf�r entstehenden Kosten.
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
		// TODO Bitte in Verbindung mit berechneKapazit�t in der Klasse Standort nochmal pr�fen
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
