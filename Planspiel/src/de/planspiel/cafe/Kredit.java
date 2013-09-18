package de.planspiel.cafe;

public class Kredit {
	
	private static double aktuellerZinssatz = 0; // TODO sinnvoller Startwert
	private static int aktuelleLaufzeit = 1; // TODO sinnvoller Startwert
	
	private Unternehmenskette kette;
	private double restbetrag;
	private double zinssatz; // pro periode
	private double tilgung;
	private int laufzeit;

	public Kredit(Unternehmenskette kette, double betrag) {
		this.kette = kette;
		zinssatz = aktuellerZinssatz;
		laufzeit = aktuelleLaufzeit;
		restbetrag = betrag;
		tilgung = betrag / laufzeit;
	}

	public void tilgen() {
		if (holeRestbetrag() > 0) {
			// TODO zinskosten verbuchen
			// holeKette().verbuchenKosten(restbetrag * zinssatz);
			setzeRestbetrag(holeRestbetrag() - holeTilgung());
		}
		// TODO Kredit entfernen
		// holeKette().entferneKredit(this);
	}
	
	public static double holeAktuellerZinssatz() {
		return aktuellerZinssatz;
	}

	public static void setzeAktuellerZinssatz(double aktuellerZinssatz) {
		if(aktuellerZinssatz >= 0 && aktuellerZinssatz <= 1) {
			Kredit.aktuellerZinssatz = aktuellerZinssatz;
		}
	}

	public static int holeAktuelleLaufzeit() {
		return aktuelleLaufzeit;
	}

	public static void setzeAktuelleLaufzeit(int aktuelleLaufzeit) {
		if (aktuelleLaufzeit > 0) {
			Kredit.aktuelleLaufzeit = aktuelleLaufzeit;
		}
	}

	public Unternehmenskette holeKette() {
		return kette;
	}
	
	public double holeRestbetrag() {
		return restbetrag;
	}

	public void setzeRestbetrag(double restbetrag) {
		if (restbetrag > 0) {
			this.restbetrag = restbetrag;
		} else {
			this.restbetrag = 0;
		}
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
