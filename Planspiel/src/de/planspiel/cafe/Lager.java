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
	
	public void ausw�hlenH�ndler(Haendler h�ndler) {
		
	}
	
	public Vector<Produkt> holeProduktliste() {
		return this.produktliste;
	}
	
	public void hinzuf�genProdukt(Produkt produkt) {
		this.produktliste.add(produkt);
	}
	
	
}
