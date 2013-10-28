package de.planspiel.konsolengui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import de.planspiel.spiel.Spiel;

public abstract class KonsolenGUI {
	
	private static boolean testModus = false;
	
	protected static Spiel spiel;
	protected static BufferedReader reader;
	protected static PrintWriter writer;
	
	public KonsolenGUI() {
		if(spiel != null) {
			return;
		}
		spiel = Spiel.holeSpiel();
		if(testModus) {
			try {
				reader = new BufferedReader(new FileReader("input.txt"));
				writer = new PrintWriter("output.txt");
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
	
	public static void setzeTestModus(boolean bTestModus) {
		testModus = bTestModus;
	}
	
	public static void close() {
		try {
			reader.close();
			writer.close();
		} catch (Exception e) {}
	}
	
	public abstract void run();
}
