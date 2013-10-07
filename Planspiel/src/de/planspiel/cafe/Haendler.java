package de.planspiel.cafe;

import de.planspiel.spiel.Zufall;

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
		//Alle Preis Qualit�t Verh�ltnis von 3-6
		PreisQualitaetVerhaeltnis v = new PreisQualitaetVerhaeltnis(Zufall.generierenZufallszahl(3) + 3);
		for (int i = 0; i < Produkttyp.values().length; i++){
			//Konstruktor: Produkt (name, qualitaet, ekpreis) 
			double zufallsQualitaet = Zufall.generierenZufallszahl(1);
			double preis = v.berechnenPreis(zufallsQualitaet);
			holeProduktliste().add(new Produkt(Produkttyp.values()[i], zufallsQualitaet, preis)); 
		}
	}

}
