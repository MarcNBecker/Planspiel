package de.planspiel.entscheidung;

import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Unternehmenskette;


public class VerkaufspreisEntscheidung extends Entscheidung {

	private Produkttyp produkttyp;
	private double vkpreis;
	
	public VerkaufspreisEntscheidung(Unternehmenskette kette, Produkttyp produkttyp, double vkpreis) {
		super(kette);
		this.produkttyp = produkttyp;
		this.vkpreis = vkpreis;
	}
	
	public void ausfuehren() {
		Produkt p = kette.holeLager().suchenProdukt(produkttyp);
		p.setzePreis(vkpreis);
	}

}
