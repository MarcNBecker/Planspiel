package de.planspiel.cafe;

public class Zufall {

	private static boolean testModus = false;
	private static boolean testEntscheidung = false;
	private static double testZufallszahl = 0.0;

	/**
	 * Zufallszahl zwischen 0 und Grenzen mit zwei Nachkommastellen erzeugen -
	 * optional auch mit Untergrenze
	 */
	public static double generierenZufallszahl(int grenze) {
		if (Zufall.holeTestModus())
			return Zufall.holeTestZufallszahl();
		double zufallszahl = Math.random() * grenze;
		int zufallszahl100 = (int) zufallszahl * 100;
		zufallszahl = zufallszahl100 / 100;
		return zufallszahl;
	}

	public static boolean treffenEntscheidung(double wahrscheinlichkeit) {
		if (Zufall.holeTestModus()) {
			return Zufall.holeTestEntscheidung();
		}
		double zufallszahl = generierenZufallszahl(1);
		if (zufallszahl <= wahrscheinlichkeit)
			return true;
		return false;
	}

	public static boolean holeTestModus() {
		return Zufall.testModus;
	}

	public static void setzeTestmodus(boolean testmodus) {
		Zufall.testModus = testmodus;
	}

	public static boolean holeTestEntscheidung() {
		return Zufall.testEntscheidung;
	}

	public static void setzeTestEntscheidung(boolean testentscheidung) {
		Zufall.testEntscheidung = testentscheidung;
	}

	public static double holeTestZufallszahl() {
		return Zufall.testZufallszahl;
	}

	public static void setzeTestZufallszahl(double testzufallszahl) {
		Zufall.testZufallszahl = testzufallszahl;
	}
}
