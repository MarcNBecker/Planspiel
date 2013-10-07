package de.planspiel.cafe;

import de.planspiel.spiel.Zufall;

/**
 * Klasse zur Organisation des Händlers
 * @author Natalie
 *
 */
public class Haendler extends ProduktVerwalter {
	
	/**
	 * Erzeugt einen neuen Händler und generiert direkt ein erstes Angebot
	 */
	public Haendler() {
		super();
		generierenAngebot();
	}
	
	/**
	 * Generiert für alle möglichen Produkttypen ein Angebot mit zufälligen Qualitäten und Preisen
	 * @author Natalie
	 */
	public void generierenAngebot() {
		//Alle Preis Qualität Verhältnis von 3-6
		PreisQualitaetVerhaeltnis v = new PreisQualitaetVerhaeltnis(Zufall.generierenZufallszahl(3) + 3);
		for (int i = 0; i < Produkttyp.values().length; i++){
			//Konstruktor: Produkt (name, qualitaet, ekpreis) 
			double zufallsQualitaet = Zufall.generierenZufallszahl(1);
			double preis = v.berechnenPreis(zufallsQualitaet);
			holeProduktliste().add(new Produkt(Produkttyp.values()[i], zufallsQualitaet, preis)); 
		}
	}

}
