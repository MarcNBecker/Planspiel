package de.planspiel.cafe;

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

	public int verkaufen(Produkttypen name, int menge) {
		// TODO
		
		return 0;
	}
	/**
	 * @author Daniel
	 * @param produkt Ist das gewollte Produkt vom Kunden. Es wird gepr�ft, ob das Produkt im Lager ist und ob die Eigenschaften (Qualit�t, etc.) stimmen.
	 * @return boolean Gibt zur�ck, ob ein Produkt vorhanden ist und, ob die gewollten Eigenschaften (Qualit�t, etc.) mit dem Produkt im Lager �bereinstimmen.
	 */
	public boolean pruefenKundenprodukt(Produkt produkt) {
		// TODO Wie wirkt sich ein Minderbestand auf den Kunden aus? Wenn er nur 15, statt 20 gewollte bekommt.
		// Pruefe, ob das Produkt vorhanden ist.
		Produkt helpProdukt = this.holeKette().holeLager().suchenProdukt(produkt.holeName()); 
		if (helpProdukt!=null) {
			// pruefe, ob die Eigenschaften, wie Qualit�t, �bereinstimmen
			if (produkt.vergleichen(helpProdukt)) {
				return true;
			}
		}
		return false;
	}

	public void berechnenKapazitaet() {
		// TODO
	}
	
	/**
	 * @author Daniel
	 * @return help Double-Wert, der die H�he der Mitarbeiter- und Standort-Kosten geb�ndelt zur�ckgibt.
	 */
	public double berechnenKosten() {
		// TODO
		double help = 0.0;
		// Kosten f�r die Mitarbeiter
		help = (this.holeMitarbeiter() * this.holeKette().holeGehalt());
		// Kosten f�r den Standort
		help = help + (this.holeStandort().holeLaufendeFilialkosten());
		return help;
	}

	/**
	 * @author Daniel
	 * @param anzahl Int-Wert, der die Anzahl der zu einstellenden Mitarbeitern angibt.
	 */
	public void einstellenMitarbeiter(int anzahl) {
		// TODO
		this.setzeMitarbeiter(this.holeMitarbeiter() + anzahl);
	}

	/**
	 * @author Daniel
	 * @param anzahl Int-Wert, der die Anzahl der zu entlassenden Mitarbeitern angibt.
	 */
	public void entlassenMitarbeiter(int anzahl) {
		// TODO
		this.setzeMitarbeiter(this.holeMitarbeiter() - anzahl);
		for (int i = 0; i < anzahl; i++) {
			this.holeKette().verbuchenKosten(Kostenverursacher.PERSONAL,
					this.holeKette().holeEntlassungskosten());
		}
	}

	public void initialisierenKapazitaet() {
		// TODO
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
