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
		// TODO Entscheidung: Wie viele Produkte bietet ein H�ndler an? Evtl. Schleife und Produktnamen in ENUM speichern
		//Konstruktor: Produkt (name, qualitaet, ekpreis) 
		produktListe.add(new Produkt(Produkttypen.KAFFEE, Spiel.generiereZufallszahl(10), Spiel.generiereZufallszahl(20)) );  // Abh�ngigkeit Preis von Qualit�t?
		produktListe.add(new Produkt(Produkttypen.TEE, Spiel.generiereZufallszahl(10), Spiel.generiereZufallszahl(20)));
		produktListe.add(new Produkt(Produkttypen.KUCHEN, Spiel.generiereZufallszahl(10), Spiel.generiereZufallszahl(20)));
	}
	
	public Vector<Produkt> ausgebenAngebot() {
		// TODO f�r die GUI? entspricht doch eigentlich holeProduktListe?
		return null;
	}
	
	public Vector<Produkt> holeProduktListe() {
		return produktListe;
	}
}
