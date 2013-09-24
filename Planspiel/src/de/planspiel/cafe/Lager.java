package de.planspiel.cafe;

import java.util.Vector;

public class Lager {
	
	private Vector<Produkt> produktListe;
	
	public Lager() {
		produktListe = new Vector<Produkt>();
	}
	
	/**
	 * Lagert das übergebene Produkt ein, falls es schon vorhanden ist werden die Produkte miteinander verschmolzen
	 * @param produkt Produkt, das eingelagert werden soll
	 * @author Natalie
	 */
	public void einlagern(Produkt produkt) {
		Produkt gesuchtesProdukt = suchenProdukt(produkt.holeName());
		if (gesuchtesProdukt == null){ //Falls Produkt noch nicht vorhanden
			produktListe.add(produkt);
		} else {
			gesuchtesProdukt.verschmelzen(produkt);//Andernfalls schon vorhandenes Produkt mit neuem verschmelzen
		}
	}
	
	/**
	 * Lagert eine bestimmte Menge eines Produktes aus dem Lager aus - falls weniger auf Lager ist, wird auch nur die geringere Menge ausgelagert
	 * @param name Produkttypen-Wert, welches Produkt ausgelagert werden soll
	 * @param menge int-Wert, welche Menge ausgelagert werden soll
	 * @return Null, falls das Produkt nicht vorhanden ist, ansonsten ein neues Produkt, welches die ausgelagerte Menge enthält sowie entsprechende Qualität und Preis
	 * @author Natalie
	 */
	public Produkt auslagern(Produkttyp name, int menge) {
		Produkt gesuchtesProdukt = suchenProdukt(name);		
		if (gesuchtesProdukt == null){
			return null;
		} else { //Falls Produkt vorhanden - neues Produkt zurückgeben, das gleiche Eigenschaften hat, und (maximal mögliche) Menge
			Produkt rueckgabeProdukt = new Produkt(gesuchtesProdukt.holeName());
			int vorhandeneMenge = gesuchtesProdukt.holeMenge();
			if (vorhandeneMenge < menge){ //Falls weniger da ist als der Kunde haben will - Rückgabemenge ist die vorhandene Menge!! Nichts mehr im Lager
				gesuchtesProdukt.setzeMenge(0);
				rueckgabeProdukt.setzeMenge(vorhandeneMenge);
			} else { //Es ist noch genug auf Lager - Vom Lager was weg nehmen und Rückgabemenge ist die gewünschte Menge
				gesuchtesProdukt.setzeMenge(vorhandeneMenge - menge);
				rueckgabeProdukt.setzeMenge(menge);
			}	
			//Preis und Qualität übernehmen vom Lagerprodukt
			rueckgabeProdukt.setzePreis(gesuchtesProdukt.holePreis());
			rueckgabeProdukt.setzeQualitaet(gesuchtesProdukt.holeQualitaet());
			return rueckgabeProdukt;
		}		
	}
	
	/**
	 * Sucht in den vorhandenen Produkten, ob das übergebene Produkt (nach Name) schon existiert
	 * @param name Produkttypen-Wert, der angibt welches Produkt gesucht wird
	 * @return Null falls das Produkt noch nicht existiert, Produkt wenn es gefunden wurde
	 * @author Natalie
	 */
	public Produkt suchenProdukt(Produkttyp name) {
		for (int i = 0; i < produktListe.size(); i++){
			if (produktListe.get(i).holeName() == name){
				return produktListe.get(i);
			}
		}
		//falls Produkt nicht vorhanden
		return null;
	}
	
	public void einkaufen(Vector<Produkt> einkaufsliste, Haendler haendler){
		//TODO Vector hat Namen und Mengen, wir müssen:
		for (int i = 0; i < einkaufsliste.size(); i++){
			haendler.sucheProdukt(einkaufsliste.get(i).holeName());
		}
		
		//vom Haendler EKPreis und Qualitaet auslesen pro Produkt und im neuen Vektor speichern
		//Rohstoffkosten pro Produkt verbuchen
		//Pro Produkt einlagern
	}
	
	public Vector<Produkt> holeProduktliste() {
		return this.produktListe;
	}
	
	public void hinzufuegenProdukt(Produkt produkt) {
		this.produktListe.add(produkt);
	}
		
}
