package de.planspiel.cafe;

import java.util.Vector;

/**
 * Abstrakte Klasse f�r alle Verwalter eines Produkts
 * 
 * @author Marc Becker, Natalie Buchner
 */
public abstract class ProduktVerwalter {

	private Vector<Produkt> produktListe;

	/**
	 * Erzeugt einen neuen Produktverwalter und erzeugt eine Produktliste
	 */
	public ProduktVerwalter() {
		this.produktListe = new Vector<Produkt>();
	}

	/**
	 * @return Gibt die Produktliste zur�ck
	 */
	public Vector<Produkt> holeProduktliste() {
		return this.produktListe;
	}

	/**
	 * Sucht in den vorhandenen Produkten, ob das �bergebene Produkt (nach Name)
	 * schon existiert
	 * 
	 * @param name
	 *            Produkttypen-Wert, der angibt welches Produkt gesucht wird
	 * @return Null falls das Produkt noch nicht existiert, Produkt wenn es
	 *         gefunden wurde
	 */
	public Produkt suchenProdukt(Produkttyp name) {
		for (int i = 0; i < holeProduktliste().size(); i++) {
			if (holeProduktliste().get(i).holeName() == name) {
				return holeProduktliste().get(i);
			}
		}
		// falls Produkt nicht vorhanden
		return null;
	}

	/**
	 * F�gt ein neues Produkt hinzu
	 * 
	 * @param produkt
	 *            Produkt, dass nicht null ist
	 */
	public void hinzufuegenProdukt(Produkt produkt) {
		if (produkt != null) {
			this.produktListe.add(produkt);
		}
	}

	/**
	 * Leert die Produktliste
	 */
	public void leeren() {
		this.produktListe = new Vector<Produkt>();
	}
}
