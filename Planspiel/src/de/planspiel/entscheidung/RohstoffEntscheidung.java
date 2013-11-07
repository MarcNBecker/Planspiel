package de.planspiel.entscheidung;

import java.util.Vector;

import de.planspiel.cafe.Haendler;
import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Unternehmenskette;

/**
 * Abstraktion einer Entscheidung zum Kauf von Produkten
 * 
 * @author Marc
 */
public class RohstoffEntscheidung extends Entscheidung {

	private Haendler haendler;
	private Produkttyp produkttyp;
	private int menge;

	/**
	 * Erstellt eine neue Entscheidung zum Einkauf von Rohstoffen
	 * 
	 * @param kette
	 *            Unternehmenskette, die die Entscheidung getroffen hat
	 * @param haendler
	 *            Händler, bei dem die Rohstoffe gekauft werden
	 * @param produkttyp
	 *            Produkttyp, der eingekauft werden soll
	 * @param menge
	 *            Menge, die gekauft werden soll
	 */
	public RohstoffEntscheidung(Unternehmenskette kette, Haendler haendler, Produkttyp produkttyp, int menge) {
		super(kette);
		this.haendler = haendler;
		this.produkttyp = produkttyp;
		this.menge = menge;
	}

	/**
	 * Führt die Entscheidung aus Erstellt eine Einkaufsliste und kauft ein
	 */
	public void ausfuehren() {
		if (haendler != null && produkttyp != null && menge > 0) {
			Vector<Produkt> einkaufsliste = new Vector<Produkt>();
			einkaufsliste.add(new Produkt(produkttyp, menge));
			kette.holeLager().einkaufen(einkaufsliste, haendler);
		}
	}

}
