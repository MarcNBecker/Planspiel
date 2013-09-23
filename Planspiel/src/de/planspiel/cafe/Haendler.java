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
		// TODO Entscheidung: Wie viele Produkte bietet ein Händler an? Evtl. Schleife und Produktnamen in ENUM speichern
		//Konstruktor: Produkt (name, qualitaet, ekpreis) 
		produktListe.add(new Produkt(Produkttypen.KAFFEE, Spiel.generiereZufallszahl(10), Spiel.generiereZufallszahl(20)) );  // Abhängigkeit Preis von Qualität?
		produktListe.add(new Produkt(Produkttypen.TEE, Spiel.generiereZufallszahl(10), Spiel.generiereZufallszahl(20)));
		produktListe.add(new Produkt(Produkttypen.KUCHEN, Spiel.generiereZufallszahl(10), Spiel.generiereZufallszahl(20)));
	}
	
	public Vector<Produkt> ausgebenAngebot() {
		// TODO für die GUI? entspricht doch eigentlich holeProduktListe?
		return null;
	}
	
	public Vector<Produkt> holeProduktListe() {
		return produktListe;
	}
}
