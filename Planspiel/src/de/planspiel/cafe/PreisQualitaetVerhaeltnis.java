package de.planspiel.cafe;

/**
 * Klasse zur Darstellung des Zusammenhanges zwischen Qualität und Preis
 * 
 * @author Marc Becker
 */

public class PreisQualitaetVerhaeltnis {

	/**
	 * Berechnet den Preis in Abhängigkeit der Qualität
	 * 
	 * @param qualitaet
	 *            Qualität zwischen 0 und 1
	 * @return Preis passend zur Qualität, abhängig vom Preis Qualität
	 *         Verhältnis
	 */
	public static double berechnenPreisFaktor(double qualitaet) {
		double a = 3.5;
		double x = qualitaet;
		if (x < 0.1) {
			x = 0.1;
		}
		return (berechnen(x, a) / berechnen(1.0, a));
	}

	/**
	 * Berechnet Werte aus der zugrundeliegenden PreisQualitätsfunktion
	 * 
	 * @param x
	 *            Qualität
	 * @param a
	 *            Faktor zur Beeinflussung
	 * @return Preisfaktor
	 */
	private static double berechnen(double x, double a) {
		return a * Math.log(x + 1.0) + 2.0 * a * Math.pow(x, 5.0) - a * Math.pow(x, 2.0) - 0.5 * Math.pow(Math.E, Math.pow(x, 9) + Math.pow(x, 3) - x * (1 / a)) + 0.5;
		// a*ln(x+1)+2*a*x^5-a*x^2-0.5*e^(x^9+x^3-(1/a)*x)+0.5
	}

}
