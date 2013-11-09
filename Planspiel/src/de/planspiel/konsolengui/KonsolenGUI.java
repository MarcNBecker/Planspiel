package de.planspiel.konsolengui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import de.planspiel.spiel.Spiel;
import de.planspiel.test.SiegbedingungsTest;

/**
 * Abstrakte Klasse aller Interpreter, die Befehle der DSL entgegennehmen Allen
 * Interpreter stehen ein globaler in und out Stream zur Verfügung, welche von
 * der abstrakten Klasse verwaltet werden.
 * 
 * @author Marc Becker
 */
public abstract class KonsolenGUI {

	private static boolean testModus = false;
	private static String inDateiname = "input.txt";
	private static String outDateiname = "output.txt";
	public static SiegbedingungsTest jUnitTestKlasse;

	protected static Spiel spiel;
	protected static BufferedReader reader;
	protected static PrintWriter writer;

	/**
	 * Erstellt die statischen Attribute der Interpreter
	 * Im Testmodus liest der Interpreter aus einer Datei aus.
	 * Im normalen Spielablauf werden Javas STDIN und STDOUT herangezogen
	 */
	public KonsolenGUI() {
		if (spiel != null) {
			return;
		}
		spiel = Spiel.holeSpiel();
		if (testModus) {
			try {
				reader = new BufferedReader(new FileReader(inDateiname));
				writer = new PrintWriter(outDateiname);
			} catch (Exception e) {
				reader = new BufferedReader(new InputStreamReader(System.in));
				writer = new PrintWriter(System.out, true);
				writer.println("---------- INITIALISIERUNG DER DATEI AUSLESUNG FEHLGESCHLAGEN ----------");
				writer.println("---------- WECHSEL AUF STANDARD IN UND OUT ----------");
			}
		} else {
			reader = new BufferedReader(new InputStreamReader(System.in));
			writer = new PrintWriter(System.out, true);
		}
	}
	
	/**
	 * Setzt den Testmodus
	 * @param bTestModus TestModus
	 */
	public static void setzeTestModus(boolean bTestModus) {
		testModus = bTestModus;
	}

	/**
	 * Setzt den Testmodus unter angabe einer Klasse, die die Siegbedingung definiert
	 * @param bTestModus TestModus
	 * @param oJUnitTestKlasse Klasse die zur Verwaltung der Siegbedingung und Siegerfüllung vorhanden ist.
	 * 			Die abstrakte Klasse SiegbedingungsTest sollte eine Oberklasse vom aufrufenden JUnit Test sein 
	 */
	public static void setzeTestModus(boolean bTestModus, SiegbedingungsTest oJUnitTestKlasse) {
		jUnitTestKlasse = oJUnitTestKlasse;
		setzeTestModus(bTestModus);
	}

	/**
	 * Setzt des Testmodus unter Angabe der Dateinamen für Input und Output
	 * @param bTestModus TestModus
	 * @param sInDateiname Dateiname, der Datei mit den Input Befehlen
	 * @param sOutDateiname Dateiname, der Datei, die für den Output zuständig ist
	 */
	public static void setzeTestModus(boolean bTestModus, String sInDateiname, String sOutDateiname) {
		inDateiname = sInDateiname;
		outDateiname = sOutDateiname;
		setzeTestModus(bTestModus);
	}
	
	/**
	 * Setzt den TestModus unter Angabe von sowohl Dateinamen für Input und Output, als auch einer Klasse zur
	 * Verwaltung der Siegbedingung
	 * @param bTestModus TestModus
	 * @param sInDateiname Dateiname, der Datei mit den Input Befehlen
	 * @param sOutDateiname Dateiname, der Datei, die für den Output zuständig ist
	 * @param oJUnitTestKlasse Klasse die zur Verwaltung der Siegbedingung und Siegerfüllung vorhanden ist.
	 * 			Die abstrakte Klasse SiegbedingungsTest sollte eine Oberklasse vom aufrufenden JUnit Test sein 
	 */
	public static void setzeTestModus(boolean bTestModus, String sInDateiname, String sOutDateiname, SiegbedingungsTest oJUnitTestKlasse) {
		jUnitTestKlasse = oJUnitTestKlasse;
		setzeTestModus(bTestModus, sInDateiname, sOutDateiname);
	}
	
	/**
	 * Schließt alle von den Interpreter verwendeten Ressourcen
	 */
	public static void close() {
		try {
			reader.close();
			writer.close();
		} catch (Exception e) {
		}
	}
	/**
	 * Führt den Interpreter aus und interpretiert die Befehle
	 */
	public abstract void run();
}
