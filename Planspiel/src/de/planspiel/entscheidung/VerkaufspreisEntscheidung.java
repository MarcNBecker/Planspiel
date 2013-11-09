package de.planspiel.entscheidung;

import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Unternehmenskette;

/**
 * Abstraktion einer Entscheidung zur Änderung des Verkaufspreises eines
 * Produkts
 * 
 * @author Marc Becker
 */
public class VerkaufspreisEntscheidung extends Entscheidung {

	private Produkttyp produkttyp;
	private double vkpreis;

	/**
	 * Erzeugt eine neue Entscheidung, die Verkaufspreise zu verändern
	 * 
	 * @param kette
	 *            Unternehmenskette, die die Entscheidung getroffen hat
	 * @param produkttyp
	 *            Produkt, für welches die Verkaufspreise geändert werden sollen
	 * @param vkpreis
	 *            neuer Verkauspreis
	 */
	public VerkaufspreisEntscheidung(Unternehmenskette kette, Produkttyp produkttyp, double vkpreis) {
		super(kette);
		this.produkttyp = produkttyp;
		this.vkpreis = vkpreis;
	}

	/**
	 * Führt die Entscheidung aus Ändert die Verkaufspreise
	 */
	public void ausfuehren() {
		Produkt p = kette.holeLager().suchenProdukt(produkttyp);
		if (p != null) {
			p.setzePreis(vkpreis);
		}
	}

}
