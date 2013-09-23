package de.planspiel.cafe;

import java.util.Vector;

public class Haendler {
	
	private Vector<Produkt> produktListe;
	
	public Haendler() {
		produktListe = new Vector<Produkt>();
	}
	
	/**
	 * Generiert f�r alle m�glichen Produkttypen ein Angebot mit zuf�lligen Qualit�ten und Preisen
	 * @author Natalie
	 */
	public void generierenAngebot() {
		for (int i = 0; i < Produkttyp.values().length; i++){
			//Konstruktor: Produkt (name, qualitaet, ekpreis) 
			produktListe.add(new Produkt(Produkttyp.values()[i], Spiel.generierenZufallszahl(10), Spiel.generierenZufallszahl(20))); 
			//TODO Abh�ngigkeit Preis von Qualit�t? Obergrenze Preis/Qualit�t?
		}
	}
	
	public Vector<Produkt> holeProduktListe() {
		return produktListe;
	}
}
