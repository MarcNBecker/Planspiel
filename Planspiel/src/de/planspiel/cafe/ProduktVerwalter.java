package de.planspiel.cafe;

import java.util.Vector;

/**
 * Abstrakte Klasse für alle Verwalter eines Produkts
 * @author Marc
 */
public abstract class ProduktVerwalter {

	private Vector<Produkt> produktListe;
	
	public ProduktVerwalter(){
		this.produktListe = new Vector<Produkt>();
	}
	
	/**
	 * @return Gibt die Produktliste zurück
	 */
	public final Vector<Produkt> holeProduktliste() {
		return this.produktListe;
	}
	
	/**
	 * Sucht in den vorhandenen Produkten, ob das übergebene Produkt (nach Name) schon existiert
	 * @param name Produkttypen-Wert, der angibt welches Produkt gesucht wird
	 * @return Null falls das Produkt noch nicht existiert, Produkt wenn es gefunden wurde
	 * @author Natalie
	 */
	public final Produkt suchenProdukt(Produkttyp name) {
		for (int i = 0; i < holeProduktliste().size(); i++){
			if (holeProduktliste().get(i).holeName() == name){
				return holeProduktliste().get(i);
			}
		}
		//falls Produkt nicht vorhanden
		return null;
	}
	
	/**
	 * Fügt ein neues Produkt hinzu
	 * @param produkt Produkt, dass nicht null ist
	 */
	public void hinzufuegenProdukt(Produkt produkt) {
		if(produkt != null) {
			this.produktListe.add(produkt);
		}
	}
	
}
