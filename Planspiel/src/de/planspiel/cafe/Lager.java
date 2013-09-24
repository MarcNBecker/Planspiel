package de.planspiel.cafe;

import java.util.Vector;

/**
 * Klasse zur Organisation der Produktbest�nde eines Unternehmens
 * @author Natalie
 *
 */
public class Lager {
	
	private Vector<Produkt> produktListe;
	private Unternehmenskette kette;
	
	/**
	 * Erzeugt eines neues Lager, welches zu einer bestimmten Unternehmenskette geh�rt
	 * @param kette
	 */
	public Lager(Unternehmenskette kette) {
		produktListe = new Vector<Produkt>();
		this.kette = kette;
	}
	
	/**
	 * Lagert das �bergebene Produkt ein, falls es schon vorhanden ist werden die Produkte miteinander verschmolzen
	 * @param produkt Produkt, das eingelagert werden soll
	 * @author Natalie
	 */
	public void einlagern(Produkt produkt) {
		Produkt gesuchtesProdukt = suchenProdukt(produkt.holeName());
		if (gesuchtesProdukt == null){ //Falls Produkt noch nicht vorhanden
			hinzufuegenProdukt(produkt);
		} else {
			gesuchtesProdukt.verschmelzen(produkt);//Andernfalls schon vorhandenes Produkt mit neuem verschmelzen
		}
	}
	
	/**
	 * Lagert eine bestimmte Menge eines Produktes aus dem Lager aus - falls weniger auf Lager ist, wird auch nur die geringere Menge ausgelagert
	 * @param name Produkttypen-Wert, welches Produkt ausgelagert werden soll
	 * @param menge int-Wert, welche Menge ausgelagert werden soll
	 * @return Null, falls das Produkt nicht vorhanden ist, ansonsten ein neues Produkt, welches die ausgelagerte Menge enth�lt sowie entsprechende Qualit�t und Preis
	 * @author Natalie
	 */
	public Produkt auslagern(Produkttyp name, int menge) {
		Produkt gesuchtesProdukt = suchenProdukt(name);		
		if (gesuchtesProdukt == null){
			return null;
		} else { //Falls Produkt vorhanden - neues Produkt zur�ckgeben, das gleiche Eigenschaften hat, und (maximal m�gliche) Menge
			Produkt rueckgabeProdukt = new Produkt(gesuchtesProdukt.holeName());
			int vorhandeneMenge = gesuchtesProdukt.holeMenge();
			if (vorhandeneMenge < menge){ //Falls weniger da ist als der Kunde haben will - R�ckgabemenge ist die vorhandene Menge!! Nichts mehr im Lager
				gesuchtesProdukt.setzeMenge(0);
				rueckgabeProdukt.setzeMenge(vorhandeneMenge);
			} else { //Es ist noch genug auf Lager - Vom Lager was weg nehmen und R�ckgabemenge ist die gew�nschte Menge
				gesuchtesProdukt.setzeMenge(vorhandeneMenge - menge);
				rueckgabeProdukt.setzeMenge(menge);
			}	
			//Preis und Qualit�t �bernehmen vom Lagerprodukt
			rueckgabeProdukt.setzePreis(gesuchtesProdukt.holePreis());
			rueckgabeProdukt.setzeQualitaet(gesuchtesProdukt.holeQualitaet());
			return rueckgabeProdukt;
		}		
	}
	
	/**
	 * Sucht in den vorhandenen Produkten, ob das �bergebene Produkt (nach Name) schon existiert
	 * @param name Produkttypen-Wert, der angibt welches Produkt gesucht wird
	 * @return Null falls das Produkt noch nicht existiert, Produkt wenn es gefunden wurde
	 * @author Natalie
	 */
	public Produkt suchenProdukt(Produkttyp name) {
		for (int i = 0; i < holeProduktliste().size(); i++){
			if (holeProduktliste().get(i).holeName() == name){
				return holeProduktliste().get(i);
			}
		}
		//falls Produkt nicht vorhanden
		return null;
	}
	
	/**
	 * �bernimmt Daten der Entscheidung und kauft die gew�nschten Rohstoffe beim H�ndler ein,
	 * verbucht Kosten pro Rohstoff und lagert diesen ein!
	 * @param einkaufsliste	Vector<Produkt>, der die Namen und die Mengen der gew�nschten Produkte enth�lt
	 * @param haendler Haendler-Objekt, das angibt bei welchem H�ndler gekauft wird
	 * @author Natalie
	 */
	public void einkaufen(Vector<Produkt> einkaufsliste, Haendler haendler){
		Produkt einkaufProdukt = null;
		Produkt vergleichProdukt = null;
		for (int i = 0; i < einkaufsliste.size(); i++){
			einkaufProdukt = einkaufsliste.get(i);
			vergleichProdukt = haendler.suchenProdukt(einkaufProdukt.holeName());		
			//vom Haendler EKPreis und Qualitaet auslesen und im neuen Vektor speichern
			einkaufProdukt.setzeEkpreis(vergleichProdukt.holeEkpreis());
			einkaufProdukt.setzeQualitaet(vergleichProdukt.holeQualitaet());
			//Rohstoffkosten verbuchen
			holeKette().verbuchenKosten(Kostenverursacher.ROHSTOFF, (einkaufProdukt.holeMenge()*einkaufProdukt.holeEkpreis()));
			//Pro Produkt einlagern
			einlagern(einkaufProdukt);
		}		
	}
	
	/**
	 * @return Gibt den Lagerbestand, zur�ck
	 */
	public Vector<Produkt> holeProduktliste() {
		return this.produktListe;
	}
	
	/**
	 * F�gt ein neues Produkt hinzu
	 * @param produkt Produkt, dass nicht null ist
	 */
	private void hinzufuegenProdukt(Produkt produkt) {
		if(produkt != null) {
			this.produktListe.add(produkt);
		}
	}
	
	/**
	 * @return Gibt die Kette des Lagers zur�ck
	 */
	public Unternehmenskette holeKette(){
		return kette;
	}
}
