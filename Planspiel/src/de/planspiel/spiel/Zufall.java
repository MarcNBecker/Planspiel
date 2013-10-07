package de.planspiel.spiel;

/**
 * Klasse zur Generierung von Zufallszahlen und zum Treffen von Entscheidungen.
 * Kann zu Testzwecken verwendet werden, indem Testmodus und Testentscheidung und Testzufallszahl gesetzt werden.
 * @author Ann-Kathrin Gessat
 *
 */

public class Zufall {

	private static boolean testModus = false;
	private static boolean testEntscheidung = false;
	private static double testZufallszahl = 0.0;

	/**
	 * Zufallszahl zwischen 0 und Grenze
	 * optional auch mit Untergrenze
	 */
	public static double generierenZufallszahl(double grenze) {
		if (Zufall.holeTestModus())
			return Zufall.holeTestZufallszahl();
		double zufallszahl = Math.random() * grenze;
		//int zufallszahl100 = (int) zufallszahl * 100;
		//zufallszahl = zufallszahl100 / 100;
		return zufallszahl;
	}

	/**
	 * Liefert zurück, ob Entscheidung getroffen wird oder nicht.
	 * @param wahrscheinlichkeit
	 * @return true oder false
	 */
	public static boolean treffenEntscheidung(double wahrscheinlichkeit) {
		if (Zufall.holeTestModus()) {
			return Zufall.holeTestEntscheidung();
		}
		double zufallszahl = generierenZufallszahl(1);
		if (zufallszahl <= wahrscheinlichkeit)
			return true;
		return false;
	}

	/**
	 * 
	 * @return testModus
	 */
	public static boolean holeTestModus() {
		return Zufall.testModus;
	}

	/**
	 * Setzt den Test-Modus auf true oder false.
	 * @param testmodus
	 */
	public static void setzeTestmodus(boolean testmodus) {
		Zufall.testModus = testmodus;
	}

	/**
	 * 
	 * @return testEntscheidung
	 */
	public static boolean holeTestEntscheidung() {
		return Zufall.testEntscheidung;
	}

	/**
	 * Setzt die Test-Entscheidung auf true oder false.
	 * @param testentscheidung
	 */
	public static void setzeTestEntscheidung(boolean testentscheidung) {
		Zufall.testEntscheidung = testentscheidung;
	}

	/**
	 * 
	 * @return testZufallszahl
	 */
	public static double holeTestZufallszahl() {
		return Zufall.testZufallszahl;
	}

	/**
	 * Setzt die Test-Zufallszahl
	 * @param testzufallszahl
	 */
	public static void setzeTestZufallszahl(double testzufallszahl) {
		Zufall.testZufallszahl = testzufallszahl;
	}
}
