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
		for (int i = 0; i < Produkttypen.values().length; i++){
			//Konstruktor: Produkt (name, qualitaet, ekpreis) 
			produktListe.add(new Produkt(Produkttypen.values()[i], Spiel.generiereZufallszahl(10), Spiel.generiereZufallszahl(20))); 
			//TODO Abh�ngigkeit Preis von Qualit�t?
		}
	}
	
	public Vector<Produkt> ausgebenAngebot() {
		return holeProduktListe();
	}
	
	public Vector<Produkt> holeProduktListe() {
		return produktListe;
	}
}
