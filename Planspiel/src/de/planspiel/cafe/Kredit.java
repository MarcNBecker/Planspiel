package de.planspiel.cafe;

/**
 * 
 * Klasse zur Simulation von Krediten
 * @author D059166
 *
 */
public class Kredit {
	
	private static double aktuellerZinssatz = 0; // TODO sinnvoller Startwert
	private static int aktuelleLaufzeit = 1; // TODO sinnvoller Startwert
	
	private Unternehmenskette kette;
	private double restbetrag;
	private double zinssatz; // pro periode
	private double tilgung;
	private int laufzeit;

	/**
	 * Beim Erstellen eines neuen Kredites werden die aktuellen Zinssätze und Laufzeiten, 
	 * die für alle Kredite zu einem bestimmten Zeitpunkt gelten genutzt
	 * @param kette Unternehmenskette, die den Kredit beantragt
	 * @param betrag Betrag, des Kredits. Dieser darf das Verhältnis 3:1 von EK zu FK nicht übeschreiten
	 */
	public Kredit(Unternehmenskette kette, double betrag) {
		this.kette = kette;
		zinssatz = aktuellerZinssatz;
		laufzeit = aktuelleLaufzeit;
		restbetrag = betrag;
		tilgung = betrag / laufzeit;
	}

	/**
	 * Tilgt den Kredit und verbucht die Kreditaufwendungen mit der Unternehmenskette
	 */
	public void tilgen() {
		if (holeRestbetrag() > 0) {
			holeKette().verbuchenKosten(Kostenverursacher.KREDIT, restbetrag * zinssatz);
			setzeRestbetrag(holeRestbetrag() - holeTilgung());
		}
		holeKette().entfernenKredit(this);
	}
	
	public static double holeAktuellerZinssatz() {
		return aktuellerZinssatz;
	}
	
	/**
	 * @param aktuellerZinssatz Zinssatz zwischen 0 und 1
	 */
	public static void setzeAktuellerZinssatz(double aktuellerZinssatz) {
		if(aktuellerZinssatz >= 0 && aktuellerZinssatz <= 1) {
			Kredit.aktuellerZinssatz = aktuellerZinssatz;
		}
	}

	public static int holeAktuelleLaufzeit() {
		return aktuelleLaufzeit;
	}
	
	/**
	 * @param aktuelleLaufzeit Laufzeit, größer als 0
	 */
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

	/**
	 * @param restbetrag Ist der Restbetrag kleiner als 0 wird der Restbetrag automatisch auf 0 gesetzt
	 */
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
