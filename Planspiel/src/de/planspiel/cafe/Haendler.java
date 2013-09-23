package de.planspiel.cafe;

import java.util.Vector;

public class Haendler {
	
	private Vector<Produkt> produktListe;
	
	public Haendler() {
		produktListe = new Vector<Produkt>();
	}
	
	/**
	 * Generiert für alle möglichen Produkttypen ein Angebot mit zufälligen Qualitäten und Preisen
	 * @author Natalie
	 */
	public void generierenAngebot() {
		for (int i = 0; i < Produkttyp.values().length; i++){
			//Konstruktor: Produkt (name, qualitaet, ekpreis) 
			produktListe.add(new Produkt(Produkttyp.values()[i], Spiel.generierenZufallszahl(10), Spiel.generierenZufallszahl(20))); 
			//TODO Abhängigkeit Preis von Qualität? Obergrenze Preis/Qualität?
		}
	}
	
	public Vector<Produkt> holeProduktListe() {
		return produktListe;
	}
}
