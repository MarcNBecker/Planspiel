package de.planspiel.cafe;

public class Kredit {
	
	private static double aktuellerZinssatz;
	private static int aktuelleLaufzeit;
	
	private double restbetrag;
	private double zinssatz;
	private double tilgung;
	private int laufzeit;

	public Kredit(double betrag) {
		this.zinssatz = aktuellerZinssatz;
		this.laufzeit = aktuelleLaufzeit;
		this.restbetrag = betrag;
		this.tilgung = (int) betrag / laufzeit; // logisch?
	}

	public void tilgen() {
		// TODO
	}
	
	public static double holeAktuellerZinssatz() {
		return aktuellerZinssatz;
	}

	public static void setzeAktuellerZinssatz(double aktuellerZinssatz) {
		Kredit.aktuellerZinssatz = aktuellerZinssatz;
	}

	public static int holeAktuelleLaufzeit() {
		return aktuelleLaufzeit;
	}

	public static void setzeAktuelleLaufzeit(int aktuelleLaufzeit) {
		Kredit.aktuelleLaufzeit = aktuelleLaufzeit;
	}

	public double holeRestbetrag() {
		return restbetrag;
	}

	public void setzeRestbetrag(double restbetrag) {
		this.restbetrag = restbetrag;
	}

	public double holeZinssatz() {
		return zinssatz;
	}

	public double holeTilgung() {
		return tilgung;
	}

	public int holeLaufzeit() {
		return laufzeit;
	}
	
}
