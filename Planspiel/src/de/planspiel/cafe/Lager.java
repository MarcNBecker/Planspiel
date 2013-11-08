package de.planspiel.cafe;

import java.util.Vector;

/**
 * Klasse zur Organisation der Produktbest�nde eines Unternehmens
 * 
 * @author Natalie Buchner
 * 
 */
public class Lager extends ProduktVerwalter {

	private Unternehmenskette kette;

	/**
	 * Erzeugt eines neues Lager, welches zu einer bestimmten Unternehmenskette
	 * geh�rt
	 * 
	 * @param kette
	 */
	public Lager(Unternehmenskette kette) {
		super();
		this.kette = kette;
	}

	/**
	 * Lagert das �bergebene Produkt ein, falls es schon vorhanden ist werden
	 * die Produkte miteinander verschmolzen
	 * 
	 * @param produkt
	 *            Produkt, das eingelagert werden soll
	 * @author Natalie
	 */
	public void einlagern(Produkt produkt) {
		Produkt gesuchtesProdukt = suchenProdukt(produkt.holeName());
		if (gesuchtesProdukt == null) { // Falls Produkt noch nicht vorhanden
			hinzufuegenProdukt(produkt);
		} else {
			gesuchtesProdukt.verschmelzen(produkt);// Andernfalls schon
													// vorhandenes Produkt mit
													// neuem verschmelzen
		}
	}

	/**
	 * Lagert eine bestimmte Menge eines Produktes aus dem Lager aus - falls
	 * weniger auf Lager ist, wird auch nur die geringere Menge ausgelagert
	 * 
	 * @param name
	 *            Produkttypen-Wert, welches Produkt ausgelagert werden soll
	 * @param menge
	 *            int-Wert, welche Menge ausgelagert werden soll
	 * @return Null, falls das Produkt nicht vorhanden ist, ansonsten ein neues
	 *         Produkt, welches die ausgelagerte Menge enth�lt sowie
	 *         entsprechende Qualit�t und Preis
	 * @author Natalie
	 */
	public Produkt auslagern(Produkttyp name, int menge) {
		Produkt gesuchtesProdukt = suchenProdukt(name);
		if (gesuchtesProdukt == null) {
			return null;
		} else { // Falls Produkt vorhanden - neues Produkt zur�ckgeben, das
					// gleiche Eigenschaften hat, und (maximal m�gliche) Menge
			Produkt rueckgabeProdukt = new Produkt(gesuchtesProdukt.holeName());
			int vorhandeneMenge = gesuchtesProdukt.holeMenge();
			if (vorhandeneMenge < menge) { // Falls weniger da ist als der Kunde
											// haben will - R�ckgabemenge ist
											// die vorhandene Menge!! Nichts
											// mehr im Lager
				gesuchtesProdukt.setzeMenge(0);
				rueckgabeProdukt.setzeMenge(vorhandeneMenge);
			} else { // Es ist noch genug auf Lager - Vom Lager was weg nehmen
						// und R�ckgabemenge ist die gew�nschte Menge
				gesuchtesProdukt.setzeMenge(vorhandeneMenge - menge);
				rueckgabeProdukt.setzeMenge(menge);
			}
			// Preis und Qualit�t �bernehmen vom Lagerprodukt
			rueckgabeProdukt.setzePreis(gesuchtesProdukt.holePreis());
			rueckgabeProdukt.setzeQualitaet(gesuchtesProdukt.holeQualitaet());
			return rueckgabeProdukt;
		}
	}

	/**
	 * �bernimmt Daten der Entscheidung und kauft die gew�nschten Rohstoffe beim
	 * H�ndler ein, verbucht Kosten pro Rohstoff und lagert diesen ein!
	 * 
	 * @param einkaufsliste
	 *            Vector<Produkt>, der die Namen und die Mengen der gew�nschten
	 *            Produkte enth�lt
	 * @param haendler
	 *            Haendler-Objekt, das angibt bei welchem H�ndler gekauft wird
	 * @author Natalie
	 */
	public void einkaufen(Vector<Produkt> einkaufsliste, Haendler haendler) {
		Produkt einkaufProdukt = null;
		Produkt vergleichProdukt = null;
		for (int i = 0; i < einkaufsliste.size(); i++) {
			einkaufProdukt = einkaufsliste.get(i);
			vergleichProdukt = haendler.suchenProdukt(einkaufProdukt.holeName());
			// vom Haendler EKPreis und Qualitaet auslesen und im neuen Vektor
			// speichern
			einkaufProdukt.setzeEkpreis(vergleichProdukt.holeEkpreis());
			einkaufProdukt.setzeQualitaet(vergleichProdukt.holeQualitaet());
			// Rohstoffkosten verbuchen
			if (holeKette().verbuchenKosten(Kostenverursacher.ROHSTOFF, (einkaufProdukt.holeMenge() * einkaufProdukt.holeEkpreis()))) {
				// Pro Produkt einlagern
				einlagern(einkaufProdukt);
			}
		}
	}

	/**
	 * Berechnet aktuellen Wert des UV (also des Lagers), mit Wert pro Produkt =
	 * Einkaufspreis
	 * 
	 * @return 0.0 Falls nichts auf Lager ist, ansonsten Summe der Produktwerte
	 * @author Natalie
	 */
	public double berechnenWert() {
		double wert = 0.0;
		for (int i = 0; i < holeProduktliste().size(); i++) {
			wert += holeProduktliste().get(i).holeEkpreis() * holeProduktliste().get(i).holeMenge();
		}
		return wert;
	}

	/**
	 * @return Gibt die Kette des Lagers zur�ck
	 */
	public Unternehmenskette holeKette() {
		return kette;
	}
}
