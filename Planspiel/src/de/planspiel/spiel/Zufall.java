package de.planspiel.spiel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Klasse zur Generierung von Zufallszahlen und zum Treffen von Entscheidungen.
 * Kann zu Testzwecken verwendet werden, indem Testmodus und Testentscheidung und Testzufallszahl gesetzt werden.
 * @author Ann-Kathrin Gessat
 *
 */

public class Zufall {
	
	private static boolean testModus = false;
	private static boolean dateiTestModus = false;
	private static boolean protokollModus = false;
	private static String zufallDateiname = "zufall.txt";
	private static BufferedReader reader;
	private static PrintWriter writer;
	private static boolean testEntscheidung = false;
	private static double testZufallszahl = 0.0;
	private static double testQualitaet = 0.0;

	/**
	 * Zufallszahl zwischen 0 und Grenze
	 * optional auch mit Untergrenze
	 */
	public static double generierenZufallszahl(double grenze) {
		if(Zufall.holeTestModus()) {
			if(Zufall.holeDateiTestModus()) {
				return Zufall.leseZufallszahlAusDatei();
			}
			return Zufall.holeTestZufallszahl();	
		}
		double zufallszahl = Math.random() * grenze;
		//int zufallszahl100 = (int) zufallszahl * 100;
		//zufallszahl = zufallszahl100 / 100;
		if(Zufall.protokollModus == true) {
			protokolliereZufallszahl(zufallszahl);
		}
		return zufallszahl;
	}

	/**
	 * Liefert zurück, ob Entscheidung getroffen wird oder nicht.
	 * @param wahrscheinlichkeit
	 * @return true oder false
	 */
	public static boolean treffenEntscheidung(double wahrscheinlichkeit) {
		if (Zufall.holeTestModus() && !Zufall.holeDateiTestModus()) {
			return Zufall.holeTestEntscheidung();
		}
		double zufallszahl = generierenZufallszahl(1);
		if (zufallszahl <= wahrscheinlichkeit)
			return true;
		return false;
	}
	
	/**
	 * Generiert eine zufällige Qualität, die entweder dem A oder C Markt zuzuordnen ist
	 * eine schwache Mindestqualität ist beispielsweise wahrscheinlicher als eine große Qualität
	 * @return zufällige Qualität
	 */
	public static double generierenQualitaet() {
		if(Zufall.holeTestModus() && !Zufall.holeDateiTestModus()) {
			return Zufall.holeTestQualitaet();	
		}
		if(Zufall.treffenEntscheidung(2.0/3.0)) { // C-Markt Qualität von 0 - 0.6
			double nv = Zufall.generierenNVZufallszahl();
			if (nv > 3.0) { //alle über 3 abkappen
				nv = 3.0;
			} else if (nv < -3.0) { //alle unter 3 abkappen
				nv = -3.0;
			}
			double anv = nv + 3.0; //von 0 bis 6 statt von -3 bis 3 laufen lassen
			double prozent = anv / 6; // Standardabweichung als Pronzentsatz
			double maxQC = 0.6; // Maximale Qualität vom C-Markt
			double qualitaet = maxQC * prozent; // Qualität von 0.0 bis 0.6
			return qualitaet;
		} else { // A-Markt Qualität von 0.6 - 1
			double nv = Zufall.generierenNVZufallszahl();
			if (nv > 3.0) { //alle über 3 abkappen
				nv = 3.0;
			} else if (nv < -3.0) { //alle unter 3 abkappen
				nv = -3.0;
			}
			double anv = nv + 3.0; //von 0 bis 6 statt von -3 bis 3 laufen lassen
			double prozent = anv / 6; // Standardabweichung als Pronzentsatz
			double maxQA = 0.4; // Maximale Qualität vom A-Markt (muss dann mit 0.6 addiert werden)
			double qualitaet = (maxQA * prozent) + 0.6;	 // Qualitaet von 0.6 bis 1
			return qualitaet;
		}
	}
	
	public static double generierenNVZufallszahl() {
		if(Zufall.holeDateiTestModus() && Zufall.holeTestModus()) {
			return Zufall.leseZufallszahlAusDatei();
		} else {
			Random r = new Random();
			double nv = r.nextGaussian();
			if(Zufall.holeProtokollModus()) {
				Zufall.protokolliereZufallszahl(nv);
			}
			return nv;
		}
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
	 * @return dateiTestModus
	 */
	public static boolean holeDateiTestModus() {
		return Zufall.dateiTestModus;
	}
	
	/**
	 * Setzt den Test-Modus auf true oder false.
	 * @param testmodus
	 */
	public static void setzeProtokollModus(boolean bProtokollModus) {
		Zufall.protokollModus = bProtokollModus;
	}
	
	public static void setzeProtokollModus(boolean bProtokollModus, String sZufallDateiname) {
		zufallDateiname = sZufallDateiname;
		setzeProtokollModus(bProtokollModus);
	}
	
	/**
	 * 
	 * @return dateiTestModus
	 */
	public static boolean holeProtokollModus() {
		return Zufall.protokollModus;
	}

	/**
	 * Setzt den Datei Test-Modus auf true oder false.
	 * @param testmodus
	 */
	public static void setzeDateiTestmodus(boolean bDateiTestModus) {
		if (Zufall.dateiTestModus == false) {
			try {
				reader = new BufferedReader(new FileReader(zufallDateiname));
			} catch (Exception e) {
				return;
			}
		} else {
			try {
				reader.close();
			} catch (Exception e) {}
			reader = null;
		}
		Zufall.dateiTestModus = bDateiTestModus;
	}

	public static void setzeDateiTestmodus(boolean bDateiTestModus, String sZufallDateiname) {
		zufallDateiname = sZufallDateiname;
		setzeDateiTestmodus(bDateiTestModus);
	}
	
	/**
	 * Liest eine Zahl aus der Zufall-Datei aus
	 */
	public static double leseZufallszahlAusDatei() {
		String zeile = "0";
		try {
			zeile = reader.readLine();
			if(zeile == null) {
				reader.close();
				reader = new BufferedReader(new FileReader(zufallDateiname));
				zeile = reader.readLine();
				if (zeile == null) {
					zeile = "0";
				}
			}
		} catch (Exception e) {}
		return Double.parseDouble(zeile);
	}
	
	/**
	 * Protokolliert eine Zufallszahl mit
	 */
	public static void protokolliereZufallszahl(double d) {
		if(writer == null) {
			try {
				writer = new PrintWriter(zufallDateiname);
			} catch (FileNotFoundException e) {
				return;
			}
		}
		writer.println(d);
		writer.flush();
	}
	
	public static void schliessenProtokoll() {
		try {
			writer.close();
		} catch (Exception e) {}
	}
	/**
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
	
	/**
	 * Setzt die Test-Qualität
	 * @param testQualitaet
	 */
	public static void setzeTestQualitaet(double testQualitaet) {
		Zufall.testQualitaet = testQualitaet;
	}
	
	/**
	 * @return Test-Qualität
	 */
	public static double holeTestQualitaet() {
		return Zufall.testQualitaet;
	}
}
