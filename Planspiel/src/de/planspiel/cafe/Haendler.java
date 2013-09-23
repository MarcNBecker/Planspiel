package de.planspiel.cafe;

import java.util.Vector;

public class Haendler {
	
	private Vector<Produkt> produktListe;
	
	public Haendler() {
		produktListe = new Vector<Produkt>();
	}
	
	public void generierenAngebot() {
		// TODO Entscheidung: Wie viele Produkte bietet ein H�ndler an? Evtl. Schleife und Produktnamen in ENUM speichern
		//Konstruktor: Produkt (name, qualitaet, ekpreis) 
		produktListe.add(new Produkt("Kaffee", Spiel.generiereZufallszahl(10), Spiel.generiereZufallszahl(20)) );  // Abh�ngigkeit Preis von Qualit�t?
		produktListe.add(new Produkt("Tee", Spiel.generiereZufallszahl(10), Spiel.generiereZufallszahl(20)));
		produktListe.add(new Produkt("Kakao", Spiel.generiereZufallszahl(10), Spiel.generiereZufallszahl(20)));
	}
	
	public Vector<Produkt> ausgebenAngebot() {
		// TODO f�r die GUI? entspricht doch eigentlich holeProduktListe?
		return null;
	}
	
	public Vector<Produkt> holeProduktListe() {
		return produktListe;
	}
}
