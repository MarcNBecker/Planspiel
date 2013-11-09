package de.planspiel.entscheidung;

import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Unternehmenskette;

/**
 * Abstraktion einer Entscheidung zur �nderung des Verkaufspreises eines
 * Produkts
 * 
 * @author Marc Becker
 */
public class VerkaufspreisEntscheidung extends Entscheidung {

	private Produkttyp produkttyp;
	private double vkpreis;

	/**
	 * Erzeugt eine neue Entscheidung, die Verkaufspreise zu ver�ndern
	 * 
	 * @param kette
	 *            Unternehmenskette, die die Entscheidung getroffen hat
	 * @param produkttyp
	 *            Produkt, f�r welches die Verkaufspreise ge�ndert werden sollen
	 * @param vkpreis
	 *            neuer Verkauspreis
	 */
	public VerkaufspreisEntscheidung(Unternehmenskette kette, Produkttyp produkttyp, double vkpreis) {
		super(kette);
		this.produkttyp = produkttyp;
		this.vkpreis = vkpreis;
	}

	/**
	 * F�hrt die Entscheidung aus �ndert die Verkaufspreise
	 */
	public void ausfuehren() {
		Produkt p = kette.holeLager().suchenProdukt(produkttyp);
		if (p != null) {
			p.setzePreis(vkpreis);
		}
	}

}
