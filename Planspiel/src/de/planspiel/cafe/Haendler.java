package de.planspiel.cafe;

/**
 * Klasse zur Organisation des H�ndlers
 * @author Natalie
 *
 */
public class Haendler extends ProduktVerwalter {
	
	/**
	 * Erzeugt einen neuen H�ndler und generiert direkt ein erstes Angebot
	 */
	public Haendler() {
		super();
		generierenAngebot();
	}
	
	/**
	 * Generiert f�r alle m�glichen Produkttypen ein Angebot mit zuf�lligen Qualit�ten und Preisen
	 * @author Natalie
	 */
	public void generierenAngebot() {
		for (int i = 0; i < Produkttyp.values().length; i++){
			//Konstruktor: Produkt (name, qualitaet, ekpreis) 
			holeProduktliste().add(new Produkt(Produkttyp.values()[i], Zufall.generierenZufallszahl(1), Zufall.generierenZufallszahl(5))); 
			//TODO Abh�ngigkeit Preis von Qualit�t? Obergrenze Preis/Qualit�t?
		}
	}

}
