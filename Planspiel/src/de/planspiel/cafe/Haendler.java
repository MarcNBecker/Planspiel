package de.planspiel.cafe;

import de.planspiel.spiel.Zufall;

/**
 * Klasse zur Organisation des Händlers
 * 
 * @author Natalie Buchner
 * 
 */
public class Haendler extends ProduktVerwalter {

	private Haendlertyp name;

	/**
	 * Erzeugt einen neuen Händler und generiert direkt ein erstes Angebot
	 */
	public Haendler(Haendlertyp name) {
		super();
		this.name = name;
		generierenAngebot();
	}

	/**
	 * Generiert für alle möglichen Produkttypen ein Angebot mit zufälligen
	 * Qualitäten und Preisen
	 * 
	 * @author Natalie
	 */
	public void generierenAngebot() {
		leeren();
		// Alle Preis Qualität Verhältnis von 3-6
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
