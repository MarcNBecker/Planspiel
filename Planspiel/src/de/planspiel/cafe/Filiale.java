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
	 * @param produkt Ist das gewollte Produkt vom Kunden. Es wird geprüft, ob das Produkt im Lager ist und ob die Eigenschaften (Qualität, etc.) stimmen.
	 * @return boolean Gibt zurück, ob ein Produkt vorhanden ist und, ob die gewollten Eigenschaften (Qualität, etc.) mit dem Produkt im Lager übereinstimmen.
	 */
	public boolean pruefenKundenprodukt(Produkt produkt) {
		// TODO Wie wirkt sich ein Minderbestand auf den Kunden aus? Wenn er nur 15, statt 20 gewollte bekommt.
		// Pruefe, ob das Produkt vorhanden ist.
		Produkt helpProdukt = this.holeKette().holeLager().suchenProdukt(produkt.holeName()); 
		if (helpProdukt!=null) {
			// pruefe, ob die Eigenschaften, wie Qualität, übereinstimmen
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
	 * @return help Double-Wert, der die Höhe der Mitarbeiter- und Standort-Kosten gebündelt zurückgibt.
	 */
	public double berechnenKosten() {
		// TODO
		double help = 0.0;
		// Kosten für die Mitarbeiter
		help = (this.holeMitarbeiter() * this.holeKette().holeGehalt());
		// Kosten für den Standort
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
