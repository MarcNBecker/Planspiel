package de.planspiel.cafe;

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
		for (int i = 0; i < Produkttyp.values().length; i++){
			//Konstruktor: Produkt (name, qualitaet, ekpreis) 
			holeProduktliste().add(new Produkt(Produkttyp.values()[i], Zufall.generierenZufallszahl(1), Zufall.generierenZufallszahl(5))); 
			//TODO Abhängigkeit Preis von Qualität? Obergrenze Preis/Qualität?
		}
	}

}
