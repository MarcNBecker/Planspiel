package de.planspiel.cafe;

import de.planspiel.spiel.Zufall;

/**
 * Klasse zur Organisation des H�ndlers
 * 
 * @author Natalie Buchner
 * 
 */
public class Haendler extends ProduktVerwalter {

	private Haendlertyp name;

	/**
	 * Erzeugt einen neuen H�ndler und generiert direkt ein erstes Angebot
	 */
	public Haendler(Haendlertyp name) {
		super();
		this.name = name;
		generierenAngebot();
	}

	/**
	 * Generiert f�r alle m�glichen Produkttypen ein Angebot mit zuf�lligen
	 * Qualit�ten und Preisen
	 * 
	 * @author Natalie
	 */
	public void generierenAngebot() {
		leeren();
		// Alle Preis Qualit�t Verh�ltnis von 3-6
		for (int i = 0; i < Produkttyp.values().length; i++) {
			Produkttyp aktuellesProdukt = Produkttyp.values()[i];
			// Konstruktor: Produkt (name, qualitaet, ekpreis)
			double zufallsQualitaet = Zufall.generierenQualitaet();
			double preis = aktuellesProdukt.holeMaxEK() * PreisQualitaetVerhaeltnis.berechnenPreisFaktor(zufallsQualitaet);
			hinzufuegenProdukt(new Produkt(aktuellesProdukt, zufallsQualitaet, preis));
		}
	}

	public String holeName() {
		return name.toString();
	}

}
