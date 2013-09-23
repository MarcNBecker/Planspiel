package de.planspiel.cafe;

import java.util.Vector;

public class Lager {
	
	private Vector<Produkt> produktListe;
	
	public Lager() {
		produktListe = new Vector<Produkt>();
	}
	
	/**
	 * 
	 * @param produkt
	 */
	public void einlagern(Produkt produkt) {
		// TODO
		//Falls Produkt noch nicht vorhanden
		produktListe.add(produkt);
		//Andernfalls schon vorhandenes Produkt mit neuem verschmelzen
		
	}
	
	public Produkt auslagern(Produkttypen name, int menge) {
		//TODO
		return null;
	}
	
	/**
	 * Sucht in den vorhandenen Produkten, ob das übergebene Produkt (nach Name) schon existiert
	 * @param name Produkttypen-Wert, der angibt welches Produkt gesucht wird
	 * @return Null falls das Produkt noch nicht existiert, Produkt wenn es gefunden wurde
	 * @author Natalie
	 */
	public Produkt suchenProdukt(Produkttypen name) {
		for (int i = 0; i < produktListe.size(); i++){
			if (produktListe.get(i).holeName() == name){
				return produktListe.get(i);
			}
		}
		//falls Produkt nicht vorhanden
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
