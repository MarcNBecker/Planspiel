package de.planspiel.cafe;

public class PreisQualitaetVerhaeltnis {
	
	private double faktor;
	
	/**
	 * Erzeugt ein neues Verhältnis von Preis zu Qualität mit dem gegeben Faktor
	 * @param faktor Beeinflusst das Verhältnis und kann grob als maximaler Preis, also dem Preis bei Qualität gegen 1 angesehen werden
	 * 			Der Faktor sollte zwischen 3 und 7 liegen, ansonsten wird ein zufälliger Faktor gewählt
	 */
	public PreisQualitaetVerhaeltnis(double faktor){
		setzeFaktor(faktor);
	}
	
	/**
	 * Erzeugt ein neues Verhältnis von Preis zu Qualität mit einem zufälligen Faktor
	 */
	public PreisQualitaetVerhaeltnis() {
		setzeFaktor(0);
	}
	
	/**
	 * Berechnet den Preis in Abhängigkeit der Qualität
	 * @param qualitaet Qualität zwischen 0 und 1
	 * @return Preis passend zur Qualität, abhängig vom Preis Qualität Verhältnis
	 */
	public double berechnenPreis(double qualitaet) {
		double a = holeFaktor();
		double x = qualitaet;
		double e = a * Math.log(x+1.0) + 2.0 * a * Math.pow(x, 5.0) - a * Math.pow(x, 2.0) - 0.5 * Math.pow(Math.E, Math.pow(x, 9) + Math.pow(x, 3) - x * (1/a)) + 0.5;
		//a*ln(x+1)+2*a*x^5-a*x^2-0.5*e^(x^9+x^3-(1/a)*x)+0.5
		return e;
	}
	
	/**
	 * Setzt den Faktor des Preis Qualitäts Verhältnisses neu
	 * @param faktor Beeinflusst das Verhältnis und kann grob als maximaler Preis, also dem Preis bei Qualität gegen 1 angesehen werden
	 * 			Der Faktor sollte zwischen 3 und 7 liegen, ansonsten wird ein zufälliger Faktor gewählt
	 */
	public void setzeFaktor(double faktor) {
		if(faktor >= 3 && faktor <= 7) {
			this.faktor = faktor;	
		} else {
			this.faktor = Zufall.generierenZufallszahl(4) + 3.0;
		}
	}
	
	/**
	 * @return Faktor: Beeinflusst das Verhältnis und kann grob als maximaler Preis, also dem Preis bei Qualität gegen 1 angesehen werden
	 * 		Der Faktor sollte zwischen 3 und 7 liegen, ansonsten wird ein zufälliger Faktor gewählt
	 */
	public double holeFaktor() {
		return faktor;
	}
}
