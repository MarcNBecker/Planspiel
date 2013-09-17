package de.planspiel.cafe;

import java.util.Vector;

public class Lager {
	private Vector<Produkt> produktliste;
	
	public Lager() {
		
	}
	
	public void einlagern(Produkt produkt) {
		
	}
	
	public Produkt auslagern(String name, int menge) {
		return null;
	}
	
	public Produkt suchenProdukt(String name) {
		return null;
	}
	
	public void auswählenHändler(Haendler händler) {
		
	}
	
	public Vector<Produkt> holeProduktliste() {
		return this.produktliste;
	}
	
	public void hinzufügenProdukt(Produkt produkt) {
		this.produktliste.add(produkt);
	}
	
	
}
