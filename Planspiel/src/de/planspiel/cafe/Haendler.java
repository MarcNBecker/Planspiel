package de.planspiel.cafe;

import java.util.Vector;

/**
 * Klasse zur Organisation des Händlers
 * @author Natalie
 *
 */
public class Haendler {
	
	private Vector<Produkt> produktListe;
	
	/**
	 * Erzeugt einen neuen Händler und generiert direkt ein erstes Angebot
	 */
	public Haendler() {
		produktListe = new Vector<Produkt>();
		generierenAngebot();
	}
	
	/**
	 * Generiert für alle möglichen Produkttypen ein Angebot mit zufälligen Qualitäten und Preisen
	 * @author Natalie
	 */
	public void generierenAngebot() {
		for (int i = 0; i < Produkttyp.values().length; i++){
			//Konstruktor: Produkt (name, qualitaet, ekpreis) 
			produktListe.add(new Produkt(Produkttyp.values()[i], Zufall.generierenZufallszahl(1), Zufall.generierenZufallszahl(10))); 
			//TODO Abhängigkeit Preis von Qualität? Obergrenze Preis/Qualität?
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
	 * @return Produktliste des Händlers
	 */
	public Vector<Produkt> holeProduktListe() {
		return produktListe;
	}
}
