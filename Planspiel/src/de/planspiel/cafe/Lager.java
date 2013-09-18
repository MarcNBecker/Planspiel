package de.planspiel.cafe;

import java.util.Vector;

public class Lager {
	
	private Vector<Produkt> produktListe;
	
	public Lager() {
		produktListe = new Vector<Produkt>();
	}
	
	public void einlagern(Produkt produkt) {
		// TODO
	}
	
	public Produkt auslagern(String name, int menge) {
		// TODO
		return null;
	}
	
	public Produkt suchenProdukt(String name) {
		// TODO
		return null;
	}
	
	public void auswaehlenHaendler(Haendler haendler) {
		// TODO
	}
	
	public Vector<Produkt> holeProduktliste() {
		return this.produktListe;
	}
	
	public void hinzufuegenProdukt(Produkt produkt) {
		this.produktListe.add(produkt);
	}
		
}
