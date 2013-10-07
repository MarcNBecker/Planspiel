package de.planspiel.entscheidung;

import java.util.Vector;

import de.planspiel.cafe.Haendler;
import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Unternehmenskette;


public class RohstoffEntscheidung extends Entscheidung {

	private Haendler haendler;
	private Produkttyp produkttyp;
	private int menge;
	
	public RohstoffEntscheidung(Unternehmenskette kette, Haendler haendler, Produkttyp produkttyp, int menge) {
		super(kette);
		this.haendler = haendler;
		this.produkttyp = produkttyp;
		this.menge = menge;
	}
	
	public void ausfuehren() {
		if(haendler != null && produkttyp != null && menge > 0) {
			Vector<Produkt> einkaufsliste = new Vector<Produkt>();
			einkaufsliste.add(new Produkt(produkttyp, menge));
			kette.holeLager().einkaufen(einkaufsliste, haendler);
		}
	}

}
