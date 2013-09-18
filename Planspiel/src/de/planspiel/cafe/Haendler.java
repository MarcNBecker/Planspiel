package de.planspiel.cafe;

import java.util.Vector;

public class Haendler {
	
	private Vector<Produkt> produktListe;
	
	public Haendler() {
		produktListe = new Vector<Produkt>();
	}
	
	public void generierenAngebot() {
		// TODO Entscheidung: Wie viele Produkte bietet ein Händler an? Evtl. Schleife und Produktnamen in ENUM speichern
		//Konstruktor: Produkt (name, qualitaet, ekpreis) 
		produktListe.add(new Produkt("Kaffee", zufallszahl(10), zufallszahl(20)) );  // Abhängigkeit Preis von Qualität?
		produktListe.add(new Produkt("Tee", zufallszahl(10), zufallszahl(20)));
		produktListe.add(new Produkt("Kakao", zufallszahl(10), zufallszahl(20)));
	}
	
	public Vector<Produkt> ausgebenAngebot() {
		// TODO für die GUI? entspricht doch eigentlich holeProduktListe?
		return null;
	}
	
	public Vector<Produkt> holeProduktListe() {
		return produktListe;
	}
	
	/*
	 * Zufallszahl zwischen 0 und Grenzen mit zwei Nachkommastellen erzeugen - optional auch mit Untergrenze
	 */
	private double zufallszahl(int grenze){
		double zufallszahl = Math.random()*grenze;
		int zufallszahl100 = (int) zufallszahl * 100;
		zufallszahl = zufallszahl100 / 100;
		
		return zufallszahl;
	}
}
