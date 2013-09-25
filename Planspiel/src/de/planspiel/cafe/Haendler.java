package de.planspiel.cafe;

import java.util.Vector;

/**
 * Klasse zur Organisation des H�ndlers
 * @author Natalie
 *
 */
public class Haendler {
	
	private Vector<Produkt> produktListe;
	
	/**
	 * Erzeugt einen neuen H�ndler und generiert direkt ein erstes Angebot
	 */
	public Haendler() {
		produktListe = new Vector<Produkt>();
		generierenAngebot();
	}
	
	/**
	 * Generiert f�r alle m�glichen Produkttypen ein Angebot mit zuf�lligen Qualit�ten und Preisen
	 * @author Natalie
	 */
	public void generierenAngebot() {
		for (int i = 0; i < Produkttyp.values().length; i++){
			//Konstruktor: Produkt (name, qualitaet, ekpreis) 
			produktListe.add(new Produkt(Produkttyp.values()[i], Zufall.generierenZufallszahl(1), Zufall.generierenZufallszahl(10))); 
			//TODO Abh�ngigkeit Preis von Qualit�t? Obergrenze Preis/Qualit�t?
		}
	}
	
	/**
	 * Sucht im vorhandenen Produktvektor nach dem angegebenen Produkt
	 * @param name Produkttyp-Wert, der angibt welches Produkt gesucht wird
	 * @return Null, falls das Produkt nicht vorhanden ist, ansonsten das gesuchte Produkt
	 */
	public Produkt suchenProdukt(Produkttyp name) {
		for (int i = 0; i < produktListe.size(); i++){
			if (produktListe.get(i).holeName() == name){
				return produktListe.get(i);
			}
		}
		//falls Produkt nicht vorhanden
		return null;
	}
	
	/**
	 * @return Produktliste des H�ndlers
	 */
	public Vector<Produkt> holeProduktListe() {
		return produktListe;
	}
}
