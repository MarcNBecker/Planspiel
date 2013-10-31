package de.planspiel.konsolengui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import de.planspiel.spiel.Spiel;

public abstract class KonsolenGUI {
	
	private static boolean testModus = false;
	private static String inDateiname = "input.txt";
	private static String outDateiname = "output.txt";
	
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
	
	public static void setzeTestModus(boolean bTestModus) {
		testModus = bTestModus;
	}
	
	public static void setzeTestModus(boolean bTestModus, String sInDateiname, String sOutDateiname) {
		inDateiname = sInDateiname;
		outDateiname = sOutDateiname;
		setzeTestModus(bTestModus);
	}
	
	public static void close() {
		try {
			reader.close();
			writer.close();
		} catch (Exception e) {}
	}
	
	public abstract void run();
}
